package EnergyServices.Controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import EnergyServices.Entities.Cliente;
import EnergyServices.PayLoad.ClientePayLoad;
import EnergyServices.Service.ClienteService;
import jakarta.validation.Valid;

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
	public ResponseEntity<ClientePayLoad> createCliente(@Valid @RequestBody ClientePayLoad clientePayload) {
		Cliente createdCliente = clienteService.createCliente(clientePayload);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ClientePayLoad(createdCliente));
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<ClientePayLoad> updateCliente(@PathVariable Long clienteId,
			@RequestBody ClientePayLoad clientePayload) {
		Cliente updatedCliente = clienteService.updateClienteById(clientePayload, clienteId);
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

	// ------------------------------------------------------ Filtr
	@GetMapping("/fatturato")
	public ResponseEntity<List<Cliente>> getClientiByFatturato(@RequestParam int minFatturato,
			@RequestParam int maxFatturato) {
		List<Cliente> clienti = clienteService.findByFatturatoAnnuale(minFatturato, maxFatturato);
		return ResponseEntity.ok(clienti);
	}

	@GetMapping("/data-inserimento")
	public ResponseEntity<List<Cliente>> getClientiByDataInserimento(@RequestParam LocalDate data) {
		List<Cliente> clienti = clienteService.findByDataInserimento(data);
		return ResponseEntity.ok(clienti);
	}

	@GetMapping("/data-ultimo-contatto")
	public ResponseEntity<List<Cliente>> getClientiByDataUltimoContatto(@RequestParam LocalDate data) {
		List<Cliente> clienti = clienteService.findByDataUltimoContatto(data);
		return ResponseEntity.ok(clienti);
	}

	@GetMapping("/nome")
	public ResponseEntity<List<Cliente>> getClientiByParteDelNome(@RequestParam String parteDelNome) {
		List<Cliente> clienti = clienteService.findByParteDelNome(parteDelNome);
		return ResponseEntity.ok(clienti);
	}

	// -------------------------------------------------------------------

	@GetMapping("/nome_ordinato")
	public ResponseEntity<List<ClientePayLoad>> getClientiOrderedByName() {
		List<Cliente> clienti = clienteService.getAllClientiOrderedByName();
		return ResponseEntity.ok(clienti.stream().map(ClientePayLoad::new).toList());
	}

	@GetMapping("/fatturato-ordinato")
	public ResponseEntity<List<ClientePayLoad>> getClientiOrderedByFatturato() {
		List<Cliente> clienti = clienteService.getAllClientiOrderedByFatturato();
		return ResponseEntity.ok(clienti.stream().map(ClientePayLoad::new).toList());
	}

	@GetMapping("/data-inserimento-ordinata")
	public ResponseEntity<List<ClientePayLoad>> getClientiOrderedByDataInserimento() {
		List<Cliente> clienti = clienteService.getAllClientiOrderedByDataInserimento();
		return ResponseEntity.ok(clienti.stream().map(ClientePayLoad::new).toList());
	}

	@GetMapping("/data_ultimo_contatto-ordinata")
	public ResponseEntity<List<ClientePayLoad>> getClientiOrderedByDataUltimoContatto() {
		List<Cliente> clienti = clienteService.getAllClientiOrderedByDataUltimoContatto();
		return ResponseEntity.ok(clienti.stream().map(ClientePayLoad::new).toList());
	}
}
