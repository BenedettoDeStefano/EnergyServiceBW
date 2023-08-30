package EnergyServices.Entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comune {

	@Id
	@GeneratedValue
	Long id;
	
	int codiceProvincia;
	int progressivoComune;
	String denominazione;

	@ManyToOne
	@JoinColumn(name = "provincia_id")
	Provincia provincia;

	@OneToMany(mappedBy = "comune")
	Set<Indirizzo> indirizzi;
}
