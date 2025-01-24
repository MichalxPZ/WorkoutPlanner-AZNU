package put.poznan.pl.michalxpz.workoutintegration.model.plan;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class WorkoutPlanListReponse {
    List<WorkoutPlanResponse> workoutPlanList;
}
