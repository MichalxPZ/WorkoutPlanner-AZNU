package put.poznan.pl.michalxpz.workoutplannerrestclient.model;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Workout {
    private Long workout_id;
    Date startDate = new Date();
    @Nullable
    Date endDate = null;

    @Nullable
    Long duration = null;
    String name;
    String description;

    private User user;
}
