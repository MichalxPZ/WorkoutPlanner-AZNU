package put.poznan.pl.michalxpz.workoutintegration.model.workout.state;

public class WorkoutStateException extends Exception {

    public WorkoutStateException() {
    }

    public WorkoutStateException(String message) {
        super(message);
    }

    public WorkoutStateException(Throwable cause) {
        super(cause);
    }

    public WorkoutStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkoutStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
