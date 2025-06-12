package ccl.inquerito.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ccl.inquerito.model.BubbleChartDTO;
import ccl.inquerito.model.InqueritoModel;

@Service
public interface InqueritoService {

	public InqueritoModel novoInquerito(InqueritoModel iquerito);
	
	public long TotalQuestionariosEnviados();
	public long TotalVisitantes();
	public Double SatisfacaoMediaGeral();
	public Double SatisfacaoMediaGeralUltimosDias( LocalDateTime data);
	
	//---- #######
	//RELATORIO Demografico
	public Double pessoasComDeficiencia();
	public int pessoasComDeficienciaUltimosDias(LocalDateTime data);
	public String FaixaEtariaPredominante();
	public List<Integer> totalDeCadaFaixaEtaria();
	public List<Integer> totalDeCadaResidencia();
	public List<Integer> totalNivelAcademico();
	public List<Integer> totalDeficiencia();
	public List<Integer> totalOcupacao();
	public List<Integer> totalProposito();
	public List<BubbleChartDTO> totalPercepcoes();
	public List<Integer> totalExperiencias();
	public List<Integer> totalOrigemInfo();
	public List<Integer> totalSatisfacaoPrecoDoBilhete();
	public List<Integer> totalAcessoQuinzenal();
	public int servicoAdicional(String valor);
	public int servicoAdicionalCafetaria(int valor, int valor2);
	public int servicoAdicionalLoja(int valor, int valor2);
	public int recomendaCcl(String valor);
	public String FaixaEtariaMenor();
	public int ContaGenero(String valor);
	public int FaixaEtariaFranca();
	public int contaRecomendacao(String valor);
	
	//------ ######## ------
	//GRAU DE SATISFACAO
	public Double indiceSatisfacao();
	public Double indiceSatisfacaoUltimosDias(LocalDateTime data);
	public long NumVisitanteSatisfeito();
	public long NumVisitanteInsatisfeito();
	public long NumVisitanteSatisfeitoUltimosDias(LocalDateTime data);
	public long NumVisitanteInsatisfeitoUltimosDias(LocalDateTime data);
	
	//SATISFACAO POR CATEGORIA
	public List<Integer> totalSatisfacaoExposicao();
	public List<Integer> totalSatisfacaoAtendimento();
	public List<Integer> totalSatisfacaoInteracao();

	public List<Integer> totalSatisfacaoLimpeza();
	public List<Integer> totalSatisfacaoCafetaria();
	public List<Integer> totalSatisfacaoMenuCafetaria();
	public List<Integer> totalSatisfacaoAtendimentoLoja();
	public List<Integer> totalSatisfacaoProdutoLoja();
	
	
	
	public int satisfacaoExposicao(int valor);
	public int satisfacaoGuia(int valor);
	public int satisfacaoBilheteira(int valor);
	public int satisfacaoLoja(int valor);
	public int satisfacaoCafetaria(int valor);
	public int satisfacaoLimpeza(int valor);
	
	
	
	
	
	
	
	
	
	
	
	
	//RELATORIO PROFISSIONAL
	public double PercentualRemuneradas(String valor);
	public int RemuneradasUltimosDias(String valor, LocalDateTime data);
	public double PercentualOcupacao(String valor);
	public int OcupacaoUltimosDias(String valor, LocalDateTime data);
	public int ContaDesempregoPorGenero(String genero, String actividade);
	public int ContaDesempregoPorGeneroEocupacao(String genero, String ocupacao);
	public int contaPorActividadeEnivelAcademico(String valorActividade, String nivelAcademico);
	public int contaPorNivelAcademico(String nivelAcademico);
	public int contaPorOcupacao(String valor);
	public int TotalActividadeRemunerada(String valor);
	
	//GERAL
	public int qtdInqueritosDoMes(String mesAno);
	public int mesMaiorDesempenho(List<Integer> listaMes);
	public int mesMenorDesempenho(List<Integer> listaMes);
	public int totalUltimosTrintasDias(LocalDateTime data);
	public Double Ultimos30diasMediaGeral(LocalDateTime data);
	
	
	
	//SESSÕES
	public Page<InqueritoModel> listarAssociadoPaginacao(int page, int size);
	public Optional<InqueritoModel> buscaPorId(Long idInquerito);
	public Page<InqueritoModel> buscarPorData(int page, int size, LocalDateTime data);
	public List<String> buscarTodoEmail();
	
	
}
