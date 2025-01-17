package put.poznan.pl.michalxpz.workoutplannerrestservice.workouts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import put.poznan.pl.michalxpz.workoutplannerrestservice.model.User;
import put.poznan.pl.michalxpz.workoutplannerrestservice.model.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    default Workout[] findWorkoutsByUser(User user) {
        return this.findAll().stream().filter(workout -> workout.getUser().equals(user)).toArray(Workout[]::new);
    }
}
