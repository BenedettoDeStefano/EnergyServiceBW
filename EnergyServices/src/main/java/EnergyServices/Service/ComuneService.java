package EnergyServices.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EnergyServices.Entities.Comune;
import EnergyServices.Repository.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	private ComuneRepository repo;

	public Comune getById(Long id) {
		return this.repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id comune non registrato: " + id));
	}

}
