package EnergyServices.Controller;

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
@RequestMapping("/clienti")
public class ClienteController {

	private ClienteService clienteService;

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) throws NotFoundException {
		Cliente cliente = clienteService.getClienteByID(id);
		return ResponseEntity.ok(cliente);
	}

	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody ClientePayLoad clientePayload) {
		Cliente createdCliente = clienteService.createCliente(clientePayload.toCliente());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClientePayLoad clientePayload) {
		Cliente updatedCliente = clienteService.updateClienteById(clientePayload.toCliente(), id);
		return ResponseEntity.ok(updatedCliente);
	}

	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id) throws NotFoundException {
		clienteService.deleteCliente(id);
		return ResponseEntity.noContent().build();
	}

}
