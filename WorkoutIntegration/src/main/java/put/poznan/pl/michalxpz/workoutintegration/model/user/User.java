package put.poznan.pl.michalxpz.workoutintegration.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class User {
    String name;
    String surname;
    String email;
}
