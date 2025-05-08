package ccl.inquerito.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import ccl.inquerito.model.InqueritoModel;

@Service
public interface InqueritoService {

	public InqueritoModel novoInquerito(InqueritoModel iquerito);
	
	public long TotalQuestionariosEnviados();
	public long TotalVisitantes();
	public Double SatisfacaoMediaGeral();
		
	public long NumVisitanteSatisfeito();
	public long NumVisitanteInsatisfeito();
	
	//SATISFACAO POR CATEGORIA
	public int satisfacaoExposicao(int valor);
	public int satisfacaoGuia(int valor);
	public int satisfacaoBilheteira(int valor);
	public int satisfacaoLoja(int valor);
	public int satisfacaoCafetaria(int valor);
	public int satisfacaoLimpeza(int valor);
	
	//RELATORIO VISITANTES
	public Double pessoasComDeficiencia();
	public int pessoasComDeficienciaUltimosDias(LocalDate data);
	public String FaixaEtariaPredominante();
	public List<Integer> totalDeCadaFaixaEtaria();	
	public String FaixaEtariaMenor();
	public int ContaGenero(String valor);
	public int FaixaEtariaFranca();
	public int contaRecomendacao(String valor);
	
	//RELATORIO PROFISSIONAL
	public double PercentualRemuneradas(String valor);
	public double PercentualOcupacao(String valor);
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
	public int totalUltimosTrintasDias(LocalDate data);
	public int Ultimos30diasMediaGeral(LocalDate data);
	
	
}
