package ccl.inquerito.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ccl.inquerito.model.UsuarioModel;

@Service
public interface UsuarioService {
	
	public UsuarioModel novoUsuario(UsuarioModel usuario);
	public Optional<UsuarioModel> buscaPorEmail(String email);
	public String gerarCodigo();
}
