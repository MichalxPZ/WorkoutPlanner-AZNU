package put.poznan.pl.michalxpz.workoutplannerrestservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import put.poznan.pl.michalxpz.workoutplannerrestservice.model.*;
import put.poznan.pl.michalxpz.workoutplannerrestservice.users.UserService;
import put.poznan.pl.michalxpz.workoutplannerrestservice.workouts.WorkoutService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutKafkaListener {
    private final WorkoutService workoutService;
    private final UserService userService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

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
            StartWorkoutRequest workout = mapper.readValue(workoutJson, StartWorkoutRequest.class);
            Workout savedWorkout = workoutService.startWorkout(workout);
            kafkaTemplate.send(workoutStartedTopic, objectMapper.writeValueAsString(savedWorkout));
        } catch (JsonProcessingException e) {
            log.error("Error while starting brew", e);
            WorkoutErrorMessage workoutErrorMessage = new WorkoutErrorMessage(e.getMessage());
            kafkaTemplate.send(workoutErrorTopic, objectMapper.writeValueAsString(workoutErrorMessage));
        }
    }
    @KafkaListener(topics = "${kafka.topic.workout-requested}")
    public void handleWorkoutRequested(String workoutJson) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Workout> workouts = workoutService.getWorkouts();
            kafkaTemplate.send("kafka:"+ workoutResponseTopic, objectMapper.writeValueAsString(workouts));
        } catch (JsonProcessingException e) {
            log.error("Error while starting brew", e);
            WorkoutErrorMessage workoutErrorMessage = new WorkoutErrorMessage(e.getMessage());
            kafkaTemplate.send(workoutErrorTopic, objectMapper.writeValueAsString(workoutErrorMessage));
        }
    }

    @KafkaListener(topics = "${kafka.topic.workout-ended}")
    public void handleWorkoutEnded(String workoutJson) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            EndWorkoutRequest workout = mapper.readValue(workoutJson, EndWorkoutRequest.class);
            workoutService.endWorkout(workout);
        } catch (JsonProcessingException e) {
            log.error("Error while starting brew", e);
            WorkoutErrorMessage workoutErrorMessage = new WorkoutErrorMessage(e.getMessage());
            kafkaTemplate.send(workoutErrorTopic, objectMapper.writeValueAsString(workoutErrorMessage));
        }
    }

    @KafkaListener(topics = "${kafka.topic.workout-deleted}")
    public void handleWorkoutDeleted(String workoutId) {
        workoutService.removeWorkout(Long.parseLong(workoutId));
    }

    @KafkaListener(topics = "${kafka.topic.user-create}")
    public void handleUserCreated(String userJson) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserRequest user = mapper.readValue(userJson, UserRequest.class);
            User user1 = new User();
            user1.setName(user.getName());
            user1.setSurname(user.getSurname());
            user1.setEmail(user.getEmail());
            User createdUser = userService.createUser(user1);
            kafkaTemplate.send(userCreatedTopic, objectMapper.writeValueAsString(createdUser));
        } catch (JsonProcessingException e) {
            log.error("Error while starting brew", e);
            WorkoutErrorMessage workoutErrorMessage = new WorkoutErrorMessage(e.getMessage());
            kafkaTemplate.send(workoutErrorTopic, objectMapper.writeValueAsString(workoutErrorMessage));
        }
    }
}