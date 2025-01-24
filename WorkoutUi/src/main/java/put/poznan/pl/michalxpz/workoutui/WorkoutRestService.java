package put.poznan.pl.michalxpz.workoutui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import put.poznan.pl.michalxpz.workoutui.model.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class WorkoutRestService {
    private final RestTemplate restTemplate;

    @Value("${workout.gateway.url}")
    private String baseUrl;

    private ObjectMapper objectMapper;

    public WorkoutRestService(RestTemplate restTemplate, @Value("${workout.gateway.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper();
    }

    public WorkoutResponse getWorkoutById(Integer workout_id) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/api/workout/status/" + workout_id,
                HttpMethod.GET,
                null,
                String.class
        );
        WorkoutListResponse list = objectMapper.readValue(response.getBody(), WorkoutListResponse.class);
        return list.getWorkouts().get(0);
    }

    public List<WorkoutResponse> getAllWorkouts() throws JsonProcessingException {

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/api/workout/workouts",
                HttpMethod.GET,
                null,
                String.class
        );
        try {
            WorkoutListResponse list = objectMapper.readValue(response.getBody(), WorkoutListResponse.class);
            return list.getWorkouts();
        } catch (JsonProcessingException e) {
            return new WorkoutListResponse().getWorkouts();
        }
    }

    public WorkoutResponse startWorkout(StartWorkoutRequest request) {
        ResponseEntity<WorkoutResponse> response = restTemplate.postForEntity(
                baseUrl + "/api/workout/start",
                request,
                WorkoutResponse.class
        );
        return response.getBody();
    }

    public WorkoutResponse endWorkout(WorkoutEndRequest request) {
        Date now = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(now);
        request.setendDate(formattedDate);
        ResponseEntity<WorkoutResponse> response = restTemplate.postForEntity(
                baseUrl + "/api/workout/end",
                request,
                WorkoutResponse.class
        );
        return response.getBody();
    }

    public void deleteWorkout(String workoutId) {
        restTemplate.delete(baseUrl + "/api/workout/delete/" + workoutId);
    }

    public WorkoutPlanListResponse getAllWorkoutPlans() {
        ResponseEntity<WorkoutPlanListResponse> response = restTemplate.exchange(
                baseUrl + "/api/plan",
                HttpMethod.GET,
                null,
                WorkoutPlanListResponse.class
        );
        if (response.getBody() == null) {
            return new WorkoutPlanListResponse();
        }
        return response.getBody();
    }

    public WorkoutPlanResponse addWorkoutPlan(WorkoutPlanRequest request) {
        ResponseEntity<WorkoutPlanResponse> response = restTemplate.postForEntity(
                baseUrl + "/api/plan/add",
                request,
                WorkoutPlanResponse.class
        );
        return response.getBody();
    }

    public List<User> getUsers() {
        String response = restTemplate.getForObject(baseUrl + "/api/user", String.class);
        try {
            List<User> list = objectMapper.readValue(response, List.class);
            return list;
        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }
    }
}