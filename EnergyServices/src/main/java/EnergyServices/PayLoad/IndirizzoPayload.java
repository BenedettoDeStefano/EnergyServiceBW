package EnergyServices.PayLoad;

import EnergyServices.Entities.Indirizzo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IndirizzoPayload {

	String via;
	int civico;
	String localita;
	int cap;

	Long comuneId;

	public IndirizzoPayload(Indirizzo i) {
		this.via = i.getVia();
		this.civico = i.getCivico();
		this.localita = i.getLocalita();
		this.cap = i.getCap();
		this.comuneId = i.getComune() != null ? i.getComune().getId() : null;
	}

	public Indirizzo toIndirizzo() {
		Indirizzo i = new Indirizzo();
		i.setCap(cap);
		i.setVia(via);
		i.setCivico(civico);
		i.setLocalita(localita);
		return i;
	}

}
