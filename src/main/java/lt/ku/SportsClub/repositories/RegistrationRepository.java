package lt.ku.SportsClub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.SportsClub.entities.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer>{

}
