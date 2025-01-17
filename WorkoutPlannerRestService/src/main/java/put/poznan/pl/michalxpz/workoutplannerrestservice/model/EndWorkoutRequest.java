package put.poznan.pl.michalxpz.workoutplannerrestservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EndWorkoutRequest {
    Long workoutId;
    Date endDate;
    Long duration;
}
