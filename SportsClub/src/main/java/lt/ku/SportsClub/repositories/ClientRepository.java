package lt.ku.SportsClub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.SportsClub.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
	Client findByUsername(String username);
}
