package put.poznan.pl.michalxpz.workoutplannerrestservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workout_id;
    Date startDate = new Date();
    @Nullable
    Date endDate = null;

    @Nullable
    @Column(nullable = true)
    Long duration = null;
    String name;
    String description;

    @Enumerated(EnumType.STRING)
    private WorkoutState state = WorkoutState.PLANNED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
