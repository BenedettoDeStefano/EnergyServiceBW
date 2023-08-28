package Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import EnergyServices.Entities.User;
import Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserByID(UUID id) throws NotFoundException {
		return userRepository.findById(id).orElse(null);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(UUID id) throws NotFoundException {
		User userTrovato = userRepository.findById(id).orElse(null);
		userRepository.delete(userTrovato);
	}

	public User updateUserById(User nuovoUser, UUID id) {
		User userTrovato = userRepository.findById(id).orElse(null);

		if (userTrovato != null) {
			userTrovato.setCognome(nuovoUser.getCognome());
			userTrovato.setEta(nuovoUser.getEta());
			userTrovato.setNome(nuovoUser.getNome());
			userTrovato.setPassword(nuovoUser.getPassword());
			userTrovato.setRuolo(nuovoUser.getRuolo());
			userTrovato.setUsername(nuovoUser.getUsername());

			return userRepository.save(userTrovato);
		} else {
			return null;
		}
	}

}
