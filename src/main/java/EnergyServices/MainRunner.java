package EnergyServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import EnergyServices.Entities.User;
import EnergyServices.Service.UserService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MainRunner implements CommandLineRunner {

	@Autowired
	private UserService srv;
	
	@Autowired
	private ApplicationContext context;

	@Override
	public void run(String... args) throws Exception {
		ApplicationContext ctx = context;

		User u = (User) ctx.getBean("getAdmin");
		try {
			User pu = srv.findByUsername(u.getUsername());
			log.info("superadmin già salvato: "+ pu);
		}catch(IllegalArgumentException e) {
			srv.saveUser(u);
		}


	}

}
