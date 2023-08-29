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

import EnergyServices.Entities.Fattura;
import EnergyServices.PayLoad.FatturaPayLoad;
import EnergyServices.Service.FatturaService;

@RestController
@RequestMapping("/fattura")
public class FatturaController {

	private FatturaService fatturaService;

	@PostMapping
	public ResponseEntity<Fattura> createFattura(@RequestBody FatturaPayLoad fatturaPayload) {
		Fattura createdFattura = fatturaService.createFattura(fatturaPayload.toFattura());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdFattura);
	}

	@GetMapping("/{fatturaId}")
	public ResponseEntity<Fattura> getFatturaById(@PathVariable Long id) throws NotFoundException {
		Fattura fattura = fatturaService.getFatturaByID(id);
		if (fattura != null) {
			return ResponseEntity.ok(fattura);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{fatturaId}")
	public ResponseEntity<Fattura> updateFattura(@PathVariable Long id, @RequestBody FatturaPayLoad fatturaPayload) {
		Fattura updatedFattura = fatturaService.updateFatturaById(fatturaPayload.toFattura(), id);
		if (updatedFattura != null) {
			return ResponseEntity.ok(updatedFattura);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{fatturaId}")
	public ResponseEntity<Void> deleteFattura(@PathVariable Long id) throws NotFoundException {
		fatturaService.deleteFattura(id);
		return ResponseEntity.noContent().build();
	}

}
