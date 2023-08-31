package EnergyServices.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestPayload {
	private String name;
	private String surname;
	private String email;
	private String password;
	private int età;
}
