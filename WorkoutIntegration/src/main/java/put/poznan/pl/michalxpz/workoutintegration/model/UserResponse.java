package put.poznan.pl.michalxpz.workoutintegration.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserResponse {
    private Long userId;
    String name;
    String surname;
    String email;
}
