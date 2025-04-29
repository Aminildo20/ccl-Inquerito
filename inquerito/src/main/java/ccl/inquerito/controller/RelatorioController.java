package ccl.inquerito.controller;

import java.util.List;

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
	
	@GetMapping("/login")
	public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {

		model.addAttribute("titulo", "CCL MAIN");
		return "admin/index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//Totais
		long total = inqueritoImpl.TotalQuestionariosEnviados();
		
		model.addAttribute("totalEnviados", total);
		model.addAttribute("totalVisitas", "totalVisitas1");
		model.addAttribute("TaxaSatisfacaoMedia", inqueritoImpl.SatisfacaoMediaGeral());
		
		//System.out.println(" MEDIA GERAL >>"+inqueritoImpl.SatisfacaoMediaGeral());
	
		long satisfeitos = inqueritoImpl.NumVisitanteSatisfeito();
		long insatisfeitos = inqueritoImpl.NumVisitanteInsatisfeito();
		Double percentual = (satisfeitos * 100.0) / total;
		
		model.addAttribute("numVisitante", satisfeitos);
		model.addAttribute("numVisitanteInsatisfeito", insatisfeitos);
		model.addAttribute("percentual", percentual);
		
		System.out.println(" percentual >>"+percentual);
		
		//DADOS DO GRAFICO DE SATISFACAO POR CATEGORIA
		//'Exposições', 'Guias', 'Bilheteira', 'Loja', 'Cafetaria', 'Limpeza'
		int valor=5;
		List<Integer> muitoSatisfeito = List.of(inqueritoImpl.satisfacaoExposicao(valor), inqueritoImpl.satisfacaoGuia(valor), inqueritoImpl.satisfacaoBilheteira(valor-1), inqueritoImpl.satisfacaoLoja(valor), inqueritoImpl.satisfacaoCafetaria(valor), inqueritoImpl.satisfacaoLimpeza(valor));
		valor=4;
		List<Integer> satisfeito = List.of(inqueritoImpl.satisfacaoExposicao(valor), inqueritoImpl.satisfacaoGuia(valor), inqueritoImpl.satisfacaoBilheteira(valor), inqueritoImpl.satisfacaoLoja(valor), inqueritoImpl.satisfacaoCafetaria(valor), inqueritoImpl.satisfacaoLimpeza(valor));
		valor=3;
		List<Integer> indiferente = List.of(inqueritoImpl.satisfacaoExposicao(valor), inqueritoImpl.satisfacaoGuia(valor), inqueritoImpl.satisfacaoBilheteira(valor), inqueritoImpl.satisfacaoLoja(valor), inqueritoImpl.satisfacaoCafetaria(valor), inqueritoImpl.satisfacaoLimpeza(valor));
		valor=2;
		List<Integer> insatisfeito = List.of(inqueritoImpl.satisfacaoExposicao(valor), inqueritoImpl.satisfacaoGuia(valor), inqueritoImpl.satisfacaoBilheteira(valor), inqueritoImpl.satisfacaoLoja(valor), inqueritoImpl.satisfacaoCafetaria(valor), inqueritoImpl.satisfacaoLimpeza(valor));
		valor=1;
		List<Integer> muitoInsatisfeito = List.of(inqueritoImpl.satisfacaoExposicao(valor), inqueritoImpl.satisfacaoGuia(valor), inqueritoImpl.satisfacaoBilheteira(valor), inqueritoImpl.satisfacaoLoja(valor), inqueritoImpl.satisfacaoCafetaria(valor), inqueritoImpl.satisfacaoLimpeza(valor));

        model.addAttribute("muitoSatisfeito", muitoSatisfeito);
        model.addAttribute("satisfeito", satisfeito);
        model.addAttribute("indiferente", indiferente);
        model.addAttribute("insatisfeito", insatisfeito);
        model.addAttribute("muitoInsatisfeito", muitoInsatisfeito);
		
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
		
		
		Double percentualDeficiencia = inqueritoImpl.pessoasComDeficiencia();
		String FaixaEtariaPredominante = inqueritoImpl.FaixaEtariaPredominante();
		String FaixaEtariaMenor = inqueritoImpl.FaixaEtariaMenor();
		int homens = inqueritoImpl.ContaGenero("Homem");
		int mulheres = inqueritoImpl.ContaGenero("Mulher");
		int outro = inqueritoImpl.ContaGenero("Outro");
		int prefiroNaoFalar = inqueritoImpl.ContaGenero("Nao");
		
		String generoMaiorParticipacao = "Homens";
		if(mulheres > homens )
			generoMaiorParticipacao = "Mulheres";

		List<Integer> graficoGeneros = List.of(mulheres, homens, outro, prefiroNaoFalar);
		List<Integer> graficoFaixaEtaria = inqueritoImpl.totalDeCadaFaixaEtaria();
		
		int contaRecomendaSim = inqueritoImpl.contaRecomendacao("Sim");
		int contaRecomendaNao = inqueritoImpl.contaRecomendacao("Nao");
		int contaRecomendaTalvez = inqueritoImpl.contaRecomendacao("Talvez");
		List<Integer> graficoRecomendacao = List.of(contaRecomendaSim, contaRecomendaNao, contaRecomendaTalvez);
		
		
		model.addAttribute("percentualDeficiencia", percentualDeficiencia);
		model.addAttribute("FaixaEtariaPredominante", FaixaEtariaPredominante);
		model.addAttribute("FaixaEtariaMenor", FaixaEtariaMenor);
		model.addAttribute("generoPredominante", generoMaiorParticipacao);
		model.addAttribute("totalGenero", homens+mulheres);
		model.addAttribute("homens", homens);
		model.addAttribute("mulheres", mulheres);
		model.addAttribute("grafGeneros", graficoGeneros);
		model.addAttribute("graficoFaixaEtaria", graficoFaixaEtaria);
		model.addAttribute("contaRecomendaSim", (contaRecomendaSim * 100) / inqueritoImpl.TotalQuestionariosEnviados());
		model.addAttribute("contaRecomendaNao", (contaRecomendaNao * 100) / inqueritoImpl.TotalQuestionariosEnviados());
		model.addAttribute("graficoRecomendacao", graficoRecomendacao);
		
		
		
		return "admin/relatorio-dos-visitantes";
	}
	
	
	
}
