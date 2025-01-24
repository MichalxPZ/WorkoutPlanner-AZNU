package put.poznan.pl.michalxpz.workoutplannersoapclient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import put.poznan.pl.michalxpz.workoutplannersoap.WorkoutSOAPResponse;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class WorkoutPlanListResponse {
    List<WorkoutSOAPResponse> workoutPlanList;
}
