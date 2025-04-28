package ccl.inquerito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ccl.inquerito.serviceImpl.InqueritoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class RelatorioController {

	@Autowired
	private InqueritoServiceImpl inqueritoImpl;	
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "admin/dashboard-admin";
	}
	
	@GetMapping("/relatorio/geral")
	public String relatorioGeral(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "admin/relatorio-de-satisfacao-geral";
	}
	
	@GetMapping("/relatorio/situacao/profissional")
	public String relatorioSituacaoProfissional(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "admin/relatorio-de-situacao-profissional";
	}
	
	@GetMapping("/relatorio/visitante")
	public String relatorioVisitantes(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "admin/relatorio-dos-visitantes";
	}
	
	
	
}
