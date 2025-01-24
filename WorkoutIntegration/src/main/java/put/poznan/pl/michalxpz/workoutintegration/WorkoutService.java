package put.poznan.pl.michalxpz.workoutintegration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import put.poznan.pl.michalxpz.workoutintegration.model.*;
import put.poznan.pl.michalxpz.workoutintegration.model.plan.*;
import put.poznan.pl.michalxpz.workoutintegration.model.user.User;
import put.poznan.pl.michalxpz.workoutintegration.model.user.UserResponse;
import put.poznan.pl.michalxpz.workoutintegration.model.user.UserResponseList;
import put.poznan.pl.michalxpz.workoutintegration.model.workout.WorkoutEndRequest;
import put.poznan.pl.michalxpz.workoutintegration.model.workout.WorkoutListResponse;
import put.poznan.pl.michalxpz.workoutintegration.model.workout.WorkoutResponse;
import put.poznan.pl.michalxpz.workoutintegration.model.workout.WorkoutStartRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WorkoutService extends RouteBuilder {

    private final ProducerTemplate producerTemplate;

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

    @Value("${kafka.topic.plan-request}")
    private String workoutPlansRequested;

    @Value("${kafka.topic.plan-response}")
    private String workoutPlansResponse;

    @Value("${kafka.topic.plan-deleted}")
    private String planDeletedTopic;

    @Value("${kafka.topic.plan-delete}")
    private String planDeleteTopic;

    @Value("${kafka.topic.user-created}")
    private String userCreatedTopic;

    @Value("${kafka.topic.user-create}")
    private String userCreateTopic;

    @Value("${kafka.topic.get-users}")
    private String getUsersTopic;

    @Value("${kafka.topic.users-response}")
    private String usersReponseTopic;

    @Value("${kafka.topic.workout-error}")
    private String workoutErrorTopic;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure() {
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
                .responseMessage().code(400).message("Workout not started").endResponseMessage()
                .to("direct:workout-start")
                .post("/end").description("End an existing workout").type(WorkoutEndRequest.class).outType(WorkoutResponse.class)
                .param().name("body").type(RestParamType.body).description("Workout end details").endParam()
                .responseMessage().code(200).message("Workout ended").endResponseMessage()
                .responseMessage().code(404).message("Workout not found").endResponseMessage()
                .to("direct:workout-end")
                .get("/workouts").description("Get workouts").outType(WorkoutListResponse.class)
                .responseMessage().code(200).message("Workouts").endResponseMessage()
                .responseMessage().code(404).message("Workout not found").endResponseMessage()
                .to("direct:workouts")
                .get("/status/{workoutId}").description("Get workout status").outType(WorkoutListResponse.class)
                .param().name("workoutId").type(RestParamType.path).description("Workout id").endParam()
                .responseMessage().code(200).message("Workout status").endResponseMessage()
                .responseMessage().code(404).message("Workout not found").endResponseMessage()
                .to("direct:workout-requested")
                .delete("/delete/{workoutId}").description("Cancel a workout plan").outType(String.class)
                .param().name("workoutId").type(RestParamType.path).description("Workout id").endParam()
                .responseMessage().code(200).message("Workout deleted").endResponseMessage()
                .responseMessage().code(404).message("Workout not found").endResponseMessage()
                .to("direct:delete-workout");
    }

    private void userRoutes() {
        rest("/user").description("User management service")
                .consumes("application/json")
                .produces("application/json")
                .post("/create").description("Create a new user").type(User.class).outType(UserResponse.class)
                .param().name("body").type(RestParamType.body).description("User details").endParam()
                .responseMessage().code(200).message("User created").endResponseMessage()
                .responseMessage().code(400).message("Bad Request - Error during user creation").endResponseMessage()
                .to("direct:user-create")
                .get().outType(UserResponseList.class)
                .responseMessage().code(200).message("User created").endResponseMessage()
                .responseMessage().code(404).message("User not found").endResponseMessage()
                .to("direct:users");
    }

    private void workoutPlanRoutes() {
        rest("/plan").description("Workout plan management service")
                .consumes("application/json")
                .produces("application/json")
                .get().description("Get all workout plans").outType(WorkoutPlanListReponse.class)
                .responseMessage().code(200).message("Workout plans").endResponseMessage()
                .responseMessage().code(404).message("Workout plans not found").endResponseMessage()
                .to("direct:workout-plans")
                .post("/add").description("Add a new workout plan").type(AddWorkoutSOAPRequest.class).outType(WorkoutPlanResponse.class)
                .param().name("body").type(RestParamType.body).description("Workout plan details").endParam()
                .responseMessage().code(201).message("Workout plan added").endResponseMessage()
                .responseMessage().code(400).message("Workout plan not added").endResponseMessage()
                .to("direct:add-workout-plan")
                .delete("/delete/{workoutId}").description("Delete a workout plan").outType(String.class)
                .param().name("workoutId").type(RestParamType.path).description("Workout plan id").endParam()
                .responseMessage().code(200).message("Workout plan deleted").endResponseMessage()
                .responseMessage().code(404).message("Workout plan not found").endResponseMessage()
                .to("direct:delete-workout-plan");
    }

    private void restIntegration() {
        // Start workout
        from("direct:workout-start")
                .routeId("workout-started")
                .log("Starting workout: ${body}")
                .process(exchange -> processExchange(
                        exchange,
                        workoutStartTopic,
                        workoutStartedTopic,
                        "workout-processor",
                        WorkoutResponse.class,
                        10000
                ));

        // End workout
        from("direct:workout-end")
                .routeId("workout-ended")
                .log("Ending workout: ${body}")
                .process(exchange -> processExchange(
                        exchange,
                        workoutEndTopic,
                        workoutEndedTopic,
                        "workout-processor",
                        WorkoutResponse.class,
                        10000
                ));

        // Request workout
        from("direct:workout-requested")
                .routeId("workout-request")
                .log("Fetching workout status for workoutId: ${header.workoutId}")
                .process(exchange -> processExchange(
                        exchange,
                        workoutRequestedTopic,
                        workoutResponseTopic,
                        "workout-processor",
                        WorkoutListResponse.class,
                        10000
                ));
        from("direct:workouts")
                .routeId("workouts-request")
                .log("Fetching workouts")
                .process(exchange -> processExchange(
                        exchange,
                        workoutRequestedTopic,
                        workoutResponseTopic,
                        "workout-processor",
                        WorkoutListResponse.class,
                        10000
                ));

        // Delete workout
        from("direct:delete-workout")
                .routeId("delete-workout")
                .log("Deleting workout plan with ID: ${header.workoutId}")
                .process(exchange -> processExchange(
                        exchange,
                        workoutDeleteTopic,
                        workoutDeletedTopic,
                        "workout-processor",
                        String.class,
                        10000
                ));
    }

    private void soapIntegration() {
        from("direct:workout-plans")
                .routeId("workout-plans")
                .log("Starting workout: ${body}")
                .process(exchange -> processExchange(
                        exchange,
                        workoutPlansRequested,
                        workoutPlansResponse,
                        "workout-processor",
                        WorkoutPlanListReponse.class,
                        10000
                ));
        // Add workout plan
        from("direct:add-workout-plan")
                .routeId("add-workout-plan")
                .log("Adding workout plan: ${body}")
                .process(exchange -> processExchange(
                        exchange,
                        planAddTopic,
                        planAddedTopic,
                        "plan-processor",
                        WorkoutPlanResponse.class,
                        10000
                ));

        // Delete workout plan
        from("direct:delete-workout-plan")
                .routeId("delete-workout-plan")
                .log("Deleting workout plan with ID: ${header.planId}")
                .process(exchange -> processExchange(
                        exchange,
                        planDeleteTopic,
                        planDeletedTopic,
                        "plan-processor",
                        String.class,
                        10000
                ));
    }

    private void userCreatedKafkaRoute() {
        from("direct:user-create")
                .routeId("user-create")
                .log("Creating user: ${body}")
                .process(exchange -> processExchange(
                        exchange,
                        userCreateTopic,
                        userCreatedTopic,
                        "user-processor",
                        UserResponse.class,
                        10000
                ));
        from("direct:users")
                .routeId("users")
                .log("Fetching users")
                .process(exchange -> processExchange(
                        exchange,
                        getUsersTopic,
                        usersReponseTopic,
                        "user-processor",
                        List.class,
                        10000
                ));
    }

    private <T> void processExchange(
            Exchange exchange,
            String sendTopic,
            String receiveTopic,
            String groupId,
            Class<T> responseType,
            long timeoutMillis
    ) {
        Object originalBody = exchange.getIn().getBody();
        String workoutId = (String) exchange.getIn().getHeaders().getOrDefault("workoutId", null);
        try {
            T response = sendAndReceiveMessage(
                        originalBody,
                        workoutId,
                        sendTopic,
                        receiveTopic,
                        groupId,
                        responseType,
                        timeoutMillis
                );
            exchange.getIn().setBody(response);
        } catch (WorkoutServiceException e) {
            exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
            exchange.getIn().setBody(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error during Kafka communication", e);
        }
    }

    <T> T sendAndReceiveMessage(
            Object payload,
            String workoutId,
            String sendTopic,
            String receiveTopic,
            String groupId,
            Class<T> responseType,
            long timeoutMillis
    ) throws WorkoutServiceException, IOException {
        String correlationId = UUID.randomUUID().toString();

        // Tworzenie wrappera wiadomości
        MessageWrapper<Object> wrapper = new MessageWrapper<>();
        wrapper.setCorrelationId(correlationId);
        if (payload instanceof InputStream || payload == null) {
            wrapper.setPayload(workoutId);
        } else {
            wrapper.setPayload(payload);
        }

        // Wysyłanie wiadomości do Kafka
        String messageBody = objectMapper.writeValueAsString(wrapper);
        producerTemplate.sendBody("kafka:" + sendTopic, messageBody);

        // Inicjalizacja konsumenta
        ConsumerTemplate consumer = getCamelContext().createConsumerTemplate();
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeoutMillis) {
            // Odbieranie odpowiedzi z głównego topicu
            Exchange receivedExchange = consumer.receive("kafka:" + receiveTopic + "?groupId=" + groupId, 1000);
            if (receivedExchange != null) {
                String receivedBody = receivedExchange.getIn().getBody(String.class);
                MessageWrapper<?> receivedWrapper = objectMapper.readValue(receivedBody, MessageWrapper.class);
                if (correlationId.equals(receivedWrapper.getCorrelationId())) {
                    consumer.close();
                    return objectMapper.convertValue(receivedWrapper.getPayload(), responseType);
                } else {
                    producerTemplate.sendBody("kafka:" + receiveTopic, receivedBody);
                }
            }

            // Odbieranie błędów z errorTopic
            Exchange errorExchange = consumer.receive("kafka:" + workoutErrorTopic + "?groupId=" + groupId, 1000);
            if (errorExchange != null) {
                String errorBody = errorExchange.getIn().getBody(String.class);
                WorkoutErrorMessage errorMessage = objectMapper.readValue(errorBody, WorkoutErrorMessage.class);
                if (correlationId.equals(errorMessage.correlationId())) {
                    consumer.close();
                    throw new WorkoutServiceException("Error received: " + errorMessage.message());
                }
            }
        }

        consumer.close();
        throw new RuntimeException("Timeout waiting for response with correlationId: " + correlationId);
    }
}