package put.poznan.pl.michalxpz.workoutplannerrestclient.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MessageWrapper<T> {
    private String correlationId;
    private T payload;

}