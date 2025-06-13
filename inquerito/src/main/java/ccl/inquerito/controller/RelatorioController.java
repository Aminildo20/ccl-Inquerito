package ccl.inquerito.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ccl.inquerito.DTO.InqueritoDTO;
import ccl.inquerito.model.BubbleChartDTO;
import ccl.inquerito.model.InqueritoModel;
import ccl.inquerito.model.UsuarioModel;
import ccl.inquerito.repository.UsuarioRepository;
import ccl.inquerito.serviceImpl.InqueritoServiceImpl;
import ccl.inquerito.serviceImpl.VisitaServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class RelatorioController {

	@Autowired
	private InqueritoServiceImpl inqueritoImpl;	

	@Autowired
	private VisitaServiceImpl visitaImpl;
	
	@GetMapping("/login")
	public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		model.addAttribute("titulo", "CCL MAIN");
		return "admin/index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//Totais
		long totalEnviados = inqueritoImpl.TotalQuestionariosEnviados();
		long totalVisitas = visitaImpl.totalVisita();
		int totalUltimosTrintaDias = inqueritoImpl.totalUltimosTrintasDias(LocalDateTime.now().minusDays(30));
		Double Ultimos30diasMediaGeral = inqueritoImpl.Ultimos30diasMediaGeral(LocalDateTime.now().minusDays(30));
			
		model.addAttribute("titulo","Dashboard Admin");
		model.addAttribute("content","Dashboard Admin");
		model.addAttribute("totalEnviados", totalEnviados);
		model.addAttribute("ultimosTrintaDiasPercentual", (totalUltimosTrintaDias * 100.0) / totalEnviados);
		model.addAttribute("totalVisitas", totalVisitas);
		model.addAttribute("TaxaSatisfacaoMedia", inqueritoImpl.SatisfacaoMediaGeral());
		model.addAttribute("Ultimos30diasMediaGeral", Ultimos30diasMediaGeral);
		
		//GRAFICO GENERO
		int homens = inqueritoImpl.ContaGenero("Homem");
		int mulheres = inqueritoImpl.ContaGenero("Mulher");
		int outro = inqueritoImpl.ContaGenero("Outro");
		int prefiroNaoFalar = inqueritoImpl.ContaGenero("Nao");
		
		model.addAttribute("homens", homens);
		model.addAttribute("mulheres", mulheres);
		model.addAttribute("outro", outro);
		model.addAttribute("prefiroNaoFalar", prefiroNaoFalar);
		
		return "admin/dashboard-admin";
	}
	
	
	//------ RELATORIO DEMOGRAFICO
	@GetMapping("/relatorio/demografico")
	public String relatorioDemografico(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
	
		model.addAttribute("titulo","Relatório dos visitantes");
		model.addAttribute("content","Relatório dos visitantes");
		
		//--
		long totalEnviados = inqueritoImpl.TotalQuestionariosEnviados();
		int homens = inqueritoImpl.ContaGenero("Homem");
		int mulheres = inqueritoImpl.ContaGenero("Mulher");		
		String generoMaiorParticipacao = "Homens";
		if(mulheres > homens )
			generoMaiorParticipacao = "Mulheres";		
		
		model.addAttribute("percentualDeficiencia",inqueritoImpl.pessoasComDeficiencia());
		model.addAttribute("deficienciaUltimosDias",inqueritoImpl.pessoasComDeficienciaUltimosDias(LocalDateTime.now().minusDays(30)));
		model.addAttribute("faixaEtariaPredominante",inqueritoImpl.FaixaEtariaPredominante());
		model.addAttribute("generoMaiorParticipacao",generoMaiorParticipacao);
		//------- GRAFICO GENERO ----------demografia//
		model.addAttribute("homens", homens);
		model.addAttribute("mulheres", mulheres);
		model.addAttribute("outro", inqueritoImpl.ContaGenero("Outro"));
		model.addAttribute("prefiroNaoFalar", inqueritoImpl.ContaGenero("Nao"));
		model.addAttribute("totalGenero", homens+mulheres);
		
		//--------GRAFICO FAIXA ETARIA ---------------//	
		model.addAttribute("graficoFaixaEtaria", inqueritoImpl.totalDeCadaFaixaEtaria());
		model.addAttribute("totalEnviados", totalEnviados);
		model.addAttribute("faixasEtarias", inqueritoImpl.listaDeFaixaEtaria());

		//#-------- GRAFICO Distribuição Geográfica --------
		model.addAttribute("graficoResidencia", inqueritoImpl.totalDeCadaResidencia());
		model.addAttribute("listaResidencia", inqueritoImpl.listaDeResidencias());
		
		//#-------- GRAFICO NIVEL ESCOLARIDADE ------
		model.addAttribute("graficoEscolaridade", inqueritoImpl.totalNivelAcademico());
		model.addAttribute("listaEscolaridade", inqueritoImpl.listaDeNivelAcademico());
		
		//#-------- GRAFICO DEFICIENCIA ------
		model.addAttribute("graficoDeficiencia", inqueritoImpl.totalDeficiencia());
		model.addAttribute("listaDeficianecia", inqueritoImpl.listaDeficiencia());
		
		//#-------- GRAFICO SITUACAO PROFISSIONAL ------
		model.addAttribute("graficoOcupacao", inqueritoImpl.totalOcupacao());
		model.addAttribute("listaOcupacao", inqueritoImpl.listaOcupacoes());
		
		return "admin/relatorio-demografico";
	}
	
	
	//------ RELATORIO EXPERIENCIA DO CLIENTE
	@GetMapping("/relatorio/experiencia-do-cliente")
	public String relatorioExperienciaDoCliente(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		model.addAttribute("titulo","Relatório de situação profissional");
		model.addAttribute("content","Relatório de situação profissional");
		//-------------------------
		int pessoasRemuneradas = inqueritoImpl.RemuneradasUltimosDias("sim",LocalDateTime.now().minusDays(30));
		int EstudadesUltimosdias = inqueritoImpl.OcupacaoUltimosDias("Estudante", LocalDateTime.now().minusDays(30));		
		model.addAttribute("percentualRemunerados",inqueritoImpl.PercentualRemuneradas("sim"));
		model.addAttribute("PercentualRemuneradosUltimosDias", pessoasRemuneradas * 100.0 / inqueritoImpl.TotalQuestionariosEnviados());
		model.addAttribute("percentualEstudante",inqueritoImpl.PercentualOcupacao("Estudante"));
		model.addAttribute("percentualEstudantesUltimosDias",EstudadesUltimosdias * 100.0 / inqueritoImpl.TotalQuestionariosEnviados());
		model.addAttribute("FaixaEtariaPredominante", inqueritoImpl.FaixaEtariaPredominante());
		//----------------------------
		
		//--------### GRAFICO ACESSO DIARIO AO CCL--------------------
		model.addAttribute("graficoAcessoDiario", inqueritoImpl.totalAcessoQuinzenal());
		model.addAttribute("ListaAcessoQuinzenal", inqueritoImpl.listaAcessoQuinzenal());
		List<String> mes = List.of("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
		model.addAttribute("labelsQuinzenais", mes);
		
		List<String> labels = new ArrayList<>();
		for (int i = 15; i >= 1; i--) {
		    LocalDate dia = LocalDate.now().minusDays(i);
		    String diaStr = dia.format(DateTimeFormatter.ofPattern("dd", new Locale("pt", "BR")));
		    String mesStr = dia.format(DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR")));
		    mesStr = mesStr.substring(0, 1).toUpperCase() + mesStr.substring(1);
		    String dataFormatada = diaStr + " - " + mesStr;		    
		    labels.add(dataFormatada);
		}
		model.addAttribute("labelsQuinzenais", labels);
		
		//----------------------------
		//--------### GRAFICO PROPOSITO DA VISITA--------------------
		model.addAttribute("graficoProposito", inqueritoImpl.totalProposito());
		model.addAttribute("listaProposito", inqueritoImpl.listaProposito());	
		
		//----------------------------
		//--------### GRAFICO COMO AS PESSOAS DESCREVEM A CCL--------------------
		model.addAttribute("graficoPercepcoes", inqueritoImpl.totalPercepcoes());
		model.addAttribute("listaPercepcoes", inqueritoImpl.listaPercepcoes());
		model.addAttribute("listaPercepcaoTotal", inqueritoImpl.ListaPercepcaoOrdenada());	
		
		//------------------------------------------------------------------------
		//--------### GRAFICO EXPERIENCIAS CCL--------------------		
		model.addAttribute("graficoExperiencia", inqueritoImpl.totalExperiencias());
		model.addAttribute("listaExperiencia", inqueritoImpl.listaExperiencias());

		//------------------------------------------------------------------------
		//--------### GRAFICO CONHECIMENTO DA CCL--------------------		
		model.addAttribute("graficoOrigemInfo", inqueritoImpl.totalOrigemInfo());
		model.addAttribute("listaOrigemInfo", inqueritoImpl.listaOrigemInfo());
		
		//------------------------------------------------------------------------
		//--------### GRAFICO PRECO DO BILHETEL--------------------		
		model.addAttribute("graficoSatisfacaoPrecoDoBilhete", inqueritoImpl.totalSatisfacaoPrecoDoBilhete());
		model.addAttribute("listaSatisfacaoPrecoDoBilhete", inqueritoImpl.listaSatisfacaoPrecoDoBilhete());

		//------------------------------------------------------------------------
		//--------### GRAFICO SERVICO ADICIONALL-------------------cafetara/sim *100
		long totalInqueritos = inqueritoImpl.TotalQuestionariosEnviados();
		int totalPeopleLoja =  inqueritoImpl.servicoAdicionalLoja(0,0);
		int totalPeopleCafetaria = inqueritoImpl.servicoAdicionalCafetaria(0,0);
		int servicoSim = inqueritoImpl.servicoAdicional("Sim. Qual?");
		
		model.addAttribute("ServicoAdicionalSim",servicoSim);
		model.addAttribute("ServicoAdiconalNao", inqueritoImpl.servicoAdicional("Não"));
		model.addAttribute("totalPeopleLoja", totalPeopleLoja);
		model.addAttribute("percentagemPeopleLoja", totalPeopleLoja * 100.0 / servicoSim);
		model.addAttribute("totalPeopleCafetaria", totalPeopleCafetaria);
		model.addAttribute("percentagemPeopleCafetaria", totalPeopleCafetaria * 100.0 / servicoSim);
		
		
		//------------------------------------------------------------------------
		//--------### GRAFICO RECOMENDA CCL-------------------
		model.addAttribute("recomendaSim",inqueritoImpl.recomendaCcl("Sim, definitivamente"));
		model.addAttribute("recomendaNao", inqueritoImpl.recomendaCcl("Não"));
		model.addAttribute("recomendaTalvez", inqueritoImpl.recomendaCcl("Talvez"));
		
		
		
		return "admin/relatorio-da-experiencia-do-cliente";
	}
	
	//------ RELATORIO GRAU SATISFACAO
	@GetMapping("/relatorio/relatorio-de-satisfacao")
	public String relatorioSatisfacao(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
	
		model.addAttribute("titulo","Relatório de satisfação Geral");
		model.addAttribute("content","Relatório de satisfação Geral");
		//------------------------------------------------------------
		long totalEnviados = inqueritoImpl.TotalQuestionariosEnviados();
		
		model.addAttribute("indiceSatisfacao", inqueritoImpl.indiceSatisfacao() * 20); // Resultado entre 0% e 100%
		model.addAttribute("indiceSatisfacaoUltimosDias", inqueritoImpl.indiceSatisfacaoUltimosDias(LocalDateTime.now().minusDays(30)));
		
		model.addAttribute("percentualInsatisfeito", inqueritoImpl.NumVisitanteSatisfeito() * 100.0 / totalEnviados);
		model.addAttribute("percentualSatisfeito", inqueritoImpl.NumVisitanteInsatisfeito() * 100.0 / totalEnviados);
		model.addAttribute("NumVisitanteSatisfeitoUltimosDias", inqueritoImpl.NumVisitanteSatisfeitoUltimosDias(LocalDateTime.now().minusDays(30)));
		model.addAttribute("NumVisitanteInsatisfeitoUltimosDias", inqueritoImpl.NumVisitanteInsatisfeitoUltimosDias(LocalDateTime.now().minusDays(30)));
		

		List<String> listaGrauSatisfacao = inqueritoImpl.listaGrauSatisfacao();
		model.addAttribute("listaDeGrauSatisfacao",listaGrauSatisfacao);
		//-------------------------
		
		//------ DADOS DOS GRAFICOS
		model.addAttribute("graficoExposicao", inqueritoImpl.totalSatisfacaoExposicao());
		model.addAttribute("graficoAtendimento", inqueritoImpl.totalSatisfacaoAtendimento());
		model.addAttribute("graficoInteracao", inqueritoImpl.totalSatisfacaoInteracao());
		

		model.addAttribute("graficoLimpeza", inqueritoImpl.totalSatisfacaoLimpeza());
		model.addAttribute("graficoCafetaria", inqueritoImpl.totalSatisfacaoCafetaria());
		model.addAttribute("graficoMenuCafetaria", inqueritoImpl.totalSatisfacaoMenuCafetaria());
		model.addAttribute("graficoAtendimentoLoja", inqueritoImpl.totalSatisfacaoAtendimentoLoja());
		model.addAttribute("graficoProdutoLoja", inqueritoImpl.totalSatisfacaoProdutoLoja());
		
		return "admin/relatorio-de-satisfacao";
	}
	
	
	
	@GetMapping("/relatorio/sessoes")
	public String sessoes(Model model,
            @RequestParam(value = "data", required = false) LocalDate data,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
		
		Page<InqueritoModel> inqueritoPage;
		List<String> listaRelatorio = inqueritoImpl.buscarTodoEmail();
        List<String> mes = List.of(" ","Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");		
		
		if(data != null) {
	        inqueritoPage = inqueritoImpl.buscarPorData(page, size, data.atStartOfDay());//// data.atStartOfDay() Converte LocalDate para LocalDateTime
	 		model.addAttribute("dataFiltro", data);
	    }
		else		
			inqueritoPage = inqueritoImpl.listarAssociadoPaginacao(page, size);
		
		model.addAttribute("pagina", inqueritoPage.getNumber());
		model.addAttribute("totalPaginas", inqueritoPage.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("inqueritoPage", inqueritoPage);
        model.addAttribute("mesLabel", mes);		

		model.addAttribute("titulo","Sessões");
		model.addAttribute("content","Sessão sessões");
		model.addAttribute("lista",listaRelatorio);
	
		return "admin/sessoes";
	}
	
	
	@GetMapping("/relatorio/sessao-individual/{idInquerito}")
	public String sessoesIndividual(@PathVariable Long idInquerito, HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
	
		InqueritoDTO inqueritoDto = inqueritoImpl.converterParaDTO(inqueritoImpl.buscaPorId(idInquerito).orElse(null));		
		
		model.addAttribute("titulo","Sessão individual");
		model.addAttribute("content","Sessão Individual");
		model.addAttribute("inquerito",inqueritoDto);
		
		return "admin/sessao-idividual";
	}
	
}
