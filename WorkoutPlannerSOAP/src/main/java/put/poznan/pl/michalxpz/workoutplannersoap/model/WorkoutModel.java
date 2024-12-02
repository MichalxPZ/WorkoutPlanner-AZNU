package put.poznan.pl.michalxpz.workoutplannersoap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "workout")
@Getter
@Setter
@Slf4j
public class WorkoutModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String type;
    private WorkoutDifficulty difficulty;
    private String duration;
    private String equipment;
    private String muscle;
    private String author;
    private String date;
    private String comments;
    private String likes;
}
