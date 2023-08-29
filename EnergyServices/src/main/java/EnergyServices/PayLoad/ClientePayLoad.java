package EnergyServices.PayLoad;

import java.time.LocalDate;

import EnergyServices.Entities.Cliente;
import EnergyServices.Enum.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientePayLoad {

	private String ragioneSociale;
	private String partitaIva;
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
