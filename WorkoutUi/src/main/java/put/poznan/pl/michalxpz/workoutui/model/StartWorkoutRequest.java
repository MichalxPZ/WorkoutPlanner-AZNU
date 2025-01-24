package put.poznan.pl.michalxpz.workoutui.model;

public class StartWorkoutRequest {
    private User user;
    private String startDate;
    private String workoutName;
    private String workoutDescription;

    // Default constructor
    public StartWorkoutRequest() {
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    // toString method
    @Override
    public String toString() {
        return "StartWorkoutRequest{" +
                "user=" + user +
                ", startDate='" + startDate + '\'' +
                ", workoutName='" + workoutName + '\'' +
                ", workoutDescription='" + workoutDescription + '\'' +
                '}';
    }
}