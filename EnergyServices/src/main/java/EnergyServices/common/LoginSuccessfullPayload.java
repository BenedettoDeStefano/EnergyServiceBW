package EnergyServices.common;

import EnergyServices.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginSuccessfullPayload {
	private String accessToken;
	private User user;
}
