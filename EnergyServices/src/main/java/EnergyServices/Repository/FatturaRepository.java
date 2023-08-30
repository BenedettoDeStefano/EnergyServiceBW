package EnergyServices.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import EnergyServices.Entities.Fattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {

	List<Fattura> findByClienteId(Long id);

}
