package lt.ku.SportsClub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.ku.SportsClub.entities.Workout;
import lt.ku.SportsClub.services.WorkoutService;

@Controller
@RequestMapping("/workout")
public class WorkoutController {
	@Autowired
	WorkoutService workoutService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("workouts", workoutService.getWorkouts());
		return "workout_list";
	}
	
	@GetMapping("/new")
	public String newWorkout(Model model) {
		return "workout_new";
	}
	
	@PostMapping("/new")
	public String addClient(
			@RequestParam("name") String name,
			@RequestParam("date") java.sql.Date date,
			@RequestParam("places") Integer places,
			@RequestParam("location") String location) {
		Workout workout_new = new Workout(name, date, places, location);
		workoutService.addWorkout(workout_new);
		return "redirect:/workout/";
	}
	
	@GetMapping("/update/{id}")
	public String newWorkout(
			@PathVariable("id") Integer id,
			Model model) {
		model.addAttribute("workout", workoutService.getWorkout(id));
		return "workout_update";
	}
	
	@PostMapping("/update/{id}")
	public String updateWorkout(
			@PathVariable("id") Integer id,
			@ModelAttribute Workout workout) {
		workoutService.updateWorkout(workout);
		return "redirect:/workout/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteWorkout(@PathVariable("id") Integer id) {
		workoutService.deleteWorkout(id);
		return "redirect:/workout/";
	}
	
}
