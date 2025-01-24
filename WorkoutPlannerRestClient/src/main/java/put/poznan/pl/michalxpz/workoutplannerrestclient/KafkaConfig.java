package put.poznan.pl.michalxpz.workoutplannerrestclient;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${kafka.topic.workout-requested}")
    private String workoutRequestedTopic;

    @Value("${kafka.topic.workout-response}")
    private String workoutResponseTopic;

    @Value("${kafka.topic.workout-started}")
    private String workoutStartedTopic;

    @Value("${kafka.topic.workout-start}")
    private String workoutStartTopic;

    @Value("${kafka.topic.workout-ended}")
    private String workoutEndedTopic;

    @Value("${kafka.topic.workout-end}")
    private String workoutEndTopic;

    @Value("${kafka.topic.workout-deleted}")
    private String workoutDeletedTopic;

    @Value("${kafka.topic.workout-delete}")
    private String workoutDeleteTopic;

    @Value("${kafka.topic.user-created}")
    private String userCreatedTopic;

    @Value("${kafka.topic.user-create}")
    private String userCreateTopic;

    @Bean
    public NewTopic workoutRequestedTopic() {
        return new NewTopic(workoutRequestedTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic workoutStartedTopic() {
        return new NewTopic(workoutStartedTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic workoutEndedTopic() {
        return new NewTopic(workoutEndedTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic workoutDeletedTopic() {
        return new NewTopic(workoutDeletedTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic userCreatedTopic() {
        return new NewTopic(userCreatedTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic workoutStartTopic() {
        return new NewTopic(workoutStartTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic workoutEndTopic() {
        return new NewTopic(workoutEndTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic workoutDeleteTopic() {
        return new NewTopic(workoutDeleteTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic userCreateTopic() {
        return new NewTopic(userCreateTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic workoutResponseTopic() {
        return new NewTopic(workoutResponseTopic, 1, (short) 1);
    }
}