package put.poznan.pl.michalxpz.workoutplannersoapclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;
import put.poznan.pl.michalxpz.workoutplannersoap.AddWorkoutSOAPRequest;
import put.poznan.pl.michalxpz.workoutplannersoap.WorkoutSOAPResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutPlanKafkaListener {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final WorkoutSoapService workoutSoapService;

    @Value("${kafka.topic.plan-added}")
    private String planAddedTopic;

    @Value("${kafka.topic.plan-request}")
    private String workoutPlanRequested;

    @Value("${kafka.topic.plan-response}")
    private String workoutPlanResponse;

    @Value("${kafka.topic.plan-add}")
    private String planAddTopic;

    @Value("${kafka.topic.plan-deleted}")
    private String planDeletedTopic;

    @Value("${kafka.topic.plan-delete}")
    private String planDeleteTopic;

    @Value("${kafka.topic.workout-error}")
    private String workoutErrorTopic;

    @KafkaListener(topics = "${kafka.topic.plan-add}")
    public void listenPlanAdd(String message) throws JsonProcessingException {
        try {
            log.info("Received message: {}", message);
            ObjectMapper mapper = new ObjectMapper();
            MessageWrapper<AddWorkoutSOAPRequest> wrapper = mapper.readValue(message, new TypeReference<>() {});
            String correlationId = wrapper.getCorrelationId();
            MessageWrapper<WorkoutSOAPResponse> responseWrapper = new MessageWrapper<>();
            responseWrapper.setCorrelationId(correlationId);
            responseWrapper.setPayload(workoutSoapService.addWorkout(wrapper.getPayload()));
            kafkaTemplate.send(planAddedTopic, objectMapper.writeValueAsString(responseWrapper));
            log.info("Sent response: {}", responseWrapper);
        } catch (Exception e) {
            handleError(message, e);
        }
    }
    @KafkaListener(topics = "${kafka.topic.plan-request}")
    public void listenGetAllPlans(String message) throws JsonProcessingException {
        try {
            log.info("Received message: {}", message);
            ObjectMapper mapper = new ObjectMapper();
            MessageWrapper<Object> wrapper = mapper.readValue(message, new TypeReference<>() {});
            String correlationId = wrapper.getCorrelationId();
            MessageWrapper<WorkoutPlanListResponse> responseWrapper = new MessageWrapper<>();
            responseWrapper.setCorrelationId(correlationId);
            WorkoutPlanListResponse workoutPlanListResponse = new WorkoutPlanListResponse();
            workoutPlanListResponse.setWorkoutPlanList(workoutSoapService.getAllWorkouts());
            responseWrapper.setPayload(workoutPlanListResponse);
            kafkaTemplate.send(workoutPlanResponse, objectMapper.writeValueAsString(responseWrapper));
            log.info("Sent response: {}", responseWrapper);
        } catch (Exception e) {
            handleError(message, e);
        }
    }

    @KafkaListener(topics = "${kafka.topic.plan-delete}")
    public void listenPlanDelete(String message) throws JsonProcessingException {
        try {
            log.info("Received message: {}", message);
            ObjectMapper mapper = new ObjectMapper();
            MessageWrapper<Object> wrapper = mapper.readValue(message, new TypeReference<>() {});
            String correlationId = wrapper.getCorrelationId();
            Long id = Long.parseLong(wrapper.getPayload().toString());
            workoutSoapService.deleteWorkout(id);
            MessageWrapper<Object> responseWrapper = new MessageWrapper<>();
            responseWrapper.setCorrelationId(correlationId);
            kafkaTemplate.send(planDeletedTopic, objectMapper.writeValueAsString(responseWrapper));
            log.info("Sent response: {}", responseWrapper);
        } catch (Exception e) {
            handleError(message, e);
        }
    }

    private void handleError(String messageJson, Exception e) throws JsonProcessingException {
        log.error("Error while processing message", e);
        MessageWrapper<Object> wrapper = objectMapper.readValue(
                messageJson,
                new TypeReference<>() {
                }
        );
        String correlationId = wrapper.getCorrelationId();
        WorkoutErrorMessage workoutErrorMessage = new WorkoutErrorMessage(correlationId, e.getMessage());
        kafkaTemplate.send(workoutErrorTopic,
                objectMapper.writeValueAsString(workoutErrorMessage));
        log.info("Sent error message: {}", workoutErrorMessage);
    }
}
