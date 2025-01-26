package put.poznan.pl.michalxpz.workoutplannerrestservice.workouts;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.pl.michalxpz.workoutplannerrestservice.model.*;

import java.util.List;

@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping("/start")
    public ResponseEntity<Workout> startWorkout(@RequestBody StartWorkoutRequest startWorkoutRequest) {
        return ResponseEntity.ok(workoutService.startWorkout(startWorkoutRequest));
    }

    @PostMapping("/end")
    public ResponseEntity<Workout> endWorkout(@RequestBody EndWorkoutRequest endWorkoutRequest) {
        return ResponseEntity.ok(workoutService.endWorkout(endWorkoutRequest));
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getWorkouts() {
        return ResponseEntity.ok(workoutService.getWorkouts());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Workout>> getWorkoutsByUser(@PathVariable Long id) {
        return ResponseEntity.ok(workoutService.getWorkoutsByUser(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        Workout workout = workoutService.getWorkoutById(id);
        if (workout == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(workout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeWorkout(@PathVariable Long id) {
        workoutService.removeWorkout(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Workout> updateWorkout(@RequestBody Workout workout) {
        return ResponseEntity.ok(workoutService.updateWorkout(workout));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Workout> cancelWorkout(@PathVariable Long id) {
        Workout workout = workoutService.updateWorkoutState(id, WorkoutState.CANCELED);
        return ResponseEntity.ok(workout);
    }
}