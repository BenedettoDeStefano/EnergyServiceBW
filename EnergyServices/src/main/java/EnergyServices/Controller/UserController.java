package EnergyServices.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EnergyServices.Entities.User;
import EnergyServices.PayLoad.LoginSuccessfullPayload;
import EnergyServices.PayLoad.NewUserResponsePayload;
import EnergyServices.PayLoad.UserLoginPayload;
import EnergyServices.PayLoad.UserRequestPayload;
import EnergyServices.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<NewUserResponsePayload> registerUser(@RequestBody UserRequestPayload userRequest) {
		User newUser = new User();
		newUser.setNome(userRequest.getName());
		newUser.setCognome(userRequest.getSurname());
		// newUser.setEmail(userRequest.getEmail());
		newUser.setPassword(userRequest.getPassword());

		User savedUser = userService.saveUser(newUser);

		NewUserResponsePayload responsePayload = new NewUserResponsePayload(savedUser.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(responsePayload);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginSuccessfullPayload> loginUser(@RequestBody UserLoginPayload loginPayload) {
		// implementare la logica jwt token!

		LoginSuccessfullPayload responsePayload = new LoginSuccessfullPayload("your-access-token");
		return ResponseEntity.ok(responsePayload);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable UUID id) {
		try {
			User user = userService.getUserByID(id);
			return ResponseEntity.ok(user);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody UserRequestPayload updateUserRequest, @PathVariable UUID id) {
		User updatedUser = new User();
		updatedUser.setNome(updateUserRequest.getName());
		updatedUser.setCognome(updateUserRequest.getSurname());
		// updatedUser.setEmail(updateUserRequest.getEmail());

		User updated = userService.updateUserById(updatedUser, id);
		if (updated != null) {
			return ResponseEntity.ok(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.noContent().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
