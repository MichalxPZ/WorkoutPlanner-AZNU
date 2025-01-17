package put.poznan.pl.michalxpz.workoutintegration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class WorkoutResponse {
    private Long workout_id;
    private String startDate;
    private String endDate;
    private Long duration;
    private String name;
    private String description;
}
