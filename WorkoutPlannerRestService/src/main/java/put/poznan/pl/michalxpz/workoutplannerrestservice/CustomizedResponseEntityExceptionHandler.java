package put.poznan.pl.michalxpz.workoutplannerrestservice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import put.poznan.pl.michalxpz.workoutplannerrestservice.model.WorkoutException;

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WorkoutException.class)
    public final void handleWorkoutException(WorkoutException ex) {
        System.out.println("WorkoutException: " + ex.getMessage());
    }
}
