package EnergyServices.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import EnergyServices.Entities.Cliente;
import EnergyServices.Repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

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

	public Cliente updateClienteById(Cliente nuovoCliente, Long id) {
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

			return clienteRepository.save(clienteTrovato);
		} else {
			return null;
		}
	}

	public Cliente createCliente(Cliente cliente) {
		return saveCliente(cliente);
	}

}
