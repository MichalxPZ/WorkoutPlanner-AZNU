package put.poznan.pl.michalxpz.workoutplannerrestservice.workouts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class WorkoutStateScheduler {
    private final WorkoutService workoutService;

    public WorkoutStateScheduler(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @Scheduled(fixedRate = 60000) // Run every minute
    public void updateWorkoutStates() {
        log.info("Updating workout states");
        workoutService.updateWorkoutStates();
        log.info("Workout states updated");
    }
}
