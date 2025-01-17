package put.poznan.pl.michalxpz.workoutintegration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import put.poznan.pl.michalxpz.workoutintegration.model.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WorkoutService extends RouteBuilder {

    @Value("${kafka.topic.workout-requested}")
    private String workoutRequestedTopic;

    @Value("${kafka.topic.workout-response}")
    private String workoutResponseTopic;

    @Value("${kafka.topic.workout-started}")
    private String workoutStartedTopic;

    @Value("${kafka.topic.workout-start}")
    private String workoutStartTopic;

    @Value("${kafka.topic.workout-ended}")
    private String workoutEndedTopic;

    @Value("${kafka.topic.workout-end}")
    private String workoutEndTopic;

    @Value("${kafka.topic.workout-deleted}")
    private String workoutDeletedTopic;

    @Value("${kafka.topic.workout-delete}")
    private String workoutDeleteTopic;

    @Value("${kafka.topic.plan-added}")
    private String planAddedTopic;

    @Value("${kafka.topic.plan-add}")
    private String planAddTopic;

    @Value("${kafka.topic.plan-updated}")
    private String planUpdatedTopic;

    @Value("${kafka.topic.plan-update}")
    private String planUpdateTopic;

    @Value("${kafka.topic.plan-deleted}")
    private String planDeletedTopic;

    @Value("${kafka.topic.plan-delete}")
    private String planDeleteTopic;

    @Value("${kafka.topic.user-created}")
    private String userCreatedTopic;

    @Value("${kafka.topic.user-create}")
    private String userCreateTopic;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure() throws Exception {
        gateway();
        restIntegration();
        soapIntegration();
        userCreatedKafkaRoute();
    }

    public void gateway() {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)
                .contextPath("/api")
                .host("localhost")
                .port(8080)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Workout Planner API")
                .apiProperty("api.version", "1.0.0");

        workoutRoutes();
        userRoutes();
        workoutPlanRoutes();
    }

    private void workoutRoutes() {
        rest("/workout").description("Workout REST service")
                .consumes("application/json")
                .produces("application/json")
                .post("/start").description("Create a new workout plan").type(WorkoutStartRequest.class).outType(WorkoutResponse.class)
                .param().name("body").type(RestParamType.body).description("Workout details").endParam()
                .responseMessage().code(200).message("Workout started").endResponseMessage()
                .to("direct:workout-start")
                .post("/end").description("End an existing workout").type(WorkoutEndRequest.class).outType(WorkoutResponse.class)
                .param().name("body").type(RestParamType.body).description("Workout end details").endParam()
                .responseMessage().code(200).message("Workout ended").endResponseMessage()
                .to("direct:workout-end")
                .get("/status/{workoutId}").description("Get workout status").outType(WorkoutResponse.class)
                .param().name("workoutId").type(RestParamType.path).description("Workout id").endParam()
                .responseMessage().code(200).message("Workout status").endResponseMessage()
                .responseMessage().code(404).message("Workout not found").endResponseMessage()
                .to("direct:workout-requested")
                .post("/delete").description("Cancel a workout plan")
                .param().name("workoutId").type(RestParamType.path).description("Workout id").endParam()
                .responseMessage().code(200).message("Workout deleted").endResponseMessage()
                .responseMessage().code(404).message("Workout not found").endResponseMessage()
                .to("direct:workout-delete");
    }

    private void userRoutes() {
        rest("/user").description("User management service")
                .consumes("application/json")
                .produces("application/json")
                .post("/create").description("Create a new user").type(User.class).outType(UserResponse.class)
                .param().name("body").type(RestParamType.body).description("User details").endParam()
                .responseMessage().code(200).message("User created").endResponseMessage()
                .to("direct:user-create");
    }

    private void workoutPlanRoutes() {
        rest("/plan").description("Workout plan management service")
                .consumes("application/json")
                .produces("application/json")
                .post("/add").description("Add a new workout plan").type(WorkoutPlanRequest.class).outType(WorkoutPlanResponse.class)
                .param().name("body").type(RestParamType.body).description("Workout plan details").endParam()
                .responseMessage().code(200).message("Workout plan added").endResponseMessage()
                .to("direct:add-workout-plan")
                .put("/update/{planId}").description("Update an existing workout plan").type(WorkoutPlanRequest.class).outType(WorkoutPlanResponse.class)
                .param().name("planId").type(RestParamType.path).description("Workout plan id").endParam()
                .param().name("body").type(RestParamType.body).description("Updated workout plan details").endParam()
                .responseMessage().code(200).message("Workout plan updated").endResponseMessage()
                .to("direct:update-workout-plan")
                .delete("/delete/{planId}").description("Delete a workout plan").param().name("planId").type(RestParamType.path).description("Workout plan id").endParam()
                .responseMessage().code(200).message("Workout plan deleted").endResponseMessage()
                .responseMessage().code(404).message("Workout plan not found").endResponseMessage()
                .to("direct:delete-workout-plan");
    }

    private void restIntegration() {
        from("direct:workout-start")
                .routeId("workout-started")
                .log("Starting workout: ${body}")
                .marshal().json(JsonLibrary.Jackson) // Convert object to JSON
                .to("kafka:" + workoutStartTopic);
        from("kafka:" + workoutStartedTopic)
                .unmarshal().json(JsonLibrary.Jackson)
                .log("Workout started: ${body}")
                .process(exchange -> {
                    try {
                        Map<String, Object> response = (Map<String, Object>) exchange.getIn().getBody();
                        exchange.getIn().setBody(response);
                    } catch (Exception e) {
                        log.error("Error ", e);
                    }
                });

        from("direct:workout-end")
                .routeId("workout-ended")
                .log("Ending workout: ${body}")
                .marshal().json(JsonLibrary.Jackson) // Convert object to JSON
                .to("kafka:" + workoutEndTopic); // Send to Kafka topic
        from("kafka:" + workoutEndedTopic)
                .unmarshal().json(JsonLibrary.Jackson)
                .log("Workout started: ${body}")
                .process(exchange -> {
                    WorkoutResponse workout = exchange.getIn().getBody(WorkoutResponse.class);
                    exchange.getIn().setBody(workout);
                });
        from("direct:workout-requested")
                .routeId("workout-request")
                .log("Fetching workout status for workoutId: ${header.workoutId}")
                .marshal().json(JsonLibrary.Jackson) // Convert object to JSON
                .to("kafka:" + workoutRequestedTopic); // Send to Kafka topic
        from("kafka:" + workoutResponseTopic)
                .routeId("workout-response")
                .log("Fetching workout status for workoutId: ${header.workoutId}")
                .unmarshal().json(JsonLibrary.Jackson)
                .process(exchange -> {
                    List<WorkoutResponse> workouts = exchange.getIn().getBody(List.class);
                    exchange.getIn().setBody(workouts);
                });
        from("direct:delete-workout")
                .routeId("delete-workout")
                .log("Deleting workout plan with ID: ${header.workoutId}")
                .to("kafka:" + workoutDeleteTopic); // Send to Kafka topic
        from("kafka:" + workoutDeletedTopic)
                .unmarshal().json(JsonLibrary.Jackson)
                .log("Workout started: ${body}")
                .process(exchange -> {
                    String workout = exchange.getIn().getBody().toString();
                    exchange.getIn().setBody(workout);
                });
    }

    private void soapIntegration() {
        from("direct:add-workout-plan")
                .routeId("add-workout-plan")
                .log("Adding workout plan: ${body}")
                .marshal().json(JsonLibrary.Jackson) // Convert object to JSON
                .to("kafka:" + planAddedTopic); // Send to Kafka topic
        from("direct:update-workout-plan")
                .routeId("update-workout-plan")
                .log("Updating workout plan with ID: ${header.planId}")
                .marshal().json(JsonLibrary.Jackson) // Convert object to JSON
                .to("kafka:" + planUpdatedTopic); // Send to Kafka topic
        from("direct:delete-workout-plan")
                .routeId("delete-workout-plan")
                .log("Deleting workout plan with ID: ${header.planId}")
                .to("kafka:" + planDeletedTopic); // Send to Kafka topic
    }

    private void userCreatedKafkaRoute() {
//        from("kafka:"+userCreatedTopic)
//                .routeId("user-created")
//                .log("Creating user: ${body}")
//                .marshal().json(JsonLibrary.Jackson)
//                        .process(exchange -> {
//                            UserResponse user = exchange.getIn().getBody(UserResponse.class);
//                            exchange.getIn().setBody(user);
//                        });
         from("direct:user-create")
                .routeId("user-create")
                .log("Creating user: ${body}")
                .marshal().json(JsonLibrary.Jackson)
                 .to("kafka:" + userCreateTopic)
                 .pollEnrich()
                 .simple("kafka:" + userCreatedTopic)
                 .timeout(10000)
                    .unmarshal().json(JsonLibrary.Jackson)
                 .log("Received response: ${body}")
                 .process(exchange -> {
                     try {
                         Map<String, Object> response = (Map<String, Object>) exchange.getIn().getBody();
                         exchange.getIn().setBody(response);
                     } catch (Exception e) {
                         log.error("Error processing response", e);
                         throw e;
                     }
                 });

         ; // Convert object to JSON
    }
}