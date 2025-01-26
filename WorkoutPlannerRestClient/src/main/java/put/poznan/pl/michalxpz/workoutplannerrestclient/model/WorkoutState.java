package put.poznan.pl.michalxpz.workoutplannerrestclient.model;

public enum WorkoutState {
    PLANNED,      // Start date is in the future
    IN_PROGRESS,  // Started but not finished
    FINISHED,     // Completed
    CANCELED      // Manually canceled
}