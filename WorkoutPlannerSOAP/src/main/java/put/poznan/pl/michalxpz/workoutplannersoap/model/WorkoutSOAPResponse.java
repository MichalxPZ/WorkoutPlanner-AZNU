package put.poznan.pl.michalxpz.workoutplannersoap.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkoutSOAPResponse {
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
