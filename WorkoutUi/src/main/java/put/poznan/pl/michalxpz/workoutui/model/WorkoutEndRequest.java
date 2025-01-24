package put.poznan.pl.michalxpz.workoutui.model;

public class WorkoutEndRequest {
    private String workoutId;
    private String endDate;

    public WorkoutEndRequest() {
    }

    public WorkoutEndRequest(String workoutId, String endDate) {
        this.workoutId = workoutId;
        this.endDate = endDate;
    }

    public String getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(String workoutId) {
        this.workoutId = workoutId;
    }

    public String getendDate() {
        return endDate;
    }

    public void setendDate(String endDate) {
        this.endDate = endDate;
    }
}
