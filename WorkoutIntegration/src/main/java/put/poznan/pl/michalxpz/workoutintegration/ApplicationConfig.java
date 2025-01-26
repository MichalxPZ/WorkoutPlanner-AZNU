package put.poznan.pl.michalxpz.workoutintegration;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import put.poznan.pl.michalxpz.workoutintegration.model.workout.state.ProcessingEvent;
import put.poznan.pl.michalxpz.workoutintegration.model.workout.state.ProcessingState;
import put.poznan.pl.michalxpz.workoutintegration.model.workout.state.StateMachineBuilder;
import put.poznan.pl.michalxpz.workoutintegration.model.workout.state.StateService;

@Configuration
public class ApplicationConfig {

    @Bean(name="basicStateMachineBuilder")
    public StateMachineBuilder basicStateMachineBuilder() {
        StateMachineBuilder smb = new StateMachineBuilder();
        smb.initialState(ProcessingState.PLANNED)
                .add(ProcessingState.PLANNED, ProcessingEvent.START,ProcessingState.IN_PROGRESS)
                .add(ProcessingState.IN_PROGRESS,ProcessingEvent.END, ProcessingState.FINISHED)
                .add(ProcessingState.CANCELLED,ProcessingEvent.START,ProcessingState.CANCELLED)
                .add(ProcessingState.CANCELLED,ProcessingEvent.END,ProcessingState.CANCELLED)
                .add(ProcessingState.FINISHED,ProcessingEvent.END,ProcessingState.FINISHED)
                .add(ProcessingState.IN_PROGRESS,ProcessingEvent.CANCEL,ProcessingState.CANCELLED)
                .add(ProcessingState.PLANNED,ProcessingEvent.CANCEL,ProcessingState.CANCELLED)
                .add(ProcessingState.FINISHED,ProcessingEvent.CANCEL,ProcessingState.CANCELLED)
                .add(ProcessingState.IN_PROGRESS,ProcessingEvent.DELETE,ProcessingState.CANCELLED)
                .add(ProcessingState.PLANNED,ProcessingEvent.DELETE,ProcessingState.CANCELLED)
                .add(ProcessingState.FINISHED,ProcessingEvent.DELETE,ProcessingState.CANCELLED)
                .add(ProcessingState.CANCELLED,ProcessingEvent.DELETE,ProcessingState.CANCELLED)
                .add(ProcessingState.CANCELLED,ProcessingEvent.CANCEL,ProcessingState.CANCELLED)
                .add(ProcessingState.PLANNED,ProcessingEvent.END,ProcessingState.FINISHED)
        ;
        return smb;
    }

    @Bean
    @Scope("prototype")
    public StateService stateService(@Qualifier("basicStateMachineBuilder") StateMachineBuilder stateMachineBuilder) {
        return new StateService (stateMachineBuilder);
    }
}
