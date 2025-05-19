package ccl.inquerito.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ccl.inquerito.DTO.InqueritoDTO;
import ccl.inquerito.model.InqueritoModel;
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
//System.out.println("HA TRINTAIDS "+LocalDate.now().minusDays(30));
			
		model.addAttribute("titulo","Dashboard Admin");
		model.addAttribute("content","Dashboard Admin");
		model.addAttribute("totalEnviados", totalEnviados);
		//model.addAttribute("ultimosTrintaDias", totalUltimosTrintaDias);
		model.addAttribute("ultimosTrintaDiasPercentual", (totalUltimosTrintaDias * 100.0) / totalEnviados);
		model.addAttribute("totalVisitas", totalVisitas);
		model.addAttribute("TaxaSatisfacaoMedia", inqueritoImpl.SatisfacaoMediaGeral());
		model.addAttribute("Ultimos30diasMediaGeral", Ultimos30diasMediaGeral);
		
		//System.out.println(" MEDIA GERAL >>"+inqueritoImpl.SatisfacaoMediaGeral());
	
		long satisfeitos = inqueritoImpl.NumVisitanteSatisfeito();
		long insatisfeitos = inqueritoImpl.NumVisitanteInsatisfeito();
		Double percentual = (satisfeitos * 100.0) / totalEnviados;
		
		model.addAttribute("numVisitante", satisfeitos);
		model.addAttribute("numVisitanteInsatisfeito", insatisfeitos);
		model.addAttribute("percentual", percentual);
		
		//System.out.println(" percentual >>"+percentual);
		
		//DADOS DO GRAFICO DE SATISFACAO POR CATEGORIA
		//'Exposições', 'Guias', 'Bilheteira', 'Loja', 'Cafetaria', 'Limpeza'
		//DADOS DO GRAFICO DE SATISFACAO POR CATEGORIA
		List<Double> muitoSatisfeito = inqueritoImpl.calcularPercentuais(5, totalEnviados, true);
		List<Double> satisfeito = inqueritoImpl.calcularPercentuais(4, totalEnviados, false);
		List<Double> indiferente = inqueritoImpl.calcularPercentuais(3, totalEnviados, false);
		List<Double> insatisfeito = inqueritoImpl.calcularPercentuais(2, totalEnviados, false);
		List<Double> muitoInsatisfeito1 = inqueritoImpl.calcularPercentuais(1, totalEnviados, false);
		List<Double> muitoInsatisfeito2 = inqueritoImpl.calcularPercentuais(0, totalEnviados, false);
		
		//System.out.println(" >>> Insatisfeito - "+insatisfeito);
		
		List<Double> muitoInsatisfeito = inqueritoImpl.somarListas(muitoInsatisfeito1, muitoInsatisfeito2); 
		
        model.addAttribute("muitoSatisfeito", muitoSatisfeito);
        model.addAttribute("satisfeito", satisfeito);
        model.addAttribute("indiferente", indiferente);
        model.addAttribute("insatisfeito", insatisfeito);
        model.addAttribute("muitoInsatisfeito", muitoInsatisfeito);
		
		return "admin/dashboard-admin";
	}
	
	@GetMapping("/relatorio/geral")
	public String relatorioGeral(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {

		//Totais
		long totalEnviados = inqueritoImpl.TotalQuestionariosEnviados();

		long satisfeitos = inqueritoImpl.NumVisitanteSatisfeito();
		Double indiceSatisfeitos = inqueritoImpl.SatisfacaoMediaGeral();
		long insatisfeitos = inqueritoImpl.NumVisitanteInsatisfeito();
		
		long ulitmosDiasSatisfeito = inqueritoImpl.NumVisitanteSatisfeitoUltimosDias(LocalDateTime.now().minusDays(20));
		
		//
		//List<String> nivelSatisfacao = List.of("Muito Insatisfeito","Insatisfeito","Indiferente","Satisfeito","Muito Satisfeito");
		System.out.println("### ULTIMOS DIAS >>>> ");
		model.addAttribute("titulo","Relatório de Satisfação Geral");
		model.addAttribute("content","Relatório de Satisfação Geral");
		model.addAttribute("indiceSastifeitos", indiceSatisfeitos);
		model.addAttribute("indiceSastifeitosUltimosDias", inqueritoImpl.SatisfacaoMediaGeralUltimosDias(LocalDateTime.now().minusDays(30)));
		model.addAttribute("percentualSatisfeitos", (satisfeitos * 100.0) / totalEnviados);
		model.addAttribute("percentualInsatisfeitos", (insatisfeitos * 100.0) / totalEnviados);
		model.addAttribute("SatisfeitosUltimosDias", ulitmosDiasSatisfeito);
		model.addAttribute("percentualSatisfeitosUltimosDias", ulitmosDiasSatisfeito * 100.0 / totalEnviados);
		//model.addAttribute("InsatisfeitosUltimosDias", inqueritoImpl.NumVisitanteInsatisfeitoUltimosDias(LocalDate.now().minusDays(30)));
		model.addAttribute("satisfeitos", satisfeitos);
		model.addAttribute("insatisfeitos", insatisfeitos);
		model.addAttribute("percentualSatisfacao", satisfeitos * 100.0 / totalEnviados);
		
		
		//DADOS DO GRAFICO DE SATISFACAO POR CATEGORIA
		List<Double> muitoSatisfeito = inqueritoImpl.calcularPercentuais(5, totalEnviados, true);
		List<Double> satisfeito = inqueritoImpl.calcularPercentuais(4, totalEnviados, false);
		List<Double> indiferente = inqueritoImpl.calcularPercentuais(3, totalEnviados, false);
		List<Double> insatisfeito = inqueritoImpl.calcularPercentuais(2, totalEnviados, false);
		List<Double> muitoInsatisfeito1 = inqueritoImpl.calcularPercentuais(1, totalEnviados, false);
		List<Double> muitoInsatisfeito2 = inqueritoImpl.calcularPercentuais(0, totalEnviados, false);
		
		
		List<Double> muitoInsatisfeito = inqueritoImpl.somarListas(muitoInsatisfeito1, muitoInsatisfeito2); 

		
        model.addAttribute("muitoSatisfeito", muitoSatisfeito);
        model.addAttribute("satisfeito", satisfeito);
        model.addAttribute("indiferente", indiferente);
        model.addAttribute("insatisfeito", insatisfeito);
        model.addAttribute("muitoInsatisfeito", muitoInsatisfeito);
		
        //['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro']
        int anoAtual = LocalDateTime.now().getYear();
        List<String> mes = List.of("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
        
        List<Double> graficoDoMes = inqueritoImpl.MesPercentuais(anoAtual, totalEnviados);
     
        List<Integer> graficoInqueritoMes = List.of(inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-01"),inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-02"),
        									inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-03"), inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-04"),
        									inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-05"),inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-06"),
        									inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-07"),inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-08"),
        									inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-09"), inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-10"),
        									inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-11"), inqueritoImpl.qtdInqueritosDoMes(anoAtual+"-12"));
        

        int posicaoDoMesMaior = inqueritoImpl.mesMaiorDesempenho(graficoInqueritoMes);
        int posicaoDoMesMenor = inqueritoImpl.mesMenorDesempenho(graficoInqueritoMes);
        
        model.addAttribute("graficoInqueritoMes", graficoDoMes);
        model.addAttribute("mesMaiorDesempenho", mes.get(posicaoDoMesMaior));
        model.addAttribute("mesMenorDesempenho", mes.get(posicaoDoMesMenor));
        model.addAttribute("valorMesMaiorDesempenho", graficoInqueritoMes.get(posicaoDoMesMaior) * 100.0 / totalEnviados);
        model.addAttribute("valorMesMenorDesempenho", graficoInqueritoMes.get(posicaoDoMesMenor) * 100.0 / totalEnviados);
        
       // System.out.println(">>>MES MAIOR : " + mes.get(posicaoDoMesMaior)+" | Valor :"+graficoInqueritoMes.get(posicaoDoMesMaior));
       // System.out.println(">>>MES MENOR : " + mes.get(posicaoDoMesMenor)+" | valor :"+graficoInqueritoMes.get(posicaoDoMesMenor));
       // System.out.println("--------------------------------------");
       //System.out.println("Muito Satisfeito >>>> "+muitoSatisfeito);
		
		return "admin/relatorio-de-satisfacao-geral";
	}
	
	@GetMapping("/relatorio/situacao/profissional")
	public String relatorioSituacaoProfissional(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		int pessoasRemuneradas = inqueritoImpl.RemuneradasUltimosDias("sim",LocalDateTime.now().minusDays(30));
		int EstudadesUltimosdias = inqueritoImpl.OcupacaoUltimosDias("Estudante", LocalDateTime.now().minusDays(30));
		//
		int homensDesempregados = inqueritoImpl.ContaDesempregoPorGenero("Homem", "Nao");
		int homensEmpregados = inqueritoImpl.ContaDesempregoPorGenero("Homem", "sim");
		int mulheresDesempregadas = inqueritoImpl.ContaDesempregoPorGenero("Mulher", "Nao");
		int mulheresEmpregadas = inqueritoImpl.ContaDesempregoPorGenero("Mulher", "sim");
		String generoMaisDesempregado = "Homens";
		if(mulheresDesempregadas > homensDesempregados )
			generoMaisDesempregado = "Mulheres";
		
		int homenTotalEstudante = inqueritoImpl.ContaDesempregoPorGeneroEocupacao("Homem", "Estudante");
		int homenTotalAutonomo = inqueritoImpl.ContaDesempregoPorGeneroEocupacao("Homem", "Autonomo");
		int homenTotalAposentado = inqueritoImpl.ContaDesempregoPorGeneroEocupacao("Homem", "Aposentado");
		int mulherTotalEstudante = inqueritoImpl.ContaDesempregoPorGeneroEocupacao("Mulher", "Estudante");
		int mulherTotalAutonomo = inqueritoImpl.ContaDesempregoPorGeneroEocupacao("Mulher", "Autonomo");
		int mulherTotalAposentada = inqueritoImpl.ContaDesempregoPorGeneroEocupacao("Mulher", "Aposentado");
		//labels: ['Empregado', 'Desempregado', 'Estudante', 'Autônomo', 'Aposentado'],
		List<Integer> graficoDesempregoHomem = List.of(homensEmpregados, homensDesempregados, homenTotalEstudante, homenTotalAutonomo,homenTotalAposentado);
		List<Integer> graficoDesempregoMulher = List.of(mulheresEmpregadas, mulheresDesempregadas, mulherTotalEstudante, mulherTotalAutonomo,mulherTotalAposentada);
		
		//
		String FaixaEtariaPredominante = inqueritoImpl.FaixaEtariaPredominante();
		String FaixaEtariaMenor = inqueritoImpl.FaixaEtariaMenor();

		//'Sem Instrução', 'Fundamental', 'Médio', 'Superior', 'Pós-graduação'
		int empregadoSemInstrucao = inqueritoImpl.contaPorActividadeEnivelAcademico("sim","sem_instrucao");
		int empregadoFundamental = inqueritoImpl.contaPorActividadeEnivelAcademico("sim","ensino_fundamental_completo");
		int empregadoFundamentalIncompleto = inqueritoImpl.contaPorActividadeEnivelAcademico("sim","ensino_fundamental_incompleto");
		int empregadoMedio = inqueritoImpl.contaPorActividadeEnivelAcademico("sim","ensino_medio_completo");
		int empregadoMedioIncompleto = inqueritoImpl.contaPorActividadeEnivelAcademico("sim","ensino_medio_incompleto");
		int empregadoSuperior = inqueritoImpl.contaPorActividadeEnivelAcademico("sim","ensino_superior_completo");
		int empregadoSuperiorIncompleto = inqueritoImpl.contaPorActividadeEnivelAcademico("sim","ensino_superior_incompleto");
		int empregadoPosgraduacao = inqueritoImpl.contaPorActividadeEnivelAcademico("sim","pos_graduacao");
		//GRAFICO
		List<Integer> graficoEmpregados = List.of(empregadoSemInstrucao, empregadoFundamental+empregadoFundamentalIncompleto, empregadoMedio+empregadoMedioIncompleto, empregadoSuperior+empregadoSuperiorIncompleto, empregadoPosgraduacao);
		
		//'Sem Instrução', 'Fundamental', 'Médio', 'Superior', 'Pós-graduação'
		int desempregadoSemInstrucao = inqueritoImpl.contaPorActividadeEnivelAcademico("Nao","sem_instrucao");
		int desempregadoFundamental = inqueritoImpl.contaPorActividadeEnivelAcademico("Nao","ensino_fundamental_completo");
		int desempregadoFundamentalIncompleto = inqueritoImpl.contaPorActividadeEnivelAcademico("Nao","ensino_fundamental_incompleto");
		int desempregadoMedio = inqueritoImpl.contaPorActividadeEnivelAcademico("Nao","ensino_medio_completo");
		int desempregadoMedioIncompleto = inqueritoImpl.contaPorActividadeEnivelAcademico("Nao","ensino_medio_incompleto");
		int desempregadoSuperior = inqueritoImpl.contaPorActividadeEnivelAcademico("Nao","ensino_superior_completo");
		int desempregadoSuperiorIncompleto = inqueritoImpl.contaPorActividadeEnivelAcademico("Nao","ensino_superior_incompleto");
		int desempregadoPosgraduacao = inqueritoImpl.contaPorActividadeEnivelAcademico("Nao","pos_graduacao");
		//GRAFICO
		List<Integer> graficoDesempregados = List.of(desempregadoSemInstrucao, desempregadoFundamental+desempregadoFundamentalIncompleto, desempregadoMedio+desempregadoMedioIncompleto, desempregadoSuperior+desempregadoSuperiorIncompleto, desempregadoPosgraduacao);
		
		//'Sem Instrução', 'Fundamental', 'Médio', 'Superior', 'Pós-graduação'
		int estudanteSemInstrucao = inqueritoImpl.contaPorNivelAcademico("sem_instrucao");
		int estudanteFundamental = inqueritoImpl.contaPorNivelAcademico("ensino_fundamental_completo");
		int estudanteFundamentalIncompleto = inqueritoImpl.contaPorNivelAcademico("ensino_fundamental_incompleto");
		int estudanteMedio = inqueritoImpl.contaPorNivelAcademico("ensino_medio_completo");
		int estudanteMedioIncompleto = inqueritoImpl.contaPorNivelAcademico("ensino_medio_incompleto");
		int estudanteSuperior = inqueritoImpl.contaPorNivelAcademico("ensino_superior_completo");
		int estudanteSuperiorIncompleto = inqueritoImpl.contaPorNivelAcademico("ensino_superior_incompleto");
		int estudantePosgraduacao = inqueritoImpl.contaPorNivelAcademico("pos_graduacao");
		//GRAFICO
		List<Integer> graficoEstudante = List.of(estudanteSemInstrucao, estudanteFundamental+estudanteFundamentalIncompleto, estudanteMedio+estudanteMedioIncompleto, estudanteSuperior+estudanteSuperiorIncompleto, estudantePosgraduacao);
		
		
		//'Empregado', 'Estudante', 'Desempregado', 'Autônomo', 'Aposentado'
		List<Integer> graficoPorOcupacao = List.of(inqueritoImpl.TotalActividadeRemunerada("sim"), inqueritoImpl.contaPorOcupacao("Estudante"), inqueritoImpl.TotalActividadeRemunerada("Nao"), inqueritoImpl.contaPorOcupacao("Autonomo"), inqueritoImpl.contaPorOcupacao("Aposentado") );
		
		
		model.addAttribute("titulo","Relatório de Situação Profissional");
		model.addAttribute("percentualRemunerados",inqueritoImpl.PercentualRemuneradas("sim"));
		model.addAttribute("RemuneradosUltimosDias", pessoasRemuneradas);
		model.addAttribute("PercentualRemuneradosUltimosDias", pessoasRemuneradas * 100.0 / inqueritoImpl.TotalQuestionariosEnviados());
		model.addAttribute("percentualEstudante",inqueritoImpl.PercentualOcupacao("Estudante"));
		model.addAttribute("estudantesUltimosDias",EstudadesUltimosdias);
		model.addAttribute("percentualEstudantesUltimosDias",EstudadesUltimosdias * 100.0 / inqueritoImpl.TotalQuestionariosEnviados());
		model.addAttribute("generoMaisDesempregado",generoMaisDesempregado);	
		model.addAttribute("homensDesempregados",homensDesempregados);	
		model.addAttribute("homensEmpregados",homensEmpregados);	
		model.addAttribute("mulheresDesempregadas",mulheresDesempregadas);	
		model.addAttribute("mulheresEmpregadas",mulheresEmpregadas);
		model.addAttribute("graficoDesempregoHomem",graficoDesempregoHomem);	
		model.addAttribute("graficoDesempregoMulher",graficoDesempregoMulher);
		model.addAttribute("FaixaEtariaPredominante", FaixaEtariaPredominante);
		model.addAttribute("FaixaEtariaMenor", FaixaEtariaMenor);			
		model.addAttribute("percentualDesempregado", ((homensDesempregados+mulheresDesempregadas) * 100.0) / inqueritoImpl.TotalQuestionariosEnviados());	
		model.addAttribute("graficoEmpregados", graficoEmpregados);	
		model.addAttribute("graficoDesempregados", graficoDesempregados);	
		model.addAttribute("graficoEstudante",graficoEstudante );
		model.addAttribute("percentualEmpregado", ((homensEmpregados+mulheresEmpregadas) * 100.0) / inqueritoImpl.TotalQuestionariosEnviados());
		model.addAttribute("percentualAposentado", inqueritoImpl.PercentualOcupacao("Aposentado"));
		model.addAttribute("graficoPorOcupacao",graficoPorOcupacao );
		
		
		return "admin/relatorio-de-situacao-profissional";
	}
	
	@GetMapping("/relatorio/visitante")
	public String relatorioVisitantes(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		

		long totalEnviados = inqueritoImpl.TotalQuestionariosEnviados();
		Double percentualDeficiencia = inqueritoImpl.pessoasComDeficiencia();
		int DeficienciaUltimosDias = inqueritoImpl.pessoasComDeficienciaUltimosDias(LocalDateTime.now().minusDays(30));
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
		

		model.addAttribute("titulo","Relatório dos Visitantes");
		model.addAttribute("content","Relatório dos Visitantes");
		model.addAttribute("percentualDeficiencia", percentualDeficiencia);
		//model.addAttribute("deficienciaUltimosDias", DeficienciaUltimosDias);
		model.addAttribute("deficienciaUltimosDiasPercentual", DeficienciaUltimosDias * 100.0 / totalEnviados);
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
	
	@GetMapping("/relatorio/sessoes")
	public String sessoes(Model model,
            @RequestParam(value = "data", required = false) LocalDate data,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
		
		Page<InqueritoModel> inqueritoPage;
		
		if(data != null) {
	         inqueritoPage = inqueritoImpl.buscarPorData(page, size, data.atStartOfDay());//// data.atStartOfDay() Converte LocalDate para LocalDateTime
	 		model.addAttribute("dataFiltro", data);
	    }
		else		
			inqueritoPage = inqueritoImpl.listarAssociadoPaginacao(page, size);
//        inqueritoPage = inqueritoImpl.converterParaDTO(inqueritoPage);
       // Page<InqueritoDTO> dtoPage = inqueritoPage.map(inqueritoImpl::converterParaDTO);
		
		model.addAttribute("pagina", inqueritoPage.getNumber());
		model.addAttribute("totalPaginas", inqueritoPage.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("inqueritoPage", inqueritoPage);		

		model.addAttribute("titulo","Sessões");
		model.addAttribute("content","Sessão sessões");
	
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
