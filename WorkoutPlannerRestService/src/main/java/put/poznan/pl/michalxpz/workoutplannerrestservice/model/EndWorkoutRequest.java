package put.poznan.pl.michalxpz.workoutplannerrestservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EndWorkoutRequest {
    Long workoutId;
    String endDate;
    Long duration;
}
