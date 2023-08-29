package EnergyServices.PayLoad;

import java.time.LocalDate;

import EnergyServices.Entities.Cliente;
import EnergyServices.Entities.Fattura;
import EnergyServices.Enum.StatoFattura;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FatturaPayLoad {

	private LocalDate data;
	private int anno;
	private double importo;
	private int numero;
	private StatoFattura stato;
	private Long clienteId;

	public Fattura toFattura() {
		Fattura fattura = new Fattura();
		fattura.setData(this.data);
		fattura.setAnno(this.anno);
		fattura.setImporto(this.importo);
		fattura.setNumero(this.numero);
		fattura.setStato(this.stato);

		Cliente cliente = new Cliente();
		cliente.setId(this.clienteId);
		fattura.setCliente(cliente);

		return fattura;
	}

}
