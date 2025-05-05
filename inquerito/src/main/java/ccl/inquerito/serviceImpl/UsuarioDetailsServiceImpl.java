package ccl.inquerito.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ccl.inquerito.model.UsuarioModel;
import ccl.inquerito.repository.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String telemovel) throws UsernameNotFoundException {

		UsuarioModel usuario = usuarioRepository.findByTelemovel(telemovel).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		//System.out.println("### Pass - "+usuario.getPassword()+" />>> Telef - "+usuario.getTelemovel());
		
		return new User(
		            usuario.getTelemovel(), // username
		            usuario.getPassword(),     // password (deve estar codificada com BCrypt)
		            List.of(new SimpleGrantedAuthority("ROLE_USER")) // ajuste conforme seus perfis
		        );
		
		/*
		return org.springframework.security.core.userdetails.User
	            .withUsername(usuario.getTelemovel())
	            .password(usuario.getPassword())
	            .roles(usuario.getRole().replace("ROLE_", ""))
	            .build();*/

	}

}
