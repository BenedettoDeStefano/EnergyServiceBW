package EnergyServices.Entities;

import java.time.LocalDate;

import EnergyServices.Enum.StatoFattura;
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
public class Fattura {

	@Id
	@GeneratedValue
	Long id;

	LocalDate data;
	int anno;
	Long importo;
	int numero;

	@Enumerated(EnumType.STRING)
	StatoFattura stato;

	Cliente cliente;

}
