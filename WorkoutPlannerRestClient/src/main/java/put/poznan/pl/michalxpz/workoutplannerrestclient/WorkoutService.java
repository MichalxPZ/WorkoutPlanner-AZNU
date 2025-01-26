package put.poznan.pl.michalxpz.workoutplannerrestclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import put.poznan.pl.michalxpz.workoutplannerrestclient.model.EndWorkoutRequest;
import put.poznan.pl.michalxpz.workoutplannerrestclient.model.StartWorkoutRequest;
import put.poznan.pl.michalxpz.workoutplannerrestclient.model.User;
import put.poznan.pl.michalxpz.workoutplannerrestclient.model.WorkoutResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutService {
    private final RestTemplate restTemplate;

    @Value("${workout.gateway.url}")
    private String workoutGatewayUrl;

    // Get all workouts
    public List<WorkoutResponse> getWorkouts() {
        String url = workoutGatewayUrl + "/workouts";
        ResponseEntity<List<WorkoutResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public List<WorkoutResponse> getWorkoutsByUser(Long userId) {
        String url = workoutGatewayUrl + "/workouts";
        if (userId != null) {
            url += "/user/" + userId;
        }
        ResponseEntity<List<WorkoutResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public WorkoutResponse getWorkoutById(Long workoutId) {
        String url = workoutGatewayUrl + "/workouts";
        if (workoutId != null) {
            url += "/" + workoutId;
        }
        ResponseEntity<WorkoutResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    // Get all users
    public List<User> getAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(
                workoutGatewayUrl + "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    // Get single user
    public User getUser(Long userId) {
        ResponseEntity<User> response = restTemplate.exchange(
                workoutGatewayUrl + "/users/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    // Start workout
    public WorkoutResponse startWorkout(StartWorkoutRequest request) {
        ResponseEntity<WorkoutResponse> response = restTemplate.postForEntity(
                workoutGatewayUrl + "/workouts/start",
                request,
                WorkoutResponse.class
        );
        log.info("Workout started: {}", response.getBody());
        return response.getBody();
    }

    // Get workout status
    public WorkoutResponse getWorkoutStatus(Long workoutId) {
        ResponseEntity<WorkoutResponse> response = restTemplate.getForEntity(
                workoutGatewayUrl + "/workouts/" + workoutId,
                WorkoutResponse.class
        );
        return response.getBody();
    }

    // End workout
    public void endWorkout(EndWorkoutRequest request) {
        restTemplate.postForEntity(
                workoutGatewayUrl + "/workouts/end",
                request,
                Void.class
        );
    }

    // Delete workout
    public void deleteWorkout(Long workoutId) {
        restTemplate.exchange(
                workoutGatewayUrl + "/workouts/{workoutId}",
                HttpMethod.DELETE,
                null,
                Void.class,
                workoutId
        );
    }

    // Create user
    public User createUser(User user) {
        ResponseEntity<User> response = restTemplate.postForEntity(
                workoutGatewayUrl + "/users",
                user,
                User.class
        );
        return response.getBody();
    }
}