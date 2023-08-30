package EnergyServices.PayLoad;

import java.time.LocalDate;

import EnergyServices.Entities.Cliente;
import EnergyServices.Entities.Fattura;
import EnergyServices.Enum.StatoFattura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FatturaPayLoad {

	private LocalDate data;
	private int anno;
	private double importo;
	private int numero;
	private StatoFattura stato;
	private Long clienteId;
	private Long id;

	public FatturaPayLoad(Fattura f) {
		this.setData(f.getData());
		this.setAnno(f.getAnno());
		this.setImporto(f.getImporto());
		this.setNumero(f.getNumero());
		this.setStato(f.getStato());
		this.setId(f.getId());
		this.setClienteId(f.getCliente() != null ? f.getCliente().getId() : null);
	}

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
