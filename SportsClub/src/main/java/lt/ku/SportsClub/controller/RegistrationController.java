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

import lt.ku.SportsClub.entities.Registration;
import lt.ku.SportsClub.services.ClientService;
import lt.ku.SportsClub.services.RegistrationService;
import lt.ku.SportsClub.services.WorkoutService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	RegistrationService registrationService;
	@Autowired
	ClientService clientService;
	@Autowired
	WorkoutService workoutService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("registrations", registrationService.getRegistrations());
		return "registration_list";
	}
	
	@GetMapping("/new")
	public String newRegistration(Model model) {
		model.addAttribute("registration", new Registration());
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("workouts", workoutService.getWorkouts());
		return "registration_new";
	}
	
	@PostMapping("/new")
	public String storeRegistration(
			@ModelAttribute
			Registration registration,
			@RequestParam("clientId")
			Integer clientId,
			@RequestParam("workoutId")
			Integer workoutId,
			Model model) {
		registration.setClient(clientService.getClient(clientId));
		registration.setWorkout(workoutService.getWorkout(workoutId));
		registrationService.addRegistration(registration);
		return "redirect:/registration/";
	}
	
	@GetMapping("/update/{id}")
	public String registrationNew(
			@PathVariable("id") 
			Integer id,
			Model model) {
		model.addAttribute("registration", registrationService.getRegistration(id));
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("workouts", workoutService.getWorkouts());
		return "registration_update";
	}
	
	@PostMapping("/update/{id}")
	public String registrationUpdate(
			@ModelAttribute Registration registration, 
			@PathVariable("id") Integer id,
			@RequestParam("clientId")
			Integer clientId,
			@RequestParam("workoutId")
			Integer workoutId,
			Model model) {
		registration.setClient(clientService.getClient(clientId));
		
		registration.setWorkout(workoutService.getWorkout(workoutId));
		registrationService.updateRegistration(registration);
		return "redirect:/registration/";
	}
	
	@GetMapping("/delete/{id}")
	public String registrationDelete(@PathVariable("id") Integer id) {
		registrationService.deleteRegistration(id);
		return "redirect:/registration/";
	}
}
