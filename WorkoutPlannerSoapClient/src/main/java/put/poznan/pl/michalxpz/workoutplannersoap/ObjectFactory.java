
package put.poznan.pl.michalxpz.workoutplannersoap;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the put.poznan.pl.michalxpz.workoutplannersoap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddWorkout_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "addWorkout");
    private final static QName _AddWorkoutResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "addWorkoutResponse");
    private final static QName _DeleteAllWorkouts_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "deleteAllWorkouts");
    private final static QName _DeleteAllWorkoutsResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "deleteAllWorkoutsResponse");
    private final static QName _DeleteWorkout_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "deleteWorkout");
    private final static QName _DeleteWorkoutResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "deleteWorkoutResponse");
    private final static QName _GetAllWorkouts_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getAllWorkouts");
    private final static QName _GetAllWorkoutsResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getAllWorkoutsResponse");
    private final static QName _GetWorkoutById_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutById");
    private final static QName _GetWorkoutByIdResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutByIdResponse");
    private final static QName _GetWorkoutsByAuthor_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutsByAuthor");
    private final static QName _GetWorkoutsByAuthorResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutsByAuthorResponse");
    private final static QName _GetWorkoutsByDifficulty_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutsByDifficulty");
    private final static QName _GetWorkoutsByDifficultyResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutsByDifficultyResponse");
    private final static QName _GetWorkoutsByEquipment_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutsByEquipment");
    private final static QName _GetWorkoutsByEquipmentResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutsByEquipmentResponse");
    private final static QName _GetWorkoutsByMuscle_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutsByMuscle");
    private final static QName _GetWorkoutsByMuscleResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutsByMuscleResponse");
    private final static QName _GetWorkoutsByType_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutsByType");
    private final static QName _GetWorkoutsByTypeResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "getWorkoutsByTypeResponse");
    private final static QName _UpdateWorkout_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "updateWorkout");
    private final static QName _UpdateWorkoutResponse_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "updateWorkoutResponse");
    private final static QName _WorkoutSOAPException_QNAME = new QName("http://workoutplannersoap.michalxpz.pl.poznan.put/", "WorkoutSOAPException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: put.poznan.pl.michalxpz.workoutplannersoap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddWorkout }
     * 
     */
    public AddWorkout createAddWorkout() {
        return new AddWorkout();
    }

    /**
     * Create an instance of {@link AddWorkoutResponse }
     * 
     */
    public AddWorkoutResponse createAddWorkoutResponse() {
        return new AddWorkoutResponse();
    }

    /**
     * Create an instance of {@link DeleteAllWorkouts }
     * 
     */
    public DeleteAllWorkouts createDeleteAllWorkouts() {
        return new DeleteAllWorkouts();
    }

    /**
     * Create an instance of {@link DeleteAllWorkoutsResponse }
     * 
     */
    public DeleteAllWorkoutsResponse createDeleteAllWorkoutsResponse() {
        return new DeleteAllWorkoutsResponse();
    }

    /**
     * Create an instance of {@link DeleteWorkout }
     * 
     */
    public DeleteWorkout createDeleteWorkout() {
        return new DeleteWorkout();
    }

    /**
     * Create an instance of {@link DeleteWorkoutResponse }
     * 
     */
    public DeleteWorkoutResponse createDeleteWorkoutResponse() {
        return new DeleteWorkoutResponse();
    }

    /**
     * Create an instance of {@link GetAllWorkouts }
     * 
     */
    public GetAllWorkouts createGetAllWorkouts() {
        return new GetAllWorkouts();
    }

    /**
     * Create an instance of {@link GetAllWorkoutsResponse }
     * 
     */
    public GetAllWorkoutsResponse createGetAllWorkoutsResponse() {
        return new GetAllWorkoutsResponse();
    }

    /**
     * Create an instance of {@link GetWorkoutById }
     * 
     */
    public GetWorkoutById createGetWorkoutById() {
        return new GetWorkoutById();
    }

    /**
     * Create an instance of {@link GetWorkoutByIdResponse }
     * 
     */
    public GetWorkoutByIdResponse createGetWorkoutByIdResponse() {
        return new GetWorkoutByIdResponse();
    }

    /**
     * Create an instance of {@link GetWorkoutsByAuthor }
     * 
     */
    public GetWorkoutsByAuthor createGetWorkoutsByAuthor() {
        return new GetWorkoutsByAuthor();
    }

    /**
     * Create an instance of {@link GetWorkoutsByAuthorResponse }
     * 
     */
    public GetWorkoutsByAuthorResponse createGetWorkoutsByAuthorResponse() {
        return new GetWorkoutsByAuthorResponse();
    }

    /**
     * Create an instance of {@link GetWorkoutsByDifficulty }
     * 
     */
    public GetWorkoutsByDifficulty createGetWorkoutsByDifficulty() {
        return new GetWorkoutsByDifficulty();
    }

    /**
     * Create an instance of {@link GetWorkoutsByDifficultyResponse }
     * 
     */
    public GetWorkoutsByDifficultyResponse createGetWorkoutsByDifficultyResponse() {
        return new GetWorkoutsByDifficultyResponse();
    }

    /**
     * Create an instance of {@link GetWorkoutsByEquipment }
     * 
     */
    public GetWorkoutsByEquipment createGetWorkoutsByEquipment() {
        return new GetWorkoutsByEquipment();
    }

    /**
     * Create an instance of {@link GetWorkoutsByEquipmentResponse }
     * 
     */
    public GetWorkoutsByEquipmentResponse createGetWorkoutsByEquipmentResponse() {
        return new GetWorkoutsByEquipmentResponse();
    }

    /**
     * Create an instance of {@link GetWorkoutsByMuscle }
     * 
     */
    public GetWorkoutsByMuscle createGetWorkoutsByMuscle() {
        return new GetWorkoutsByMuscle();
    }

    /**
     * Create an instance of {@link GetWorkoutsByMuscleResponse }
     * 
     */
    public GetWorkoutsByMuscleResponse createGetWorkoutsByMuscleResponse() {
        return new GetWorkoutsByMuscleResponse();
    }

    /**
     * Create an instance of {@link GetWorkoutsByType }
     * 
     */
    public GetWorkoutsByType createGetWorkoutsByType() {
        return new GetWorkoutsByType();
    }

    /**
     * Create an instance of {@link GetWorkoutsByTypeResponse }
     * 
     */
    public GetWorkoutsByTypeResponse createGetWorkoutsByTypeResponse() {
        return new GetWorkoutsByTypeResponse();
    }

    /**
     * Create an instance of {@link UpdateWorkout }
     * 
     */
    public UpdateWorkout createUpdateWorkout() {
        return new UpdateWorkout();
    }

    /**
     * Create an instance of {@link UpdateWorkoutResponse }
     * 
     */
    public UpdateWorkoutResponse createUpdateWorkoutResponse() {
        return new UpdateWorkoutResponse();
    }

    /**
     * Create an instance of {@link WorkoutSOAPException }
     * 
     */
    public WorkoutSOAPException createWorkoutSOAPException() {
        return new WorkoutSOAPException();
    }

    /**
     * Create an instance of {@link WorkoutSOAPResponse }
     * 
     */
    public WorkoutSOAPResponse createWorkoutSOAPResponse() {
        return new WorkoutSOAPResponse();
    }

    /**
     * Create an instance of {@link AddWorkoutSOAPRequest }
     * 
     */
    public AddWorkoutSOAPRequest createAddWorkoutSOAPRequest() {
        return new AddWorkoutSOAPRequest();
    }

    /**
     * Create an instance of {@link UpdateWorkoutSOAPRequest }
     * 
     */
    public UpdateWorkoutSOAPRequest createUpdateWorkoutSOAPRequest() {
        return new UpdateWorkoutSOAPRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddWorkout }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddWorkout }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "addWorkout")
    public JAXBElement<AddWorkout> createAddWorkout(AddWorkout value) {
        return new JAXBElement<AddWorkout>(_AddWorkout_QNAME, AddWorkout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddWorkoutResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddWorkoutResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "addWorkoutResponse")
    public JAXBElement<AddWorkoutResponse> createAddWorkoutResponse(AddWorkoutResponse value) {
        return new JAXBElement<AddWorkoutResponse>(_AddWorkoutResponse_QNAME, AddWorkoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllWorkouts }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteAllWorkouts }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "deleteAllWorkouts")
    public JAXBElement<DeleteAllWorkouts> createDeleteAllWorkouts(DeleteAllWorkouts value) {
        return new JAXBElement<DeleteAllWorkouts>(_DeleteAllWorkouts_QNAME, DeleteAllWorkouts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllWorkoutsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteAllWorkoutsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "deleteAllWorkoutsResponse")
    public JAXBElement<DeleteAllWorkoutsResponse> createDeleteAllWorkoutsResponse(DeleteAllWorkoutsResponse value) {
        return new JAXBElement<DeleteAllWorkoutsResponse>(_DeleteAllWorkoutsResponse_QNAME, DeleteAllWorkoutsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteWorkout }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteWorkout }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "deleteWorkout")
    public JAXBElement<DeleteWorkout> createDeleteWorkout(DeleteWorkout value) {
        return new JAXBElement<DeleteWorkout>(_DeleteWorkout_QNAME, DeleteWorkout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteWorkoutResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteWorkoutResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "deleteWorkoutResponse")
    public JAXBElement<DeleteWorkoutResponse> createDeleteWorkoutResponse(DeleteWorkoutResponse value) {
        return new JAXBElement<DeleteWorkoutResponse>(_DeleteWorkoutResponse_QNAME, DeleteWorkoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllWorkouts }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllWorkouts }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getAllWorkouts")
    public JAXBElement<GetAllWorkouts> createGetAllWorkouts(GetAllWorkouts value) {
        return new JAXBElement<GetAllWorkouts>(_GetAllWorkouts_QNAME, GetAllWorkouts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllWorkoutsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllWorkoutsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getAllWorkoutsResponse")
    public JAXBElement<GetAllWorkoutsResponse> createGetAllWorkoutsResponse(GetAllWorkoutsResponse value) {
        return new JAXBElement<GetAllWorkoutsResponse>(_GetAllWorkoutsResponse_QNAME, GetAllWorkoutsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutById }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutById")
    public JAXBElement<GetWorkoutById> createGetWorkoutById(GetWorkoutById value) {
        return new JAXBElement<GetWorkoutById>(_GetWorkoutById_QNAME, GetWorkoutById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutByIdResponse")
    public JAXBElement<GetWorkoutByIdResponse> createGetWorkoutByIdResponse(GetWorkoutByIdResponse value) {
        return new JAXBElement<GetWorkoutByIdResponse>(_GetWorkoutByIdResponse_QNAME, GetWorkoutByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByAuthor }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByAuthor }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutsByAuthor")
    public JAXBElement<GetWorkoutsByAuthor> createGetWorkoutsByAuthor(GetWorkoutsByAuthor value) {
        return new JAXBElement<GetWorkoutsByAuthor>(_GetWorkoutsByAuthor_QNAME, GetWorkoutsByAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByAuthorResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByAuthorResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutsByAuthorResponse")
    public JAXBElement<GetWorkoutsByAuthorResponse> createGetWorkoutsByAuthorResponse(GetWorkoutsByAuthorResponse value) {
        return new JAXBElement<GetWorkoutsByAuthorResponse>(_GetWorkoutsByAuthorResponse_QNAME, GetWorkoutsByAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByDifficulty }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByDifficulty }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutsByDifficulty")
    public JAXBElement<GetWorkoutsByDifficulty> createGetWorkoutsByDifficulty(GetWorkoutsByDifficulty value) {
        return new JAXBElement<GetWorkoutsByDifficulty>(_GetWorkoutsByDifficulty_QNAME, GetWorkoutsByDifficulty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByDifficultyResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByDifficultyResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutsByDifficultyResponse")
    public JAXBElement<GetWorkoutsByDifficultyResponse> createGetWorkoutsByDifficultyResponse(GetWorkoutsByDifficultyResponse value) {
        return new JAXBElement<GetWorkoutsByDifficultyResponse>(_GetWorkoutsByDifficultyResponse_QNAME, GetWorkoutsByDifficultyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByEquipment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByEquipment }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutsByEquipment")
    public JAXBElement<GetWorkoutsByEquipment> createGetWorkoutsByEquipment(GetWorkoutsByEquipment value) {
        return new JAXBElement<GetWorkoutsByEquipment>(_GetWorkoutsByEquipment_QNAME, GetWorkoutsByEquipment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByEquipmentResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByEquipmentResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutsByEquipmentResponse")
    public JAXBElement<GetWorkoutsByEquipmentResponse> createGetWorkoutsByEquipmentResponse(GetWorkoutsByEquipmentResponse value) {
        return new JAXBElement<GetWorkoutsByEquipmentResponse>(_GetWorkoutsByEquipmentResponse_QNAME, GetWorkoutsByEquipmentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByMuscle }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByMuscle }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutsByMuscle")
    public JAXBElement<GetWorkoutsByMuscle> createGetWorkoutsByMuscle(GetWorkoutsByMuscle value) {
        return new JAXBElement<GetWorkoutsByMuscle>(_GetWorkoutsByMuscle_QNAME, GetWorkoutsByMuscle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByMuscleResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByMuscleResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutsByMuscleResponse")
    public JAXBElement<GetWorkoutsByMuscleResponse> createGetWorkoutsByMuscleResponse(GetWorkoutsByMuscleResponse value) {
        return new JAXBElement<GetWorkoutsByMuscleResponse>(_GetWorkoutsByMuscleResponse_QNAME, GetWorkoutsByMuscleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByType }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutsByType")
    public JAXBElement<GetWorkoutsByType> createGetWorkoutsByType(GetWorkoutsByType value) {
        return new JAXBElement<GetWorkoutsByType>(_GetWorkoutsByType_QNAME, GetWorkoutsByType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByTypeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetWorkoutsByTypeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "getWorkoutsByTypeResponse")
    public JAXBElement<GetWorkoutsByTypeResponse> createGetWorkoutsByTypeResponse(GetWorkoutsByTypeResponse value) {
        return new JAXBElement<GetWorkoutsByTypeResponse>(_GetWorkoutsByTypeResponse_QNAME, GetWorkoutsByTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateWorkout }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateWorkout }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "updateWorkout")
    public JAXBElement<UpdateWorkout> createUpdateWorkout(UpdateWorkout value) {
        return new JAXBElement<UpdateWorkout>(_UpdateWorkout_QNAME, UpdateWorkout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateWorkoutResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateWorkoutResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "updateWorkoutResponse")
    public JAXBElement<UpdateWorkoutResponse> createUpdateWorkoutResponse(UpdateWorkoutResponse value) {
        return new JAXBElement<UpdateWorkoutResponse>(_UpdateWorkoutResponse_QNAME, UpdateWorkoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WorkoutSOAPException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WorkoutSOAPException }{@code >}
     */
    @XmlElementDecl(namespace = "http://workoutplannersoap.michalxpz.pl.poznan.put/", name = "WorkoutSOAPException")
    public JAXBElement<WorkoutSOAPException> createWorkoutSOAPException(WorkoutSOAPException value) {
        return new JAXBElement<WorkoutSOAPException>(_WorkoutSOAPException_QNAME, WorkoutSOAPException.class, null, value);
    }

}
