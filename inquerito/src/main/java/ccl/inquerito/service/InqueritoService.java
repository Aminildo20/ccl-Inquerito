package ccl.inquerito.service;

import org.springframework.stereotype.Service;

import ccl.inquerito.model.InqueritoModel;

@Service
public interface InqueritoService {

	public InqueritoModel novoInquerito(InqueritoModel iquerito);
	
	public long TotalQuestionariosEnviados();
	public long TotalVisitantes();
	public Double SatisfacaoMediaGeral();
	
	public long Ultimos30diasEnviados();
	public long Ultimos30diasVisitantes();
	public Double Ultimos30diasMediaGeral();
		
	public long NumVisitanteSatisfeito();
	public long NumVisitanteInsatisfeito();
	
	//SATISFACAO POR CATEGORIA
	public int satisfacaoExposicao(int valor);
	public int satisfacaoGuia(int valor);
	public int satisfacaoBilheteira(int valor);
	public int satisfacaoLoja(int valor);
	public int satisfacaoCafetaria(int valor);
	public int satisfacaoLimpeza(int valor);
	
	
}
