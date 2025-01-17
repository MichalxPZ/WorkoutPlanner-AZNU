package put.poznan.pl.michalxpz.workoutplannerrestclient.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StartWorkoutRequest {
    private User user;
    private String startDate;
    private String workoutName;
    private String workoutDescription;
}
