package lt.ku.SportsClub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lt.ku.SportsClub.entities.Workout;
import lt.ku.SportsClub.services.FileStorageService;
import lt.ku.SportsClub.services.WorkoutService;

@Controller
@RequestMapping("/workout")
public class WorkoutController {
	@Autowired
	WorkoutService workoutService;
	
	@Autowired
	FileStorageService fileStorage;
	
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
			@RequestParam("location") String location,
			@RequestParam("agreement") MultipartFile agreement) {
		Workout workout_new = new Workout(name, date, places, location, agreement.getOriginalFilename());
		workout_new = workoutService.addWorkout(workout_new);
		fileStorage.store(agreement,workout_new.getId().toString());
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
	
	@GetMapping("/agreement/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getAgreement(@PathVariable Integer id) {
		Resource file=fileStorage.loadFile(id.toString());
		Workout workout=workoutService.getWorkout(id);
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+workout.getFileName()+"\"")
				.body(file);
	}
	
}
