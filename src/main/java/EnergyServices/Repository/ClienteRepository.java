package EnergyServices.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import EnergyServices.Entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByFatturatoAnnualeBetween(int fatturatoMin, int fatturatoMax);

	List<Cliente> findByDataInserimento(LocalDate data);

	List<Cliente> findByDataUltimoContatto(LocalDate data);

	List<Cliente> findByNomeContattoContainingIgnoreCase(String parteDelNome);

}
