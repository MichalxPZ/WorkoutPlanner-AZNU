package put.poznan.pl.michalxpz.workoutintegration.model.workout;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class WorkoutListResponse {
    List<WorkoutResponse> workoutList;
}
