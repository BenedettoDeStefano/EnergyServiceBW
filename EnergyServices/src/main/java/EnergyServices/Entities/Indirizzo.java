package EnergyServices.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Indirizzo {

	@Id
	@GeneratedValue
	Long id;

	String via;
	int civico;
	String localita;
	int cap;

	@Enumerated(EnumType.STRING)
	Comune comune;

}
