package EnergyServices.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "comune_id")
	Comune comune;

}
