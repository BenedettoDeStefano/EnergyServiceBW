package EnergyServices.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import EnergyServices.Entities.User;
import EnergyServices.Enum.Role;
import EnergyServices.Service.UserService;
import EnergyServices.common.LoginSuccessfullPayload;
import EnergyServices.common.UserLoginPayload;
import EnergyServices.common.UserRequestPayload;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
	
	@Autowired
	UserService usersService;

	@Autowired
	JWTTools jwtTools;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody UserRequestPayload body) {
		try {
		User created = usersService.saveUser(convertPayload(body));
		return created;
		} catch (DataIntegrityViolationException e) {
			log.error(e.getMessage());
			throw new BadRequestException("username già esistente.");
		}


	}

	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginSuccessfullPayload login(@RequestBody UserLoginPayload body) {
		// 1. Verifichiamo che l'email dell'utente sia presente nel db

		User user = usersService.findByUsername(body.getEmail());

		// 2. In caso affermativo, devo verificare che la pw corrisponda a quella
		// trovata nel db
		if (passwordEncoder.matches(body.getPassword(), user.getPassword())) {

			// 3. Se le credenziali sono OK --> genero un JWT e lo invio come risposta
			String token = jwtTools.createToken(user);
			return new LoginSuccessfullPayload(token, user);

		} else {
			// 4. Se le credenziali NON sono OK --> 401
			throw new UnauthorizedException("Credenziali non valide!");
		}
	}

	private User convertPayload(UserRequestPayload body) {
		User newUser = new User(null, body.getEmail(), this.passwordEncoder.encode(body.getPassword()), body.getName(),
				body.getSurname(),
				body.getEtà(), Role.USER);
		return newUser;
	}

}
