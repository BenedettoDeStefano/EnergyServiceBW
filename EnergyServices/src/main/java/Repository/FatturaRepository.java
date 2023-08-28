package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import EnergyServices.Entities.Fattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {

}
