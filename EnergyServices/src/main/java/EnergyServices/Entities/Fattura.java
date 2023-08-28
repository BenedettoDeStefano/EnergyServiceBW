package EnergyServices.Entities;

import java.time.LocalDate;

import EnergyServices.Enum.StatoFattura;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Fattura {

	@Id
	@GeneratedValue
	Long id;

	LocalDate data;
	int anno;
	double importo;
	int numero;

	@Enumerated(EnumType.STRING)
	StatoFattura stato;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	Cliente cliente;

}
