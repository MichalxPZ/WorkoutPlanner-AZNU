package put.poznan.pl.michalxpz.workoutplannerrestclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import put.poznan.pl.michalxpz.workoutplannerrestclient.WorkoutService;
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
    private final WorkoutService workoutService;

    @GetMapping
    public String index(Model model) {
        try {
            List<WorkoutResponse> workouts = workoutService.getWorkouts();
            List<User> users = workoutService.getAllUsers();
            model.addAttribute("workouts", workouts);
            model.addAttribute("users", users);
            model.addAttribute("user", new User());
        } catch (HttpClientErrorException e) {
            handleError(e, model);
        }
        return "workouts-list";
    }

    @GetMapping("/list")
    public String listWorkouts(@RequestParam(required = false) Long userId, Model model) {
        try {
            List<WorkoutResponse> workouts = workoutService.getWorkoutsByUser(userId);
            List<User> users = workoutService.getAllUsers();
            model.addAttribute("workouts", workouts);
            model.addAttribute("users", users);

            if (userId != null) {
                User user = workoutService.getUser(userId);
                model.addAttribute("user", user);
            } else {
                model.addAttribute("user", new User());
            }
        } catch (HttpClientErrorException e) {
            handleError(e, model);
        }
        return "workouts-list";
    }

    @GetMapping("/start")
    public String startWorkoutForm(Model model) {
        try {
            List<User> users = workoutService.getAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("startWorkoutRequest", new StartWorkoutRequest());
        } catch (HttpClientErrorException e) {
            handleError(e, model);
        }
        return "start-workout";
    }

    @PostMapping("/start")
    public String startWorkout(StartWorkoutRequest startWorkoutRequest, Model model) {
        try {
            WorkoutResponse response = workoutService.startWorkout(startWorkoutRequest);
            return "redirect:/workouts/status/" + response.workout_id();
        } catch (HttpClientErrorException e) {
            handleError(e, model);
            model.addAttribute("startWorkoutRequest", startWorkoutRequest);
            return "start-workout";
        }
    }

    @GetMapping("/status/{workoutId}")
    public String status(@PathVariable Long workoutId, Model model) {
        try {
            WorkoutResponse workoutResponse = workoutService.getWorkoutStatus(workoutId);
            String status = (workoutResponse.endDate() != null) ? "Completed" : "In progress";
            String statusColor = getStatusColorMap().get(status);

            model.addAttribute("workoutResponse", workoutResponse);
            model.addAttribute("status", status);
            model.addAttribute("statusColor", statusColor);
            return "workout-status";
        } catch (HttpClientErrorException e) {
            handleError(e, model);
            return "start-workout";
        }
    }

    @PostMapping("/end")
    public String endWorkout(EndWorkoutRequest endWorkoutRequest, Model model) {
        try {
            workoutService.endWorkout(endWorkoutRequest);
            return "redirect:/workouts/status/" + endWorkoutRequest.getWorkoutId();
        } catch (HttpClientErrorException e) {
            handleError(e, model);
            model.addAttribute("endWorkoutRequest", endWorkoutRequest);
            return "end-workout";
        }
    }

    @PostMapping("/{workoutId}")
    public String deleteWorkout(@PathVariable Long workoutId, Model model) {
        try {
            workoutService.deleteWorkout(workoutId);
            return "redirect:/workouts/list";
        } catch (HttpClientErrorException e) {
            handleError(e, model);
            return "workouts-list";
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
            workoutService.createUser(newUser);
            return "redirect:/workouts";
        } catch (HttpClientErrorException e) {
            handleError(e, model);
            model.addAttribute("newUser", newUser);
            return "create-user";
        }
    }

    private void handleError(HttpClientErrorException e, Model model) {
        log.error(e.getResponseBodyAsString());
        ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
        model.addAttribute("problemDetail", problemDetail);
    }

    private Map<String, String> getStatusColorMap() {
        return Map.of(
                "In progress", "text-primary",
                "Completed", "text-success"
        );
    }
}