package EnergyServices.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import EnergyServices.Entities.Cliente;
import EnergyServices.Entities.Comune;
import EnergyServices.Entities.Indirizzo;
import EnergyServices.PayLoad.ClientePayLoad;
import EnergyServices.Repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ComuneService cSrv;

	public List<Cliente> getAllClienti() {
		return clienteRepository.findAll();
	}
	
	public Cliente getClienteByID(Long id) throws NotFoundException {
		return clienteRepository.findById(id).orElse(null);
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void deleteCliente(Long id) throws NotFoundException {
		Cliente clienteTrovato = clienteRepository.findById(id).orElse(null);
		clienteRepository.delete(clienteTrovato);
	}

	public Cliente updateClienteById(ClientePayLoad nuovoClientePayload, Long id) {
		Cliente nuovoCliente = nuovoClientePayload.toCliente();
		Cliente clienteTrovato = clienteRepository.findById(id).orElse(null);

		if (clienteTrovato != null) {
			clienteTrovato.setCognomeContatto(nuovoCliente.getCognomeContatto());
			clienteTrovato.setDataInserimento(nuovoCliente.getDataInserimento());
			clienteTrovato.setDataUltimoContatto(nuovoCliente.getDataUltimoContatto());
			clienteTrovato.setEmail(nuovoCliente.getEmail());
			clienteTrovato.setEmailContatto(nuovoCliente.getEmailContatto());
			clienteTrovato.setFatturatoAnnuale(nuovoCliente.getFatturatoAnnuale());
			clienteTrovato.setFatture(nuovoCliente.getFatture());
			clienteTrovato.setIndirizzi(nuovoCliente.getIndirizzi());
			clienteTrovato.setNomeContatto(nuovoCliente.getNomeContatto());
			clienteTrovato.setPartitaIva(nuovoCliente.getPartitaIva());
			clienteTrovato.setPec(nuovoCliente.getPec());
			clienteTrovato.setRagioneSociale(nuovoCliente.getRagioneSociale());
			clienteTrovato.setTelefono(nuovoCliente.getTelefono());
			clienteTrovato.setTelefonoContatto(nuovoCliente.getTelefonoContatto());
			clienteTrovato.setTipo(nuovoCliente.getTipo());

			setIndirizzi(nuovoClientePayload, clienteTrovato);

			return clienteRepository.save(clienteTrovato);
		} else {
			return null;
		}
	}

	public Cliente createCliente(ClientePayLoad clientePayload) {
		Cliente cliente = clientePayload.toCliente();
		setIndirizzi(clientePayload, cliente);
		return saveCliente(cliente);
	}

	private void setIndirizzi(ClientePayLoad payload, Cliente c) {
		if (payload.getSedeLegale() == null) {
			return;
		}
		Indirizzo[] indirizzi = new Indirizzo[2];
		if (payload.getSedeLegale() != null) {
			Indirizzo sedeLegale = payload.getSedeLegale().toIndirizzo();
			Comune comune = cSrv.getById(payload.getSedeLegale().getComuneId());
			sedeLegale.setComune(comune);
			sedeLegale.setOreder(0);
			sedeLegale.setCliente(c);
			indirizzi[0] = sedeLegale;

		}

		if (payload.getSedeOperativa() != null) {
			Indirizzo sedeOp = payload.getSedeOperativa().toIndirizzo();
			Comune comune = cSrv.getById(payload.getSedeOperativa().getComuneId());
			sedeOp.setComune(comune);
			sedeOp.setOreder(1);
			sedeOp.setCliente(c);
			indirizzi[1] = sedeOp;
		}
		Set<Indirizzo> setI = new HashSet<>();
		for (Indirizzo i : indirizzi) {
			if (i != null) {
				setI.add(i);
			}
		}
		c.setIndirizzi(setI);
	}

	// ------------------------------------------------------ Filtr

	public List<Cliente> findByFatturatoAnnuale(int fatturatoMin, int fatturatoMax) {
		return clienteRepository.findByFatturatoAnnualeBetween(fatturatoMin, fatturatoMax);
	}

	public List<Cliente> findByDataInserimento(LocalDate data) {
		return clienteRepository.findByDataInserimento(data);
	}

	public List<Cliente> findByDataUltimoContatto(LocalDate data) {
		return clienteRepository.findByDataUltimoContatto(data);
	}

	public List<Cliente> findByParteDelNome(String parteDelNome) {
		return clienteRepository.findByNomeContattoContainingIgnoreCase(parteDelNome);
	}

	// ------------------------------------------------------
	public List<Cliente> getAllClientiOrderedByName() {
		return clienteRepository.findAllByOrderByRagioneSocialeAsc();
	}

	public List<Cliente> getAllClientiOrderedByFatturato() {
		return clienteRepository.findAllByOrderByFatturatoAnnualeDesc();
	}

	public List<Cliente> getAllClientiOrderedByDataInserimento() {
		return clienteRepository.findAllByOrderByDataInserimentoDesc();
	}

	public List<Cliente> getAllClientiOrderedByDataUltimoContatto() {
		return clienteRepository.findAllByOrderByDataUltimoContattoDesc();
	}
}
