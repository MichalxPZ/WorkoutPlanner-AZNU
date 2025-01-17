package put.poznan.pl.michalxpz.workoutintegration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class WorkoutStartRequest {
    private User user;
    private String startDate;
    private String workoutName;
    private String workoutDescription;
}
