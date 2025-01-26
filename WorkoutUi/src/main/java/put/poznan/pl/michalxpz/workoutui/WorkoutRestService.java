package put.poznan.pl.michalxpz.workoutui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import put.poznan.pl.michalxpz.workoutui.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
@Slf4j
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
        if (list.getWorkouts().isEmpty()) {
            return new WorkoutResponse();
        }
        WorkoutResponse workoutResponse = list.getWorkouts().get(0);
        try {
            if (workoutResponse.getStartDate() != null) {
                String startDate = parseWorkoutDate(workoutResponse.getStartDate());
                workoutResponse.setStartDate(startDate);
            }
            if (workoutResponse.getEndDate() != null) {
                String endDate = parseWorkoutDate(workoutResponse.getEndDate());
                workoutResponse.setEndDate(endDate);
            }
        } catch (ParseException e) {
            log.error("Error while parsing date: {}", e.getMessage());
        }
        return workoutResponse;
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
            list.getWorkouts().forEach(workout -> {
                try {
                    if (workout.getStartDate() != null) {
                        String date = parseWorkoutDate(workout.getStartDate());
                        workout.setStartDate(date);
                    }
                    if (workout.getEndDate() != null) {
                        String date = parseWorkoutDate(workout.getEndDate());
                        workout.setEndDate(date);
                    }
                } catch (ParseException e) {
                    log.error("Error while parsing date: {}", e.getMessage());
                }
            });
            return list.getWorkouts();
        } catch (JsonProcessingException e) {
            return new WorkoutListResponse().getWorkouts();
        }
    }

    public WorkoutResponse  startWorkout(StartWorkoutRequest request) {
        ResponseEntity<WorkoutResponse> response = restTemplate.postForEntity(
                baseUrl + "/api/workout/start",
                request,
                WorkoutResponse.class
        );
        // Formatowanie daty
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WorkoutResponse workout = response.getBody();
        try {
            if (workout.getStartDate() != null) {
                workout.setStartDate(dateFormat.format(workout.getStartDate()));
            }
            if (workout.getEndDate() != null) {
                workout.setEndDate(dateFormat.format(workout.getEndDate()));
            }
        } catch (Exception e) {
            log.error("Error while parsing date: {}", e.getMessage());
        }
        return workout;
    }

    public WorkoutResponse endWorkout(WorkoutEndRequest request) {
        Date now = new Date();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = outputFormat.format(now);
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

    private static String parseWorkoutDate(String date) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Traktuj datę jako lokalną

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Zachowaj czas bez zmiany
        Date parsedDate = inputFormat.parse(date);
        return outputFormat.format(parsedDate);
    }
}