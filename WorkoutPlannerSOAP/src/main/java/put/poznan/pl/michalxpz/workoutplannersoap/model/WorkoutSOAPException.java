package put.poznan.pl.michalxpz.workoutplannersoap.model;

import lombok.Getter;

@Getter
public class WorkoutSOAPException extends Exception {
    private final Long workoutId;

    public WorkoutSOAPException(Long workoutId, String message) {
        super(message);
        this.workoutId = workoutId;
    }

    public WorkoutSOAPException(Long workoutId, String message, Throwable cause) {
        super(message, cause);
        this.workoutId = workoutId;
    }

}