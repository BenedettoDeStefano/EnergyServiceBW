package EnergyServices.Entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {

	@Id
	@GeneratedValue
	Long id;

	String sigla;
	String provincia;
	String regione;

	@OneToMany(mappedBy = "provincia")
	Set<Comune> comuni;
	

}
