package EnergyServices.PayLoad;

import java.time.LocalDate;
import java.util.TreeSet;

import EnergyServices.Entities.Cliente;
import EnergyServices.Entities.Indirizzo;
import EnergyServices.Enum.TipoCliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientePayLoad {

	@NotNull(message = "ragione sociale è un campo obbligatorio")
	private String ragioneSociale;
	@NotNull(message = "partita iva è un campo obbligatorio")
	private String partitaIva;
	@Email(message = "email non valida")
	private String email;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private double fatturatoAnnuale;
	private String pec;
	private String telefono;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	private TipoCliente tipo;
	private Long id;

	private IndirizzoPayload sedeLegale;
	private IndirizzoPayload sedeOperativa;

	public ClientePayLoad(Cliente c) {
		this.setRagioneSociale(c.getRagioneSociale());
		this.setPartitaIva(c.getPartitaIva());
		this.setEmail(c.getEmail());
		this.setDataInserimento(c.getDataInserimento());
		this.setDataUltimoContatto(c.getDataUltimoContatto());
		this.setFatturatoAnnuale(c.getFatturatoAnnuale());
		this.setPec(c.getPec());
		this.setTelefono(c.getTelefono());
		this.setEmailContatto(c.getEmailContatto());
		this.setNomeContatto(c.getNomeContatto());
		this.setCognomeContatto(c.getCognomeContatto());
		this.setTelefonoContatto(c.getTelefonoContatto());
		this.setTipo(c.getTipo());
		this.setId(c.getId());

		if (c.getIndirizzi().size() > 0) {
			TreeSet<Indirizzo> set = new TreeSet<>(c.getIndirizzi());
			this.setSedeLegale(new IndirizzoPayload(set.pollFirst()));
			if (!set.isEmpty()) {
				this.setSedeOperativa(new IndirizzoPayload(set.pollFirst()));
			}
		}
	}

	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		cliente.setRagioneSociale(this.ragioneSociale);
		cliente.setPartitaIva(this.partitaIva);
		cliente.setEmail(this.email);
		cliente.setDataInserimento(this.dataInserimento);
		cliente.setDataUltimoContatto(this.dataUltimoContatto);
		cliente.setFatturatoAnnuale(this.fatturatoAnnuale);
		cliente.setPec(this.pec);
		cliente.setTelefono(this.telefono);
		cliente.setEmailContatto(this.emailContatto);
		cliente.setNomeContatto(this.nomeContatto);
		cliente.setCognomeContatto(this.cognomeContatto);
		cliente.setTelefonoContatto(this.telefonoContatto);
		cliente.setTipo(this.tipo);
		return cliente;
	}

}
