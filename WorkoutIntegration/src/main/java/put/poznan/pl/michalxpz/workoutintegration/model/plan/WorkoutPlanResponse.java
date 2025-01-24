package put.poznan.pl.michalxpz.workoutintegration.model.plan;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class WorkoutPlanResponse {
    private Long id;
    private String name;
    private String description;
    private String type;
    private String difficulty;
    private String duration;
    private String equipment;
    private String muscle;
    private String author;
    private String date;
    private String comments;
    private String likes;
}

