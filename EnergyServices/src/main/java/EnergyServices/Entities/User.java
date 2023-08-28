package EnergyServices.Entities;

import java.util.UUID;

import EnergyServices.Enum.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	UUID id;

	String username;
	String password;
	String nome;
	String cognome;
	int eta;

	@Enumerated(EnumType.STRING)
	Role ruolo;

}
