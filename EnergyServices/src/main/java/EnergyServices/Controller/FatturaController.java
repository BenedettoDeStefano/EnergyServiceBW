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

import EnergyServices.Entities.Fattura;
import EnergyServices.PayLoad.FatturaPayLoad;
import EnergyServices.Service.FatturaService;

@RestController
@RequestMapping("/fattura")
public class FatturaController {

	@Autowired
	private FatturaService fatturaService;

	@GetMapping("search/{clienteId}")
	public ResponseEntity<List<FatturaPayLoad>> getFattureByClienteId(@PathVariable Long clienteId)
			throws NotFoundException {
		List<Fattura> fatture = fatturaService.getByCliente(clienteId);
		if (fatture != null && !fatture.isEmpty()) {
			return ResponseEntity.ok(fatture.stream().map(FatturaPayLoad::new).toList());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<FatturaPayLoad> createFattura(@RequestBody FatturaPayLoad fatturaPayload) {
		Fattura createdFattura = fatturaService.createFattura(fatturaPayload.toFattura());
		return ResponseEntity.status(HttpStatus.CREATED).body(new FatturaPayLoad(createdFattura));
	}

	@GetMapping("/{fatturaId}")
	public ResponseEntity<FatturaPayLoad> getFatturaById(@PathVariable Long fatturaId) throws NotFoundException {
		Fattura fattura = fatturaService.getFatturaByID(fatturaId);
		if (fattura != null) {
			return ResponseEntity.ok(new FatturaPayLoad(fattura));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{fatturaId}")
	public ResponseEntity<FatturaPayLoad> updateFattura(@PathVariable Long fatturaId,
			@RequestBody FatturaPayLoad fatturaPayload) {
		Fattura updatedFattura = fatturaService.updateFatturaById(fatturaPayload.toFattura(), fatturaId);
		if (updatedFattura != null) {
			return ResponseEntity.ok(new FatturaPayLoad(updatedFattura));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{fatturaId}")
	public ResponseEntity<String> deleteFattura(@PathVariable Long fatturaId) throws NotFoundException {
		fatturaService.deleteFattura(fatturaId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cancellata fattura con id: " + fatturaId);
	}

}
