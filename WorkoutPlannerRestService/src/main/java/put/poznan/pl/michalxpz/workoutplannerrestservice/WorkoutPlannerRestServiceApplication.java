package put.poznan.pl.michalxpz.workoutplannerrestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class WorkoutPlannerRestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkoutPlannerRestServiceApplication.class, args);
    }

}
