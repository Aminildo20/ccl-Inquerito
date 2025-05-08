package ccl.inquerito.serviceImpl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccl.inquerito.model.UsuarioModel;
import ccl.inquerito.repository.UsuarioRepository;
import ccl.inquerito.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public Optional<UsuarioModel> buscaPorEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UsuarioModel novoUsuario(UsuarioModel usuario) {
		return userRepository.save(usuario);
	}
	
	@Override
	public String gerarCodigo() {
	    return String.format("%06d", new Random().nextInt(999999));
	}
	
}
