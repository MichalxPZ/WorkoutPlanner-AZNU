package put.poznan.pl.michalxpz.workoutui.model;


public class WorkoutPlanRequest {
    private String name;
    private String description;
    private String type;
    private String difficulty;
    private String duration;
    private String equipment;
    private String muscle;
    private String author;

    public WorkoutPlanRequest() {
    }

    public WorkoutPlanRequest(String name, String description, String type, String difficulty, String duration, String equipment, String muscle, String author) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.difficulty = difficulty;
        this.duration = duration;
        this.equipment = equipment;
        this.muscle = muscle;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
