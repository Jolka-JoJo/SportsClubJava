package lt.ku.SportsClub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lt.ku.SportsClub.entities.Client;
import lt.ku.SportsClub.repositories.ClientRepository;

@Service
public class ClientService implements UserDetailsService{

	@Autowired
	ClientRepository clientRepository;
	
	public Client addClient(Client client) {
		client.setPassword((new BCryptPasswordEncoder()).encode(client.getPassword()));
		return clientRepository.save(client);
	}
	
	public List<Client> getClients(){
		return clientRepository.findAll();
	}
	
	public Client updateClient(Client client) {
		Client old=clientRepository.getById(client.getId());
		old.setName(client.getName());
		old.setSurname(client.getSurname());
		old.setEmail(client.getEmail());
		old.setPhone(client.getPhone());
		old.setUsername(client.getUsername());
		old.setPassword((new BCryptPasswordEncoder()).encode(client.getPassword()));
		old.setRole(client.getRole());
		clientRepository.save(old);
		return old;
	}
	
	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);
	}
	
	public Client getClient(Integer id) {
		return clientRepository.getById(id);
	}

	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		Client client = clientRepository.findByUsername(username);
		if(client == null) {
			throw new UsernameNotFoundException("Vartotojas nerastas");
		}
		return client;
	}
}
