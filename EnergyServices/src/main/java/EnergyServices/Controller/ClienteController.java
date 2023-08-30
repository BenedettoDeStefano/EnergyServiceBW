package EnergyServices.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EnergyServices.Entities.Cliente;
import EnergyServices.PayLoad.ClientePayLoad;
import EnergyServices.Service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClientePayLoad>> getClienti() throws NotFoundException {
		List<Cliente> clienti = clienteService.getAllClienti();
		if (clienti.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clienti.stream().map(ClientePayLoad::new).toList());
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<ClientePayLoad> getClienteById(@PathVariable Long clienteId) throws NotFoundException {
		Cliente cliente = clienteService.getClienteByID(clienteId);
		return ResponseEntity.ok(new ClientePayLoad(cliente));
	}

	@PostMapping
	public ResponseEntity<ClientePayLoad> createCliente(@RequestBody ClientePayLoad clientePayload) {
		Cliente createdCliente = clienteService.createCliente(clientePayload.toCliente());
		return ResponseEntity.status(HttpStatus.CREATED).body(new ClientePayLoad(createdCliente));
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<ClientePayLoad> updateCliente(@PathVariable Long clienteId,
			@RequestBody ClientePayLoad clientePayload) {
		Cliente updatedCliente = clienteService.updateClienteById(clientePayload.toCliente(), clienteId);
		return ResponseEntity.ok(new ClientePayLoad(updatedCliente));
	}

	@DeleteMapping("/{clientId}")
	public ResponseEntity<String> deleteCliente(@PathVariable Long clientId) throws NotFoundException {
		if (clienteService.getClienteByID(clientId) == null) {
			return ResponseEntity.notFound().build();
		}
		clienteService.deleteCliente(clientId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cancellato cliente con id: " + clientId);
	}

}
