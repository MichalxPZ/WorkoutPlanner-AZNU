package put.poznan.pl.michalxpz.workoutintegration.model.workout.state;

import java.util.HashMap;

public class StateService {
    private HashMap<String, StateMachine> processingStates=new HashMap<>();

    public StateService(StateMachineBuilder stateMachineBuilder) {
        this.stateMachineBuilder = stateMachineBuilder;
    }


    private StateMachineBuilder stateMachineBuilder = null;

    public ProcessingState sendEvent(String workoutId, ProcessingEvent event) {
        StateMachine stateMachine;
        synchronized(this){
            stateMachine = processingStates.get(workoutId);
            if (stateMachine==null) {
                stateMachine=stateMachineBuilder.build();
                processingStates.put(workoutId, stateMachine);
            }
        }
        return stateMachine.sendEvent(event);

    }

    public void removeState(String workoutId) {
        processingStates.remove(workoutId);
    }

}
