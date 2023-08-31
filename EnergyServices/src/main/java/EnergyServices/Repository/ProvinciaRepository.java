package EnergyServices.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import EnergyServices.Entities.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{
	
	List<Provincia> findByProvincia(String provincia);
}
