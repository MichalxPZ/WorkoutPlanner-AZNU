package put.poznan.pl.michalxpz.workoutplannersoap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 4.0.5
 * 2025-01-26T16:21:16.783+01:00
 * Generated source version: 4.0.5
 *
 */
@WebService(targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "WorkoutService")
@XmlSeeAlso({ObjectFactory.class})
public interface WorkoutService {

    @WebMethod
    @RequestWrapper(localName = "deleteWorkout", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.DeleteWorkout")
    @ResponseWrapper(localName = "deleteWorkoutResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.DeleteWorkoutResponse")
    public void deleteWorkout(

        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0
    );

    @WebMethod
    @RequestWrapper(localName = "getAllWorkouts", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetAllWorkouts")
    @ResponseWrapper(localName = "getAllWorkoutsResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetAllWorkoutsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<WorkoutSOAPResponse> getAllWorkouts()
;

    @WebMethod
    @RequestWrapper(localName = "addWorkout", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.AddWorkout")
    @ResponseWrapper(localName = "addWorkoutResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.AddWorkoutResponse")
    @WebResult(name = "return", targetNamespace = "")
    public WorkoutSOAPResponse addWorkout(

        @WebParam(name = "arg0", targetNamespace = "")
        AddWorkoutSOAPRequest arg0
    ) throws WorkoutSOAPException_Exception;

    @WebMethod
    @RequestWrapper(localName = "getWorkoutsByMuscle", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutsByMuscle")
    @ResponseWrapper(localName = "getWorkoutsByMuscleResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutsByMuscleResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<WorkoutSOAPResponse> getWorkoutsByMuscle(

        @WebParam(name = "arg0", targetNamespace = "")
        String arg0
    );

    @WebMethod
    @RequestWrapper(localName = "updateWorkout", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.UpdateWorkout")
    @ResponseWrapper(localName = "updateWorkoutResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.UpdateWorkoutResponse")
    @WebResult(name = "return", targetNamespace = "")
    public WorkoutSOAPResponse updateWorkout(

        @WebParam(name = "arg0", targetNamespace = "")
        UpdateWorkoutSOAPRequest arg0
    ) throws WorkoutSOAPException_Exception;

    @WebMethod
    @RequestWrapper(localName = "deleteAllWorkouts", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.DeleteAllWorkouts")
    @ResponseWrapper(localName = "deleteAllWorkoutsResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.DeleteAllWorkoutsResponse")
    public void deleteAllWorkouts()
;

    @WebMethod
    @RequestWrapper(localName = "getWorkoutById", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutById")
    @ResponseWrapper(localName = "getWorkoutByIdResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutByIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public WorkoutSOAPResponse getWorkoutById(

        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0
    ) throws WorkoutSOAPException_Exception;

    @WebMethod
    @RequestWrapper(localName = "getWorkoutsByEquipment", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutsByEquipment")
    @ResponseWrapper(localName = "getWorkoutsByEquipmentResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutsByEquipmentResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<WorkoutSOAPResponse> getWorkoutsByEquipment(

        @WebParam(name = "arg0", targetNamespace = "")
        String arg0
    );

    @WebMethod
    @RequestWrapper(localName = "getWorkoutsByAuthor", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutsByAuthor")
    @ResponseWrapper(localName = "getWorkoutsByAuthorResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutsByAuthorResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<WorkoutSOAPResponse> getWorkoutsByAuthor(

        @WebParam(name = "arg0", targetNamespace = "")
        String arg0
    );

    @WebMethod
    @RequestWrapper(localName = "getWorkoutsByType", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutsByType")
    @ResponseWrapper(localName = "getWorkoutsByTypeResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutsByTypeResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<WorkoutSOAPResponse> getWorkoutsByType(

        @WebParam(name = "arg0", targetNamespace = "")
        String arg0
    );

    @WebMethod
    @RequestWrapper(localName = "getWorkoutsByDifficulty", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutsByDifficulty")
    @ResponseWrapper(localName = "getWorkoutsByDifficultyResponse", targetNamespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", className = "put.poznan.pl.michalxpz.workoutplannersoap.GetWorkoutsByDifficultyResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<WorkoutSOAPResponse> getWorkoutsByDifficulty(

        @WebParam(name = "arg0", targetNamespace = "")
        String arg0
    );
}
