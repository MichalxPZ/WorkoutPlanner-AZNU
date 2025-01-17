package put.poznan.pl.michalxpz.workoutintegration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class WorkoutEndRequest {
    private Long workoutId;
    private String endDate;
    private Long duration;
}
