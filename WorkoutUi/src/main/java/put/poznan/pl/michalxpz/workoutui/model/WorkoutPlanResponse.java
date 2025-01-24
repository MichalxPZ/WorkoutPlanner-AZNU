package put.poznan.pl.michalxpz.workoutui.model;

public class WorkoutPlanResponse {
    private Long id;
    private String name;
    private String description;
    private String type;
    private String difficulty;

    public WorkoutPlanResponse() {
    }

    public WorkoutPlanResponse(Long id, String name, String description, String type, String difficulty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
