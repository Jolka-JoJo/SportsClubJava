package lt.ku.SportsClub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.SportsClub.entities.Workout;
import lt.ku.SportsClub.repositories.WorkoutRepository;

@Service
public class WorkoutService {
	@Autowired
	WorkoutRepository workoutRepository;
	
	public Workout addWorkout(Workout workout) {
		return workoutRepository.save(workout);
	}
	
	public List<Workout> getWorkouts(){
		return workoutRepository.findAll();
	}
	
	public Workout updateWorkout(Workout workout) {
		Workout old = workoutRepository.getById(workout.getId());
		old.setName(workout.getName());
		old.setDate(workout.getDate());
		old.setPlaces(workout.getPlaces());
		old.setLocation(workout.getLocation());
		workoutRepository.save(old);
		return old;
	}
	
	public void deleteWorkout(Integer id) {
		workoutRepository.deleteById(id);
	}
	
	public Workout getWorkout(Integer id) {
		return workoutRepository.getById(id);
	}
}
