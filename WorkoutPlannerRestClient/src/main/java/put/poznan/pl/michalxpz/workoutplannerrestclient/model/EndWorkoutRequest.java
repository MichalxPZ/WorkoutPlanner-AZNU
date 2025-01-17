package put.poznan.pl.michalxpz.workoutplannerrestclient.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EndWorkoutRequest {
    Long workoutId;
    String endDate;
    Long duration;
}
