package put.poznan.pl.michalxpz.workoutui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import put.poznan.pl.michalxpz.workoutui.model.WorkoutPlanRequest;
import put.poznan.pl.michalxpz.workoutui.model.WorkoutPlanResponse;

@Controller
@RequestMapping("/plans")
public class WorkoutPlanController {
    private final WorkoutRestService workoutService;

    public WorkoutPlanController(WorkoutRestService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public String listWorkoutPlans(Model model) {
        model.addAttribute("plans", workoutService.getAllWorkoutPlans().getWorkoutPlanList());
        return "plans/list";
    }

    @GetMapping("/add")
    public String addWorkoutPlanForm(Model model) {
        model.addAttribute("workoutPlan", new WorkoutPlanRequest());
        model.addAttribute("users", workoutService.getUsers());
        return "plans/add";
    }

    @PostMapping("/add")
    public String addWorkoutPlan(@ModelAttribute WorkoutPlanRequest request, Model model) {
        WorkoutPlanResponse plan = workoutService.addWorkoutPlan(request);
        model.addAttribute("plan", plan);
        return "redirect:/plans";
    }
}