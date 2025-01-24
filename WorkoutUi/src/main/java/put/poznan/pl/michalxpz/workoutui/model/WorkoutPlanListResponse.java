package put.poznan.pl.michalxpz.workoutui.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class WorkoutPlanListResponse {
    private List<WorkoutPlanResponse> workoutPlanList;

    public WorkoutPlanListResponse(List<WorkoutPlanResponse> workoutPlanList) {
        this.workoutPlanList = workoutPlanList;
    }

    public List<WorkoutPlanResponse> getWorkoutPlanList() {
        return workoutPlanList;
    }

    public void setWorkoutPlanList(List<WorkoutPlanResponse> workoutPlanList) {
        this.workoutPlanList = workoutPlanList;
    }
}