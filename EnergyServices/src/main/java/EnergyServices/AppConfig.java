package EnergyServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import EnergyServices.Entities.User;
import EnergyServices.Enum.Role;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan
public class AppConfig {

	@Value("${admin.username}")
	private String adminUsername;

	@Value("${admin.password}")
	private String adminPassword;

	@Autowired
	PasswordEncoder encoder;

	@Bean
	public User getAdmin() {
		return new User(null, adminUsername, encoder.encode(adminPassword), "Super", "Admin", 30, Role.ADMIN);
	}
}
