package EnergyServices.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import EnergyServices.Entities.Fattura;
import EnergyServices.Repository.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	private FatturaRepository fatturaRepository;

	public List<Fattura> getAllFatture() {
		return fatturaRepository.findAll();
	}

	public Fattura getFatturaByID(Long id) throws NotFoundException {
		return fatturaRepository.findById(id).orElse(null);
	}

	public Fattura saveFattura(Fattura fattura) {
		return fatturaRepository.save(fattura);
	}

	public void deleteFattura(Long id) throws NotFoundException {
		Fattura fatturaTrovata = fatturaRepository.findById(id).orElse(null);
		fatturaRepository.delete(fatturaTrovata);
	}

	public Fattura updateFatturaById(Fattura nuovaFattura, Long id) {
		Fattura fatturaTrovata = fatturaRepository.findById(id).orElse(null);

		if (fatturaTrovata != null) {
			fatturaTrovata.setAnno(nuovaFattura.getAnno());
			fatturaTrovata.setCliente(nuovaFattura.getCliente());
			fatturaTrovata.setData(nuovaFattura.getData());
			fatturaTrovata.setImporto(nuovaFattura.getImporto());
			fatturaTrovata.setNumero(nuovaFattura.getNumero());
			fatturaTrovata.setStato(nuovaFattura.getStato());

			return fatturaRepository.save(fatturaTrovata);
		} else {
			return null;
		}
	}

	public Fattura createFattura(Fattura fattura) {
		return saveFattura(fattura);
	}

}
