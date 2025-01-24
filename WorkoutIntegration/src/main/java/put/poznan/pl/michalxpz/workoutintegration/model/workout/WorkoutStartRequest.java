package put.poznan.pl.michalxpz.workoutintegration.model.workout;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import put.poznan.pl.michalxpz.workoutintegration.model.user.UserResponse;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class WorkoutStartRequest {
    private UserResponse user;
    private String startDate;
    private String workoutName;
    private String workoutDescription;
}
