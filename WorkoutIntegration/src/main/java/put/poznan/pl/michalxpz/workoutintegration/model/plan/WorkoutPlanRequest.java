package put.poznan.pl.michalxpz.workoutintegration.model.plan;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class WorkoutPlanRequest {
    private Long workout_id;
    private Date startDate;
    private Date endDate;
    private Long duration;
    private String name;
    private String description;
    private String status;
}
