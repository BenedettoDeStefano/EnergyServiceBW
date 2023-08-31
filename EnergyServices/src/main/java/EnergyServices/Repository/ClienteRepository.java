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


	List<Cliente> findAllByOrderByRagioneSocialeAsc(); // Ordine per Nome

	List<Cliente> findAllByOrderByFatturatoAnnualeDesc(); // Ordine per Fatturato Annuale

	List<Cliente> findAllByOrderByDataInserimentoDesc(); // Ordine per Data di Inserimento

	List<Cliente> findAllByOrderByDataUltimoContattoDesc(); // Ordine per Data di Ultimo Contatto


}
