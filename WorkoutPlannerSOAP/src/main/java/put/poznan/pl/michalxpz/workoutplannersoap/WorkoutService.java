package put.poznan.pl.michalxpz.workoutplannersoap;


import jakarta.jws.WebService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import put.poznan.pl.michalxpz.workoutplannersoap.mappers.WorkoutModelSoapMapper;
import put.poznan.pl.michalxpz.workoutplannersoap.model.*;

import java.util.ArrayList;
import java.util.List;

@WebService
@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutSOAPResponse getWorkoutById(Long id) throws WorkoutSOAPException {
        WorkoutModel workout = workoutRepository.findById(id).orElse(null);
        if (workout == null) {
            throw new WorkoutSOAPException(id, "Workout not found");
        }
        return WorkoutModelSoapMapper.mapWorkoutModelToWorkoutSOAPResponse(workout);
    }

    @Transactional
    public WorkoutSOAPResponse addWorkout(AddWorkoutSOAPRequest addWorkoutToSoapModel) throws WorkoutSOAPException {
        WorkoutModel workoutModel = WorkoutModelSoapMapper.mapAddWorkoutSOAPRequestToWorkoutModel(addWorkoutToSoapModel);
        try {
            WorkoutModel saveWorkout = workoutRepository.save(workoutModel);
            return WorkoutModelSoapMapper.mapWorkoutModelToWorkoutSOAPResponse(saveWorkout);
        } catch (DataIntegrityViolationException e) {
            throw new WorkoutSOAPException(workoutModel.getId(), "Workout already exists", e);
        }
    }

    @Transactional
    public WorkoutSOAPResponse updateWorkout(UpdateWorkoutSOAPRequest updateWorkoutSOAPRequest) throws WorkoutSOAPException {
        WorkoutModel workoutModel = WorkoutModelSoapMapper.mapUpdateWorkoutSOAPRequestToWorkoutModel(updateWorkoutSOAPRequest);
        try {
            WorkoutModel saveWorkout = workoutRepository.save(workoutModel);
            return WorkoutModelSoapMapper.mapWorkoutModelToWorkoutSOAPResponse(saveWorkout);
        } catch (DataIntegrityViolationException e) {
            throw new WorkoutSOAPException(workoutModel.getId(), "Cannot update workout", e);
        }
    }
    @Transactional
    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllWorkouts() {
        workoutRepository.deleteAll();
    }

    public List<WorkoutSOAPResponse> getAllWorkouts() {
        List<WorkoutModel> workouts = workoutRepository.findAll();
        List<WorkoutSOAPResponse> workoutSOAPResponses = new ArrayList<>();
        for (WorkoutModel workout : workouts) {
            workoutSOAPResponses.add(WorkoutModelSoapMapper.mapWorkoutModelToWorkoutSOAPResponse(workout));
        }
        return workoutSOAPResponses;
    }

    public List<WorkoutSOAPResponse> getWorkoutsByAuthor(String author) {
        List<WorkoutModel> workouts = workoutRepository.findAllByAuthor(author);
        List<WorkoutSOAPResponse> workoutSOAPResponses = new ArrayList<>();
        for (WorkoutModel workout : workouts) {
            workoutSOAPResponses.add(WorkoutModelSoapMapper.mapWorkoutModelToWorkoutSOAPResponse(workout));
        }
        return workoutSOAPResponses;
    }

    public List<WorkoutSOAPResponse> getWorkoutsByType(String type) {
        List<WorkoutModel> workouts = workoutRepository.findAllByType(type);
        List<WorkoutSOAPResponse> workoutSOAPResponses = new ArrayList<>();
        for (WorkoutModel workout : workouts) {
            workoutSOAPResponses.add(WorkoutModelSoapMapper.mapWorkoutModelToWorkoutSOAPResponse(workout));
        }
        return workoutSOAPResponses;
    }

    public List<WorkoutSOAPResponse> getWorkoutsByDifficulty(String difficulty) {
        List<WorkoutModel> workouts = workoutRepository.findAllByDifficulty(difficulty);
        List<WorkoutSOAPResponse> workoutSOAPResponses = new ArrayList<>();
        for (WorkoutModel workout : workouts) {
            workoutSOAPResponses.add(WorkoutModelSoapMapper.mapWorkoutModelToWorkoutSOAPResponse(workout));
        }
        return workoutSOAPResponses;
    }

    public List<WorkoutSOAPResponse> getWorkoutsByEquipment(String equipment) {
        List<WorkoutModel> workouts = workoutRepository.findAllByEquipment(equipment);
        List<WorkoutSOAPResponse> workoutSOAPResponses = new ArrayList<>();
        for (WorkoutModel workout : workouts) {
            workoutSOAPResponses.add(WorkoutModelSoapMapper.mapWorkoutModelToWorkoutSOAPResponse(workout));
        }
        return workoutSOAPResponses;
    }

    public List<WorkoutSOAPResponse> getWorkoutsByMuscle(String muscle) {
        List<WorkoutModel> workouts = workoutRepository.findAllByMuscle(muscle);
        List<WorkoutSOAPResponse> workoutSOAPResponses = new ArrayList<>();
        for (WorkoutModel workout : workouts) {
            workoutSOAPResponses.add(WorkoutModelSoapMapper.mapWorkoutModelToWorkoutSOAPResponse(workout));
        }
        return workoutSOAPResponses;
    }
}