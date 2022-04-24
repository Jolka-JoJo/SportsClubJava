package lt.ku.SportsClub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.SportsClub.entities.Registration;
import lt.ku.SportsClub.repositories.RegistrationRepository;

@Service
public class RegistrationService {
	@Autowired
	RegistrationRepository registrationRepository;
	
	public Registration addRegistration(Registration registration) {
		long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);
        registration.setRegistration_date(date);
		return registrationRepository.save(registration);
	}
	
	public List<Registration> getRegistrations(){
		return registrationRepository.findAll();
	}
	
	public Registration updateRegistration(Registration registration) {
		Registration old = registrationRepository.getById(registration.getId());
		
		long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis); 
        
		old.setClient(registration.getClient());
		old.setRegistration_date(date);
		old.setWorkout(registration.getWorkout());
		registrationRepository.save(old);
		return old;
	}
	
	public void deleteRegistration(Integer id) {
		registrationRepository.deleteById(id);
	}
	
	public Registration getRegistration(Integer id) {
		return registrationRepository.getById(id);
	}
	

}
