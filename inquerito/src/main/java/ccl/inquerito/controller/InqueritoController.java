package ccl.inquerito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import ccl.inquerito.model.InqueritoModel;
import ccl.inquerito.serviceImpl.InqueritoServiceImpl;
import ccl.inquerito.serviceImpl.VisitaServiceImpl;


@Controller
@RequestMapping("ccl")
public class InqueritoController {
	
	@Autowired
	private InqueritoServiceImpl inqueritoImpl;

	@Autowired
	private VisitaServiceImpl visitaImpl;

	@GetMapping("/inquerito")
	public String formulario(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(visitaImpl.contaVisita() <= 0)
			visitaImpl.inserirVisita();
		else
			visitaImpl.atualizaNumVisitas();
		
		return "index";
	}
	
	@PostMapping("/fomulario-inquerito-acao")
	public ResponseEntity<String> formularioAcao(@ModelAttribute("inquerito") InqueritoModel inquerito) {
		
		try {
			/*
			System.out.println(inquerito.getGrauSatisfacaoAtendimentoLoja());
			System.out.println(inquerito.getGrauSatisfacaoCafetaria());
			System.out.println(inquerito.getGrauSatisfacaoDeAtendimento());
			System.out.println(inquerito.getGrauSatisfacaoDeQualidade());
			System.out.println(inquerito.getGrauSatisfacaoInteracao());
			System.out.println(inquerito.getGrauSatisfacaoLimpeza());
			System.out.println(inquerito.getGrauSatisfacaoMenuCafetaria());
			System.out.println(inquerito.getGrauSatisfacaoProdutoLoja());*/		
			
	        inqueritoImpl.novoInquerito(inquerito);
	        return ResponseEntity.ok("Inquérito salvo com sucesso!");
	    } catch (IllegalArgumentException e) {
	        // Erros de validação, dados inválidos, etc.
	        return ResponseEntity.badRequest().body("Erro nos dados do formulário: " + e.getMessage());
	    } catch (Exception e) {
	        // Erros gerais, como falha no banco, NullPointerException, etc.
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Erro interno ao processar o inquérito: " + e.getMessage());
	    }
	}
	
}
