package put.poznan.pl.michalxpz.workoutplannerrestclient.model;

import java.util.Date;

public record WorkoutResponse(
        Long workout_id,
        Date startDate,
        Date endDate,
        Long duration,
        String name,
        String description,
        String status
) {
}