package put.poznan.pl.michalxpz.workoutplannerrestservice.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkoutMapper {

    public static Workout mapStartWorkout(StartWorkoutRequest startWorkoutRequest) {
        Workout workout = new Workout();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date startDate = dateFormat.parse(startWorkoutRequest.getStartDate());
            workout.setStartDate(startDate);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
        workout.setName(startWorkoutRequest.getWorkoutName());
        workout.setDescription(startWorkoutRequest.getWorkoutDescription());
        return workout;
    }
}
