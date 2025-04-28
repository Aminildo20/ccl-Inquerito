package ccl.inquerito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ccl.inquerito.model.InqueritoModel;

@Repository
public interface VisitaRepository extends JpaRepository<InqueritoModel, Long>{

}
