package ccl.inquerito.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ccl.inquerito.model.InqueritoModel;
import ccl.inquerito.model.VisitasModel;

@Repository
public interface VisitaRepository extends JpaRepository<VisitasModel, Long>{
	
	Optional<VisitasModel> findByIdVisita(Long idVisita);
}
