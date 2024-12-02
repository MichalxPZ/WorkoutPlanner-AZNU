package put.poznan.pl.michalxpz.workoutplannersoap.mappers;

import put.poznan.pl.michalxpz.workoutplannersoap.model.*;

public class WorkoutModelSoapMapper {

    public static WorkoutModel mapAddWorkoutSOAPRequestToWorkoutModel(AddWorkoutSOAPRequest addWorkoutSOAPRequest) {
        WorkoutModel workoutModel = new WorkoutModel();
        workoutModel.setName(addWorkoutSOAPRequest.getName());
        workoutModel.setDescription(addWorkoutSOAPRequest.getDescription());
        workoutModel.setType(addWorkoutSOAPRequest.getType());
        workoutModel.setDifficulty(WorkoutDifficulty.valueOf(addWorkoutSOAPRequest.getDifficulty()));
        workoutModel.setDuration(addWorkoutSOAPRequest.getDuration());
        workoutModel.setEquipment(addWorkoutSOAPRequest.getEquipment());
        workoutModel.setMuscle(addWorkoutSOAPRequest.getMuscle());
        workoutModel.setAuthor(addWorkoutSOAPRequest.getAuthor());
        workoutModel.setDate(addWorkoutSOAPRequest.getDate());
        return workoutModel;
    }

    public static WorkoutModel mapUpdateWorkoutSOAPRequestToWorkoutModel(UpdateWorkoutSOAPRequest updateWorkoutSOAPRequest) {
        WorkoutModel workoutModel = new WorkoutModel();
        workoutModel.setId(updateWorkoutSOAPRequest.getId());
        workoutModel.setDescription(updateWorkoutSOAPRequest.getDescription());
        workoutModel.setType(updateWorkoutSOAPRequest.getType());
        workoutModel.setDifficulty(WorkoutDifficulty.valueOf(updateWorkoutSOAPRequest.getDifficulty()));
        workoutModel.setDuration(updateWorkoutSOAPRequest.getDuration());
        workoutModel.setEquipment(updateWorkoutSOAPRequest.getEquipment());
        workoutModel.setMuscle(updateWorkoutSOAPRequest.getMuscle());
        return workoutModel;
    }

    public static WorkoutSOAPResponse mapWorkoutModelToWorkoutSOAPResponse(WorkoutModel workoutModel) {
        WorkoutSOAPResponse workoutSOAPResponse = new WorkoutSOAPResponse();
        workoutSOAPResponse.setId(workoutModel.getId());
        workoutSOAPResponse.setName(workoutModel.getName());
        workoutSOAPResponse.setDescription(workoutModel.getDescription());
        workoutSOAPResponse.setType(workoutModel.getType());
        workoutSOAPResponse.setDifficulty(String.valueOf(workoutModel.getDifficulty()));
        workoutSOAPResponse.setDuration(workoutModel.getDuration());
        workoutSOAPResponse.setEquipment(workoutModel.getEquipment());
        workoutSOAPResponse.setMuscle(workoutModel.getMuscle());
        workoutSOAPResponse.setAuthor(workoutModel.getAuthor());
        workoutSOAPResponse.setDate(workoutModel.getDate());
        return workoutSOAPResponse;
    }
}
