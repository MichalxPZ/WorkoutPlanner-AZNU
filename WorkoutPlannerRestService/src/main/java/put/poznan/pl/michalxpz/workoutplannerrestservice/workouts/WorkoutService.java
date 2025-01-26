package put.poznan.pl.michalxpz.workoutplannerrestservice.workouts;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import put.poznan.pl.michalxpz.workoutplannerrestservice.model.*;
import put.poznan.pl.michalxpz.workoutplannerrestservice.users.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    @Transactional
    public Workout startWorkout(StartWorkoutRequest startWorkoutRequest) {
        log.info("Start workout request: {}", startWorkoutRequest);
        if (startWorkoutRequest.getUser() == null || startWorkoutRequest.getUser().getUserId() == null) {
            throw new WorkoutException("User cannot be null");
        }
        if (userRepository.findByUserId(startWorkoutRequest.getUser().getUserId()).isEmpty()) {
            throw new WorkoutException("User not found with id: " + startWorkoutRequest.getUser().getUserId());
        }
        if (startWorkoutRequest.getStartDate() == null || startWorkoutRequest.getStartDate().trim().isEmpty()) {
            throw new WorkoutException("Start date cannot be null");
        }
        if (startWorkoutRequest.getWorkoutName() == null || startWorkoutRequest.getWorkoutName().trim().isEmpty()) {
            throw new WorkoutException("Workout name cannot be null");
        }
        Workout workout = WorkoutMapper.mapStartWorkout(startWorkoutRequest);
        workout.setUser(startWorkoutRequest.getUser());
        Workout savedWorkout = workoutRepository.save(workout);
        log.info("Workout started: {}", savedWorkout);
        return savedWorkout;
    }

    @Transactional
    public Workout endWorkout(EndWorkoutRequest endWorkoutRequest) {
        log.info("End workout request: {}", endWorkoutRequest);
        if (endWorkoutRequest.getWorkoutId() == null) {
            throw new WorkoutException("Workout id cannot be null");
        }
        if (endWorkoutRequest.getEndDate() == null) {
            throw new WorkoutException("End date cannot be null");
        }
        Workout workout = workoutRepository.findById(endWorkoutRequest.getWorkoutId()).orElseThrow(() -> new WorkoutException("Workout not found with id: " + endWorkoutRequest.getWorkoutId()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");  // Format bez sekund
        SimpleDateFormat dateFormatWithSeconds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = endWorkoutRequest.getEndDate();
        try {
            Date endDate = dateFormat.parse(dateString);
            workout.setEndDate(endDate);
        } catch (ParseException e) {
            try {
                Date endDate = dateFormatWithSeconds.parse(dateString);
                workout.setEndDate(endDate);
            } catch (ParseException e2) {
                throw new WorkoutException("Invalid date format");
            }
        }
        if (endWorkoutRequest.getDuration() == null || endWorkoutRequest.getDuration() <= 0) {
            Instant startInstant = workout.getStartDate().toInstant();
            Instant endInstant = workout.getEndDate().toInstant();
            Duration duration = Duration.between(startInstant, endInstant);
            workout.setDuration(duration.toMillis());
        }
        Workout savedWorkout = workoutRepository.save(workout);
        log.info("Workout ended: {}", savedWorkout);
        return savedWorkout;
    }

    public List<Workout> getWorkouts() {
        return workoutRepository.findAll().stream()
                .toList();
    }

    public List<Workout> getWorkoutsByUser(Long userId) {
        if (userId == null) {
            throw new WorkoutException("User id cannot be null");
        }
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new WorkoutException("User not found with id: " + userId));
        return Arrays.stream(workoutRepository.findWorkoutsByUser(user))
                .toList();
    }

    public Workout getWorkoutById(Long id) {
        return workoutRepository.findById(id).orElseThrow(() -> new WorkoutException("Workout not found with id: " + id));
    }

    @Transactional
    public void removeWorkout(Long id) {
        if (!workoutRepository.existsById(id)) {
            throw new WorkoutException("Workout not found with id: " + id);
        }
        workoutRepository.deleteById(id);
    }

    @Transactional
    public Workout updateWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Transactional
    public Workout updateWorkoutState(Long workoutId, WorkoutState state) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new WorkoutException("Workout not found"));
        workout.setState(state);
        return workoutRepository.save(workout);
    }

    @Transactional
    public void updateWorkoutStates() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
        Date currentDate = Date.from(now.toInstant());
        List<Workout> workouts = workoutRepository.findAll();
        log.info("Updating states for {} workouts", workouts.size());
        for (Workout workout : workouts) {
            WorkoutState newState = determineWorkoutState(workout, currentDate);
            log.info("Updating state for workout {} from {} to {}", workout.getWorkout_id(), workout.getState(), newState);
            if (workout.getState() != newState) {
                workout.setState(newState);
                workoutRepository.save(workout);
                log.info("State updated for workout {}", workout.getWorkout_id());
            }
        }
    }

    private WorkoutState determineWorkoutState(Workout workout, Date now) {
        log.info("Workout start date: {}", workout.getStartDate());
        log.info("Workout end date: {}", workout.getEndDate());
        log.info("Now: {}", now);
        if (workout.getState() == WorkoutState.CANCELED) {
            return WorkoutState.CANCELED;
        }

        if (workout.getStartDate().after(now)) {
            return WorkoutState.PLANNED;
        }

        if (workout.getEndDate() == null) {
            return WorkoutState.IN_PROGRESS;
        }

        if (workout.getEndDate().before(now)) {
            return WorkoutState.FINISHED;
        }

        return WorkoutState.IN_PROGRESS;
    }
}