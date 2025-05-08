package ccl.inquerito.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ccl.inquerito.model.UsuarioModel;
import ccl.inquerito.service.EmailService;
import ccl.inquerito.serviceImpl.UsuarioServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("conta")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuarioImpl;
	
	@Autowired
	private EmailService emailService;


	@GetMapping("/esqueceu-senha")
	public String esqueceu(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		model.addAttribute("titulo", "Esqueceu a senha");
		return "seguranca/esqueceu-a-senha";
	}

	@PostMapping("/codigo-de-seguranca-page")
	public String codigoSeguranca(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		model.addAttribute("titulo", "Esqueceu a senha");
		return "seguranca/codigo-de-seguranca";
	}
	
	
	@PostMapping("/codigo-de-seguranca")
    @ResponseBody	
	public int codigoSegurancaAcao(@RequestParam(required = true) String email, HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {

		System.out.println(">>> RECEBI O email : "+email);
		
		Optional<UsuarioModel> optional = usuarioImpl.buscaPorEmail(email);		
		if (optional.isEmpty()) {
			System.out.println(">>>>> PEGUEI -- "+optional.get().getEmail());
	        return 0;
	    }
		
		UsuarioModel usuario = optional.get();
	    String codigo = usuarioImpl.gerarCodigo();
	    usuario.setCodigoRecuperacao(codigo);
	    usuario.setExpiracaoCodigoRecuperacao(LocalDateTime.now().plusMinutes(15));
	    usuarioImpl.novoUsuario(usuario);
		
	 // Enviar por e-mail
	   //emailService.enviarCodigo(email, codigo);
	    
		model.addAttribute("titulo", "Codigo de seguranca");
		//return "seguranca/codigo-de-seguranca";
		return 2;
	}
	
	@GetMapping("/nova-senha")
	public String novaSenha(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		model.addAttribute("titulo", "Codigo de seguranca");
		return "seguranca/criar-nova-senha";
	}
	
	@PostMapping("/nova-senha-acao")
	public ResponseEntity<String> novaSenhaAcao(@RequestParam String email, @RequestParam String codigo,
	        @RequestParam String novaSenha) {
		
		Optional<UsuarioModel> optional = usuarioImpl.buscaPorEmail(email);
	    if (optional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email não encontrado.");
	    }
	    
	    UsuarioModel usuario = optional.get();

	    if (!codigo.equals(usuario.getCodigoRecuperacao())) {
	        return ResponseEntity.badRequest().body("Código não corresponde");
	    }
	    if (usuario.getExpiracaoCodigoRecuperacao().isBefore(LocalDateTime.now())) {
	        return ResponseEntity.badRequest().body("Código expirado.");
	    }

	    // Atualiza a senha
	    String senhaCriptografada = new BCryptPasswordEncoder().encode(novaSenha);
	    usuario.setPassword(senhaCriptografada);
	    usuario.setCodigoRecuperacao(null);
	    usuario.setExpiracaoCodigoRecuperacao(null);

	    usuarioImpl.novoUsuario(usuario);

	    return ResponseEntity.ok("Senha redefinida com sucesso.");		
	}
	
}
