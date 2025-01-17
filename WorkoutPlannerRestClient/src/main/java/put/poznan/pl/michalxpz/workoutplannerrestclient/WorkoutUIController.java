package put.poznan.pl.michalxpz.workoutplannerrestclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import put.poznan.pl.michalxpz.workoutplannerrestclient.model.EndWorkoutRequest;
import put.poznan.pl.michalxpz.workoutplannerrestclient.model.StartWorkoutRequest;
import put.poznan.pl.michalxpz.workoutplannerrestclient.model.User;
import put.poznan.pl.michalxpz.workoutplannerrestclient.model.WorkoutResponse;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workouts")
@RequiredArgsConstructor
@Slf4j
public class WorkoutUIController {

    private final RestTemplate restTemplate;

    @Value("${workout.gateway.url}")
    private String workoutGatewayUrl;

    @GetMapping
    public String index(Model model) {
        try {
            ResponseEntity<List<WorkoutResponse>> response = restTemplate.exchange(
                    workoutGatewayUrl + "/workouts", HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {
                    });
            ResponseEntity<List<User>> userResponse = restTemplate.exchange(
                    workoutGatewayUrl + "/users", HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {
                    });
            List<User> users = userResponse.getBody();
            model.addAttribute("users", users);
            List<WorkoutResponse> workouts = response.getBody();
            model.addAttribute("workouts", workouts);
            model.addAttribute("user", new User());
        } catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            model.addAttribute("problemDetail", problemDetail);
        }
        return "workouts-list";
    }

    @GetMapping("/list")
    public String listWorkouts(
            @RequestParam(required = false) Long userId,
            Model model) {
        try {
            String url = workoutGatewayUrl + "/workouts";
            if (userId != null) {
                url += "/user/" + userId;
            }
            ResponseEntity<List<WorkoutResponse>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );
            model.addAttribute("workouts", response.getBody());

            if (userId != null) {
                ResponseEntity<User> userResponse = restTemplate.exchange(
                        workoutGatewayUrl + "/users/" + userId, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
                User user = userResponse.getBody();
                model.addAttribute("user", user);
            } else {
                model.addAttribute("user", new User());
            }

            ResponseEntity<List<User>> usersResponse = restTemplate.exchange(
                    workoutGatewayUrl + "/users", HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {
                    });
            List<User> users = usersResponse.getBody();
            model.addAttribute("users", users);
        } catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            model.addAttribute("problemDetail", problemDetail);
        }
        return "workouts-list";
    }

    @GetMapping("/start")
    public String startWorkoutForm(Model model) {
        // Pobierz listę użytkowników
        try {
            ResponseEntity<List<User>> response = restTemplate.exchange(
                    workoutGatewayUrl + "/users", HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {
                    });
            List<User> users = response.getBody();
            model.addAttribute("users", users);
        } catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            model.addAttribute("problemDetail", problemDetail);
        }
        model.addAttribute("startWorkoutRequest", new StartWorkoutRequest());
        return "start-workout";
    }

    @PostMapping("/start")
    public String startWorkout(StartWorkoutRequest startWorkoutRequest, Model model) {
        try {
            ResponseEntity<WorkoutResponse> response = restTemplate.postForEntity(
                    workoutGatewayUrl + "/workouts/start", startWorkoutRequest,
                    WorkoutResponse.class);
            Long workoutId = response.getBody().workout_id();
            return "redirect:/workouts/status/" + workoutId;
        } catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            model.addAttribute("problemDetail", problemDetail);
        }
        model.addAttribute("startWorkoutRequest", startWorkoutRequest);
        return "start-workout";
    }

    @GetMapping("/status/{workoutId}")
    public String status(@PathVariable Long workoutId, Model model) {
        try {
            // Wysyłanie żądania do serwisu REST
            ResponseEntity<WorkoutResponse> response = restTemplate.getForEntity(
                    workoutGatewayUrl + "/workouts/" + workoutId,
                    WorkoutResponse.class);

            WorkoutResponse workoutResponse = response.getBody();

            String status = (workoutResponse.endDate() != null) ? "Completed" : "In progress";
            String statusColor = getStatusColorMap().get(status);

            model.addAttribute("workoutResponse", workoutResponse);
            model.addAttribute("status", status);
            model.addAttribute("statusColor", statusColor);
        } catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            model.addAttribute("problemDetail", problemDetail);
            return "start-workout";
        }
        return "workout-status";
    }

    @PostMapping("/end")
    public String endWorkout(EndWorkoutRequest endWorkoutRequest, Model model) {
        try {
            restTemplate.postForEntity(
                    workoutGatewayUrl + "/workouts/end", endWorkoutRequest,
                    Void.class);
            return "redirect:/workouts/status/" + endWorkoutRequest.getWorkoutId();
        } catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            model.addAttribute("problemDetail", problemDetail);
        }
        model.addAttribute("endWorkoutRequest", endWorkoutRequest);
        return "end-workout";
    }

    @PostMapping("/{workoutId}")
    public String deleteWorkout(@PathVariable Long workoutId, Model model) {
        try {
            restTemplate.exchange(
                    workoutGatewayUrl + "/workouts/{workoutId}",
                    HttpMethod.DELETE,
                    null,
                    Void.class,
                    workoutId
            );
            return "redirect:/workouts/list";  // Po usunięciu przekierowanie do listy
        } catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            model.addAttribute("problemDetail", problemDetail);
            return "workouts-list";  // W przypadku błędu, wyświetl listę z komunikatem
        }
    }

    @GetMapping("/create-user")
    public String createUserForm(Model model) {
        model.addAttribute("newUser", new User());
        return "create-user";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute User newUser, Model model) {
        try {
            restTemplate.postForEntity(workoutGatewayUrl + "/users", newUser, User.class);
            return "redirect:/workouts"; // Po utworzeniu użytkownika przekierowanie na stronę z listą treningów
        } catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            model.addAttribute("problemDetail", problemDetail);
        }
        model.addAttribute("newUser", newUser);
        return "create-user";
    }

    public Map<String, String> getStatusColorMap() {
        return Map.of(
                "In progress", "text-primary",
                "Completed", "text-success"
        );
    }
}