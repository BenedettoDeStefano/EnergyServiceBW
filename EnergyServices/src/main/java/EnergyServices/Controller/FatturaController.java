package EnergyServices.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

import EnergyServices.Entities.Fattura;
import EnergyServices.PayLoad.FatturaPayLoad;
import EnergyServices.Service.FatturaService;
import EnergyServices.auth.BadRequestException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/fattura")
public class FatturaController {

	@Autowired
	private FatturaService fatturaService;

	@GetMapping("search/{clienteId}")
	public ResponseEntity<List<FatturaPayLoad>> getFattureByClienteId(@PathVariable Long clienteId,
			@RequestParam(required = false) String stato, @RequestParam(required = false) String data,
			@RequestParam(required = false) Integer anno, @RequestParam(required = false) Double min,
			@RequestParam(required = false) Double max

	) throws NotFoundException {
		List<Fattura> fatture;
		if (clienteId != null) {
			fatture = fatturaService.getByCliente(clienteId);
		} else {
			fatture = fatturaService.getAllFatture();
		}
		List<Fattura> lista = new ArrayList<Fattura>();
		ricerca: {
		if (stato != null && !stato.isEmpty()) {
			lista.addAll(fatturaService.findByStato(stato));
			if (lista.isEmpty()) {
				break ricerca;
			}
		}
		if (data != null && !data.isEmpty()) {
			try {
				LocalDate date = LocalDate.parse(data, DateTimeFormatter.ofPattern("uuuu-MM-dd"));
				List<Fattura> found = fatturaService.findByData(date);
				if (lista.isEmpty()) {
					lista.addAll(found);

				} else {
					lista = lista.stream().filter(found::contains).toList();

				}
				if (lista.isEmpty()) {
					break ricerca;
				}

			} catch (DateTimeParseException e) {
				throw new IllegalArgumentException("formato data non valida per il campo data fattura: " + data);
			}
		}
		if (anno != null && anno >= 1970) {
			List<Fattura> found = fatturaService.findByAnno(anno);
			if (lista.isEmpty()) {
				lista.addAll(found);

			} else {
				lista = lista.stream().filter(found::contains).toList();

			}
			if (lista.isEmpty()) {
				break ricerca;
			}
		}
		if (min != null && max != null) {
			if (min >= max) {
				throw new IllegalArgumentException("Parametri per filtro su importo non validi.");
			} else {
				List<Fattura> found = fatturaService.findByImporto(min, max);
				if (lista.isEmpty()) {
					lista.addAll(found);

				} else {
					lista = lista.stream().filter(found::contains).toList();

				}
				if (lista.isEmpty()) {
					break ricerca;
				}
			}
		} else if (min != null || max != null) {
			throw new BadRequestException("Parametri su importo fattura non validi min e max.");
		}
	}

	if (!lista.isEmpty()) {
		fatture = fatture.stream().filter(lista::contains).toList();
	}

		if (fatture != null && !fatture.isEmpty()) {
			return ResponseEntity.ok(fatture.stream().map(FatturaPayLoad::new).toList());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<FatturaPayLoad> createFattura(@Valid @RequestBody FatturaPayLoad fatturaPayload) {
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
		if (fatturaService.getFatturaByID(fatturaId) == null) {
			return ResponseEntity.notFound().build();
		}
		fatturaService.deleteFattura(fatturaId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cancellata fattura con id: " + fatturaId);
	}

}
