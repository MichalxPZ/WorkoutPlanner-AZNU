package put.poznan.pl.michalxpz.workoutui.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class WorkoutListResponse {
    @JsonAlias("workoutList")
    private List<WorkoutResponse> workouts = new ArrayList<>();

    public List<WorkoutResponse> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutResponse> workouts) {
        this.workouts = workouts;
    }
}
