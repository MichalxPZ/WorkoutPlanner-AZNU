package put.poznan.pl.michalxpz.workoutplannersoapclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import put.poznan.pl.michalxpz.workoutplannersoap.*;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.List;

@Slf4j
@Controller
public class WorkoutPlannerController {

    private static final QName SERVICE_NAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "WorkoutServiceService");
    URL wsdlURL = WorkoutServiceService.WSDL_LOCATION;
    List<String> difficulties = List.of("BEGINNER", "INTERMEDIATE", "ADVANCED", "EXPERT");

    @GetMapping("/addWorkout")
    public String addWorkoutForm(Model model) {
        model.addAttribute("addWorkoutSOAPRequest", new AddWorkoutSOAPRequest());
        model.addAttribute("difficulties", difficulties);
        return "addWorkoutForm";
    }

    @PostMapping("/addWorkout")
    public String addWorkout(@ModelAttribute AddWorkoutSOAPRequest workout, Model model) {
        WorkoutServiceService ss = new WorkoutServiceService(wsdlURL, SERVICE_NAME);
        WorkoutService port = ss.getWorkoutServicePort();
        System.out.println("Invoking addWorkout...");
        try {
            WorkoutSOAPResponse _addWorkout__return = port.addWorkout(workout);
            System.out.println("addWorkout.result=" + _addWorkout__return);
            model.addAttribute("workoutSOAPResponse", _addWorkout__return);
            return "result";
        } catch (WorkoutSOAPException_Exception e) {
            System.out.println("Expected exception: WorkoutSOAPException has occurred.");
            log.error("e: ", e);
            model.addAttribute("workoutFaultMsg", e);
            return "fault";
        }
    }

    @GetMapping("/workouts")
    public String getAllWorkouts(Model model) {
        WorkoutServiceService ss = new WorkoutServiceService(wsdlURL, SERVICE_NAME);
        WorkoutService port = ss.getWorkoutServicePort();
        model.addAttribute("workouts", port.getAllWorkouts());
        model.addAttribute("difficulties", difficulties);
        return "workouts";
    }

    @GetMapping("/workout/{id}")
    public String getWorkoutById(@PathVariable Long id, Model model) {
        WorkoutServiceService ss = new WorkoutServiceService(wsdlURL, SERVICE_NAME);
        WorkoutService port = ss.getWorkoutServicePort();
        try {
            model.addAttribute("workout", port.getWorkoutById(id));
            model.addAttribute("difficulties", difficulties);
            return "workout";
        } catch (WorkoutSOAPException_Exception e) {
            log.error("Workout not found", e);
            model.addAttribute("error", "Workout not found");
            return "error";
        }
    }

    @GetMapping("/workouts/filter")
    public String filterWorkouts(@RequestParam(required = false) String author, @RequestParam(required = false) String type, @RequestParam(required = false) String difficulty, Model model) {
        WorkoutServiceService ss = new WorkoutServiceService(wsdlURL, SERVICE_NAME);
        WorkoutService port = ss.getWorkoutServicePort();
        model.addAttribute("difficulties", difficulties);

        if (!author.isEmpty()) {
            model.addAttribute("workouts", port.getWorkoutsByAuthor(author));
        } else if (!type.isEmpty()) {
            model.addAttribute("workouts", port.getWorkoutsByType(type));
        } else if (!difficulty.isEmpty()) {
            model.addAttribute("workouts", port.getWorkoutsByDifficulty(difficulty));
        } else {
            model.addAttribute("workouts", port.getAllWorkouts());
        }

        return "workouts";
    }

    @PostMapping("/workout/delete/{id}")
    public String deleteWorkout(@PathVariable Long id, Model model) {
        WorkoutServiceService ss = new WorkoutServiceService(wsdlURL, SERVICE_NAME);
        WorkoutService port = ss.getWorkoutServicePort();
        port.deleteWorkout(id);
        return "redirect:/workouts";
    }

    @PostMapping("/workout/deleteAll")
    public String deleteAllWorkouts() {
        WorkoutServiceService ss = new WorkoutServiceService(wsdlURL, SERVICE_NAME);
        WorkoutService port = ss.getWorkoutServicePort();
        port.deleteAllWorkouts();
        return "redirect:/workouts";
    }
}
