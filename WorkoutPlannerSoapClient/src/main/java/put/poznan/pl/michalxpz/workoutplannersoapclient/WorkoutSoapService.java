package put.poznan.pl.michalxpz.workoutplannersoapclient;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import put.poznan.pl.michalxpz.workoutplannersoap.*;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.List;

@Slf4j
@Service
@Getter
@Setter
public class WorkoutSoapService {
    private static final QName SERVICE_NAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "WorkoutServiceService");
    private final URL wsdlURL = WorkoutServiceService.WSDL_LOCATION;
    private final List<String> difficulties = List.of("BEGINNER", "INTERMEDIATE", "ADVANCED", "EXPERT");

    private WorkoutService getPort() {
        WorkoutServiceService ss = new WorkoutServiceService(wsdlURL, SERVICE_NAME);
        return ss.getWorkoutServicePort();
    }

    public WorkoutSOAPResponse addWorkout(AddWorkoutSOAPRequest workout) throws WorkoutSOAPException_Exception {
        WorkoutService port = getPort();
        return port.addWorkout(workout);
    }

    public List<WorkoutSOAPResponse> getAllWorkouts() {
        WorkoutService port = getPort();
        return port.getAllWorkouts();
    }

    public WorkoutSOAPResponse getWorkoutById(Long id) throws WorkoutSOAPException_Exception {
        WorkoutService port = getPort();
        return port.getWorkoutById(id);
    }

    public List<WorkoutSOAPResponse> getWorkoutsByAuthor(String author) {
        WorkoutService port = getPort();
        return port.getWorkoutsByAuthor(author);
    }

    public List<WorkoutSOAPResponse> getWorkoutsByType(String type) {
        WorkoutService port = getPort();
        return port.getWorkoutsByType(type);
    }

    public List<WorkoutSOAPResponse> getWorkoutsByDifficulty(String difficulty) {
        WorkoutService port = getPort();
        return port.getWorkoutsByDifficulty(difficulty);
    }

    public void deleteWorkout(Long id) {
        WorkoutService port = getPort();
        port.deleteWorkout(id);
    }

    public void deleteAllWorkouts() {
        WorkoutService port = getPort();
        port.deleteAllWorkouts();
    }
}