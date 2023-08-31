package EnergyServices.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import EnergyServices.Entities.Fattura;
import EnergyServices.Enum.StatoFattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {

	List<Fattura> findByClienteId(Long id);

	List<Fattura> findByStato(StatoFattura stato);

	List<Fattura> findByData(LocalDate data);

	List<Fattura> findByAnno(int anno);

	@Query(value = "SELECT f FROM Fattura f WHERE f.importo >= ?1 and f.importo <= ?2")
	List<Fattura> findByImporto(double min, double max);

}
