package put.poznan.pl.michalxpz.workoutplannersoapclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import put.poznan.pl.michalxpz.workoutplannersoap.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WorkoutPlannerController {
    private final WorkoutSoapService workoutSoapService;

    @GetMapping("/addWorkout")
    public String addWorkoutForm(Model model) {
        model.addAttribute("addWorkoutSOAPRequest", new AddWorkoutSOAPRequest());
        model.addAttribute("difficulties", workoutSoapService.getDifficulties());
        return "addWorkoutForm";
    }

    @PostMapping("/addWorkout")
    public String addWorkout(@ModelAttribute AddWorkoutSOAPRequest workout, Model model) {
        try {
            WorkoutSOAPResponse response = workoutSoapService.addWorkout(workout);
            model.addAttribute("workoutSOAPResponse", response);
            return "result";
        } catch (WorkoutSOAPException_Exception e) {
            log.error("e: ", e);
            model.addAttribute("workoutFaultMsg", e);
            return "fault";
        }
    }

    @GetMapping("/workouts")
    public String getAllWorkouts(Model model) {
        model.addAttribute("workouts", workoutSoapService.getAllWorkouts());
        model.addAttribute("difficulties", workoutSoapService.getDifficulties());
        return "workouts";
    }

    @GetMapping("/workout/{id}")
    public String getWorkoutById(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("workout", workoutSoapService.getWorkoutById(id));
            model.addAttribute("difficulties", workoutSoapService.getDifficulties());
            return "workout";
        } catch (WorkoutSOAPException_Exception e) {
            log.error("Workout not found", e);
            model.addAttribute("error", "Workout not found");
            return "error";
        }
    }

    @GetMapping("/workouts/filter")
    public String filterWorkouts(@RequestParam(required = false) String author,
                                 @RequestParam(required = false) String type,
                                 @RequestParam(required = false) String difficulty,
                                 Model model) {
        model.addAttribute("difficulties", workoutSoapService.getDifficulties());

        if (!author.isEmpty()) {
            model.addAttribute("workouts", workoutSoapService.getWorkoutsByAuthor(author));
        } else if (!type.isEmpty()) {
            model.addAttribute("workouts", workoutSoapService.getWorkoutsByType(type));
        } else if (!difficulty.isEmpty()) {
            model.addAttribute("workouts", workoutSoapService.getWorkoutsByDifficulty(difficulty));
        } else {
            model.addAttribute("workouts", workoutSoapService.getAllWorkouts());
        }

        return "workouts";
    }

    @PostMapping("/workout/delete/{id}")
    public String deleteWorkout(@PathVariable Long id) {
        workoutSoapService.deleteWorkout(id);
        return "redirect:/workouts";
    }

    @PostMapping("/workout/deleteAll")
    public String deleteAllWorkouts() {
        workoutSoapService.deleteAllWorkouts();
        return "redirect:/workouts";
    }
}