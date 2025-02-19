package put.poznan.pl.michalxpz.workoutplannerrestservice.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import put.poznan.pl.michalxpz.workoutplannerrestservice.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBySurname(String surname);

    Optional<User> findByUserId(Long userId);
}