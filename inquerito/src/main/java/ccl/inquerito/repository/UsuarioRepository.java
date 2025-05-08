package ccl.inquerito.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ccl.inquerito.model.UsuarioModel;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

	Optional<UsuarioModel> findByTelemovel(String telemovel);
	Optional<UsuarioModel> findByEmail(String email);
}
