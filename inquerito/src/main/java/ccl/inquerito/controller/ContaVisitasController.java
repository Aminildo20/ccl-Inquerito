package ccl.inquerito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ccl.inquerito.serviceImpl.VisitaServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class ContaVisitasController {	

	@Autowired
	private VisitaServiceImpl visitaImpl;
	
	@GetMapping("")
	public String formulario(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(visitaImpl.contaVisita() <= 0)
			visitaImpl.inserirVisita();
		else
			visitaImpl.atualizaNumVisitas();
		
		return "index";
	}
}
