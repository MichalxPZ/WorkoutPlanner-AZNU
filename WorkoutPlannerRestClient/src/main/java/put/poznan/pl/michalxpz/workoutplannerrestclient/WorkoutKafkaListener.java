package put.poznan.pl.michalxpz.workoutplannerrestclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import put.poznan.pl.michalxpz.workoutplannerrestclient.model.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutKafkaListener {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private final WorkoutService workoutService;

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

    @Value("${kafka.topic.workout-deleted}")
    private String workoutDeletedTopic;

    @Value("${kafka.topic.workout-error}")
    private String workoutErrorTopic;

    @Value("${kafka.topic.user-create}")
    private String userCreateTopic;

    @Value("${kafka.topic.user-created}")
    private String userCreatedTopic;

    @KafkaListener(topics = "${kafka.topic.workout-start}")
    public void handleWorkoutStarted(String workoutJson) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MessageWrapper<StartWorkoutRequest> wrapper = mapper.readValue(workoutJson, new TypeReference<>() {});
            WorkoutResponse savedWorkout = workoutService.startWorkout(wrapper.getPayload());
            String correlationId = wrapper.getCorrelationId();
            MessageWrapper<WorkoutResponse> responseWrapper = new MessageWrapper<>();
            responseWrapper.setCorrelationId(correlationId);
            responseWrapper.setPayload(savedWorkout);

            kafkaTemplate.send(workoutStartedTopic,
                    objectMapper.writeValueAsString(responseWrapper));
        } catch (JsonProcessingException | ResourceAccessException | HttpServerErrorException e) {
            handleError(workoutJson, e);
        }
    }
    @KafkaListener(topics = "${kafka.topic.workout-requested}")
    public void handleWorkoutRequested(String workoutJson) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MessageWrapper<Object> wrapper = mapper.readValue(workoutJson, new TypeReference<>() {});
            String correlationId = wrapper.getCorrelationId();
            Long workoutId = null;
            try {
                 workoutId = Long.parseLong((String) wrapper.getPayload());
            } catch (Exception e) {
                log.error("Error while parsing workout ID", e);
            }
            List<WorkoutResponse> workouts;
            if (workoutId == null)
              workouts = workoutService.getWorkouts();
            else workouts = List.of(workoutService.getWorkoutById(workoutId));
            MessageWrapper<WorkoutListResponse> responseWrapper = new MessageWrapper<>();
            WorkoutListResponse workoutListResponse = new WorkoutListResponse();
            workoutListResponse.setWorkoutList(workouts);
            responseWrapper.setCorrelationId(correlationId);
            responseWrapper.setPayload(workoutListResponse);
            kafkaTemplate.send(workoutResponseTopic, objectMapper.writeValueAsString(responseWrapper));
        } catch (Exception e) {
            handleError(workoutJson, e);
        }
    }

    @KafkaListener(topics = "${kafka.topic.workout-end}")
    public void handleWorkoutEnded(String workoutJson) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MessageWrapper<EndWorkoutRequest> wrapper = mapper.readValue(workoutJson, new TypeReference<>() {});
            workoutService.endWorkout(wrapper.getPayload());
            String correlationId = wrapper.getCorrelationId();
            MessageWrapper<WorkoutResponse> responseWrapper = new MessageWrapper<>();
            responseWrapper.setCorrelationId(correlationId);
            responseWrapper.setPayload(workoutService.getWorkoutStatus(wrapper.getPayload().getWorkoutId()));
            kafkaTemplate.send(workoutEndedTopic,
                    objectMapper.writeValueAsString(responseWrapper));
        } catch (JsonProcessingException | ResourceAccessException | HttpServerErrorException e) {
            handleError(workoutJson, e);
        }
    }

    @KafkaListener(topics = "${kafka.topic.workout-delete}")
    public void handleWorkoutDeleted(String workoutJson) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MessageWrapper<Object> wrapper = mapper.readValue(workoutJson, new TypeReference<>() {});
            String correlationId = wrapper.getCorrelationId();
            Long workoutId = null;
            try {
                workoutId = Long.parseLong((String) wrapper.getPayload());
            } catch (Exception e) {
               handleError(workoutJson, e);
               return;
            }
            workoutService.deleteWorkout(workoutId);
            MessageWrapper<String> responseWrapper = new MessageWrapper<>();
            responseWrapper.setCorrelationId(correlationId);
            responseWrapper.setPayload("Successfully deleted workout with id:" + workoutId);
            kafkaTemplate.send(workoutDeletedTopic, objectMapper.writeValueAsString(responseWrapper));
        } catch (Exception e) {
            handleError(workoutJson, e);
        }
    }

    @KafkaListener(topics = "${kafka.topic.user-create}")
    public void handleUserCreated(String messageJson) throws JsonProcessingException {
        try {
            MessageWrapper<UserRequest> wrapper = objectMapper.readValue(
                    messageJson,
                    new TypeReference<>() {}
            );

            String correlationId = wrapper.getCorrelationId();
            UserRequest user = wrapper.getPayload();

            User user1 = new User();
            user1.setName(user.getName());
            user1.setSurname(user.getSurname());
            user1.setEmail(user.getEmail());
            User createdUser = workoutService.createUser(user1);

            // Wrap the response with the same correlation ID
            MessageWrapper<User> responseWrapper = new MessageWrapper<>();
            responseWrapper.setCorrelationId(correlationId);
            responseWrapper.setPayload(createdUser);

            kafkaTemplate.send(userCreatedTopic,
                    objectMapper.writeValueAsString(responseWrapper));

        } catch (Exception e) {
            handleError(messageJson, e);
        }
    }

    private void handleError(String messageJson, Exception e) throws JsonProcessingException {
        log.error("Error while creating user", e);
        MessageWrapper<Object> wrapper = objectMapper.readValue(
                messageJson,
                new TypeReference<>() {
                }
        );
        String correlationId = wrapper.getCorrelationId();
        WorkoutErrorMessage workoutErrorMessage = new WorkoutErrorMessage(correlationId, e.getMessage());
        kafkaTemplate.send(workoutErrorTopic,
                objectMapper.writeValueAsString(workoutErrorMessage));
    }
}