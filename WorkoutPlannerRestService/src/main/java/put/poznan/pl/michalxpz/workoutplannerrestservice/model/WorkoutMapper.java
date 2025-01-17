package put.poznan.pl.michalxpz.workoutplannerrestservice.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkoutMapper {

    public static Workout mapStartWorkout(StartWorkoutRequest startWorkoutRequest) {
        Workout workout = new Workout();
        String dateString = startWorkoutRequest.getStartDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");  // Format bez sekund
        SimpleDateFormat dateFormatWithSeconds = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date startDate = dateFormat.parse(dateString);
            workout.setStartDate(startDate);
        } catch (ParseException e) {
            try {
                Date startDate = dateFormatWithSeconds.parse(dateString);
                workout.setStartDate(startDate);
            } catch (ParseException e2) {
                throw new WorkoutException("Invalid date format");
            }
        }
        workout.setName(startWorkoutRequest.getWorkoutName());
        workout.setDescription(startWorkoutRequest.getWorkoutDescription());
        return workout;
    }
}
