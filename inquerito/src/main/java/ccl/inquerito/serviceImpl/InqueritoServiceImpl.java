package ccl.inquerito.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccl.inquerito.model.InqueritoModel;
import ccl.inquerito.repository.InqueritoRepository;
import ccl.inquerito.service.InqueritoService;

@Service
public class InqueritoServiceImpl implements InqueritoService{
	
	@Autowired
	private InqueritoRepository inqueritoRepository;

	@Override
	public InqueritoModel novoInquerito(InqueritoModel inquerito) {
		
		return inqueritoRepository.save(inquerito);
	}

	@Override
	public long TotalQuestionariosEnviados() {
		
		return inqueritoRepository.count();
	}

	@Override
	public long TotalVisitantes() {
		return 0;
	}

	@Override
	public Double SatisfacaoMediaGeral() {
		return inqueritoRepository.calcularTaxaSatisfacaoMediaGeral();
	}

	@Override
	public long Ultimos30diasEnviados() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long Ultimos30diasVisitantes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double Ultimos30diasMediaGeral() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long NumVisitanteSatisfeito() {
		return inqueritoRepository.contarVisitantesSatisfeitos();
	}

	@Override
	public long NumVisitanteInsatisfeito() {
		return inqueritoRepository.contarVisitantesInsatisfeitos();
	}

	@Override
	public int satisfacaoExposicao(int valor) {
		return inqueritoRepository.VisitantesExposicao(valor);
	}

	@Override
	public int satisfacaoGuia(int valor) {
		return inqueritoRepository.VisitantesGuia(valor);
	}
	
	//SATISFACAO POR CATEGORIA
	@Override
	public int satisfacaoBilheteira(int valor) {
		return inqueritoRepository.VisitantesBilheiteria(valor);
	}

	@Override
	public int satisfacaoLoja(int valor) {
		return inqueritoRepository.VisitantesLoja(valor);
	}

	@Override
	public int satisfacaoCafetaria(int valor) {
		return inqueritoRepository.VisitantesCafetaria(valor);
	}

	@Override
	public int satisfacaoLimpeza(int valor) {
		return inqueritoRepository.VisitantesLimpeza(valor);
	}

	
}
