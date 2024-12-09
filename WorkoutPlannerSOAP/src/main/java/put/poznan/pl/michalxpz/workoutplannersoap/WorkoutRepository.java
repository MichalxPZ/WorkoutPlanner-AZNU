package put.poznan.pl.michalxpz.workoutplannersoap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import put.poznan.pl.michalxpz.workoutplannersoap.model.WorkoutDifficulty;
import put.poznan.pl.michalxpz.workoutplannersoap.model.WorkoutModel;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutModel, Long> {

    @Query("SELECT w FROM workout w WHERE w.author ILIKE :author")
    public List<WorkoutModel> findAllByAuthor(String author);

    @Query("SELECT w FROM workout w WHERE w.name ILIKE :name")
    public List<WorkoutModel> findAllByName(String name);

    @Query("SELECT w FROM workout w WHERE w.type ILIKE :type")
    public List<WorkoutModel> findAllByType(String type);

    @Query("SELECT w FROM workout w WHERE w.difficulty = :difficulty")
    public List<WorkoutModel> findAllByDifficulty(WorkoutDifficulty difficulty);

    @Query("SELECT w FROM workout w WHERE w.equipment ILIKE :equipment")
    public List<WorkoutModel> findAllByEquipment(String equipment);

    @Query("SELECT w FROM workout w WHERE w.muscle ILIKE :muscle")
    public List<WorkoutModel> findAllByMuscle(String muscle);
}
