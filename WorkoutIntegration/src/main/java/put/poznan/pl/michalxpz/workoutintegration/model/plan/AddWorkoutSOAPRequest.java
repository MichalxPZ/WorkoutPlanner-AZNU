package put.poznan.pl.michalxpz.workoutintegration.model.plan;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AddWorkoutSOAPRequest {
    private String name;
    private String description;
    private String type;
    private String difficulty;
    private String duration;
    private String equipment;
    private String muscle;
    private String author;
}