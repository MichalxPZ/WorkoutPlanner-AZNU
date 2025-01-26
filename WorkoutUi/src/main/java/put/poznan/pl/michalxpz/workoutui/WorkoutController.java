package put.poznan.pl.michalxpz.workoutui;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import put.poznan.pl.michalxpz.workoutui.model.StartWorkoutRequest;
import put.poznan.pl.michalxpz.workoutui.model.WorkoutEndRequest;
import put.poznan.pl.michalxpz.workoutui.model.WorkoutResponse;

import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutRestService workoutRestService;

    public WorkoutController(WorkoutRestService workoutRestService) {
        this.workoutRestService = workoutRestService;
    }

    @GetMapping
    public String listWorkouts(Model model) throws JsonProcessingException {
        List<WorkoutResponse> workouts = workoutRestService.getAllWorkouts();
        model.addAttribute("workouts", workouts);
        log.info("Workouts: {}", workouts);
      return "workouts/list";
    }

    @GetMapping("/start")
    public String startWorkoutForm(Model model) {
        model.addAttribute("workoutStart", new StartWorkoutRequest());
        model.addAttribute("users", workoutRestService.getUsers());
        model.addAttribute("plans", workoutRestService.getAllWorkoutPlans().getWorkoutPlanList());
        return "workouts/start";
    }

    @PostMapping("/start")
    public String startWorkout(@ModelAttribute StartWorkoutRequest request, Model model) {
        // Wywołanie usługi do uruchomienia treningu
        WorkoutResponse workout = workoutRestService.startWorkout(request);

        // Przekazanie danych do widoku
        model.addAttribute("workout", workout);
        return "redirect:/workouts/detail/" + workout.getWorkoutId();
    }

    @PostMapping("/end")
    public String endWorkout(@ModelAttribute WorkoutEndRequest request, Model model) {
        WorkoutResponse workout = workoutRestService.endWorkout(request);
        model.addAttribute("workout", workout);
        return "redirect:/workouts/detail/" + workout.getWorkoutId();
    }

    @GetMapping("/delete/{workoutId}")
    public String deleteWorkout(@PathVariable String workoutId) {
        workoutRestService.deleteWorkout(workoutId);
        return "redirect:/workouts";
    }

    @GetMapping("/detail/{workoutId}")
    public String workoutDetail(@PathVariable int workoutId, Model model) throws JsonProcessingException {
        WorkoutResponse workout = workoutRestService.getWorkoutById(workoutId);
        model.addAttribute("workout", workout);
        return "workouts/detail";
    }
}