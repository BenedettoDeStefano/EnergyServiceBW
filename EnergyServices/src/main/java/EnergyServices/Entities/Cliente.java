package EnergyServices.Entities;

import java.time.LocalDate;
import java.util.Set;

import EnergyServices.Enum.TipoCliente;
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
public class Cliente {

	@Id
	@GeneratedValue
	Long id;

	Set<Fattura> fatture;
	String ragioneSociale;
	String partitaIva;
	String email;
	LocalDate dataInserimento;
	LocalDate dataUltimoContatto;
	double fatturatoAnnuale;
	String pec;
	String telefono;
	String emailContatto;
	String nomeContatto;
	String cognomeContatto;
	String telefonoContatto;
	Set<Indirizzo> indirizzi;

	@Enumerated(EnumType.STRING)
	TipoCliente tipo;

}
