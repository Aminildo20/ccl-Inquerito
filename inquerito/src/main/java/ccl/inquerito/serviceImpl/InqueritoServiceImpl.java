package ccl.inquerito.serviceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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
		
		inquerito.setDataCriacao(LocalDate.now());
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
		Double media = inqueritoRepository.calcularTaxaSatisfacaoMediaGeral();
	    return media != null ? media : 0.0;
		//return inqueritoRepository.calcularTaxaSatisfacaoMediaGeral();
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

	@Override
	public Double pessoasComDeficiencia() {
		return  (inqueritoRepository.pessoasComDeficiencia() * 100) / (double) inqueritoRepository.count();
	}

	@Override
	public String FaixaEtariaPredominante() {

		List<String> faixa = List.of("15-19","20-24", "25-29","30-34","35-39","40-44","45-49","50-55","56-59","60-69","70-79","+80");		

		return faixa.get(posicaoMaior(totalDeCadaFaixaEtaria()));
	}
	
	@Override
	public List<Integer> totalDeCadaFaixaEtaria(){
		List<Integer> faixaEtaria = List.of(
				inqueritoRepository.contaFaixaEtaria("15-19"),inqueritoRepository.contaFaixaEtaria("20-24"),
				inqueritoRepository.contaFaixaEtaria("25-29"),inqueritoRepository.contaFaixaEtaria("30-34"),
				inqueritoRepository.contaFaixaEtaria("35-39"),inqueritoRepository.contaFaixaEtaria("40-44"),
				inqueritoRepository.contaFaixaEtaria("45-49"),inqueritoRepository.contaFaixaEtaria("50-55"),
				inqueritoRepository.contaFaixaEtaria("56-59"),inqueritoRepository.contaFaixaEtaria("60-69"),
				inqueritoRepository.contaFaixaEtaria("70-79"),inqueritoRepository.contaFaixaEtaria("+80"));
		return faixaEtaria;
	}
	
	@Override
	public String FaixaEtariaMenor() {
		
		List<String> faixa = List.of("15-19","20-24", "25-29","30-34","35-39","40-44","45-49","50-55","56-59","60-69","70-79","+80");
		
		return faixa.get(posicaoMenor(totalDeCadaFaixaEtaria()));	
	}
	
	@Override
	public int ContaGenero(String valor) {
		return inqueritoRepository.contaGenero(valor);
	}

	@Override
	public int FaixaEtariaFranca() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int contaRecomendacao(String valor) {
		return inqueritoRepository.countByRecomendaCcl(valor);
	}

private int posicaoMaior(List<Integer> faixaEtaria) {
		
		int maior = faixaEtaria.get(0);
        int indice = 0;

        for (int i = 1; i < faixaEtaria.size(); i++) {
            if (faixaEtaria.get(i) > maior) {
                maior = faixaEtaria.get(i);
                indice = i;
            }
        }
		return indice;
	}
	
	private int posicaoMenor(List<Integer> faixaEtaria) {
		
		int menor = faixaEtaria.get(0);
        int indice = 0;

        for (int i = 1; i < faixaEtaria.size(); i++) {
            if (faixaEtaria.get(i) < menor) {
            	menor = faixaEtaria.get(i);
                indice = i;
            }
        }
		return indice;
	}

	@Override
	public double PercentualRemuneradas(String valor) {
		//(inqueritoRepository.pessoasComDeficiencia() * 100) / (double) inqueritoRepository.count()
		return (inqueritoRepository.countByActividadeRemunerada(valor) * 100 ) / inqueritoRepository.count();
	}

	@Override
	public double PercentualOcupacao(String valor) {
		return (inqueritoRepository.countByAcupacao(valor) * 100 ) / inqueritoRepository.count();
	}

	@Override
	public int ContaDesempregoPorGenero(String genero, String actividade) {
		return inqueritoRepository.countByGeneroAndActividadeRemunerada(genero, actividade);
	}

	@Override
	public int ContaDesempregoPorGeneroEocupacao(String genero, String ocupacao) {
		return inqueritoRepository.countByGeneroAndAcupacao(genero, ocupacao);
	}

	@Override
	public int contaPorActividadeEnivelAcademico(String valorActividade, String nivelAcademico) {
		return inqueritoRepository.countByActividadeRemuneradaAndNivelAcademico(valorActividade, nivelAcademico);
	}

	@Override
	public int contaPorNivelAcademico(String nivelAcademico) {
		return inqueritoRepository.countByNivelAcademico(nivelAcademico);
	}

	@Override
	public int contaPorOcupacao(String valor) {
		return inqueritoRepository.countByAcupacao(valor);
	}

	@Override
	public int TotalActividadeRemunerada(String valor) {
		return inqueritoRepository.countByActividadeRemunerada(valor);
	}

	@Override
	public int qtdInqueritosDoMes(String mesAno) {
		return inqueritoRepository.contarPorMesAno(mesAno);
	}

	@Override
	public int mesMaiorDesempenho(List<Integer> listaMes) {
		return posicaoMaior(listaMes);
	}

	@Override
	public int mesMenorDesempenho(List<Integer> listaMes) {
		return posicaoMenor(listaMes);
	}

	@Override
	public int totalUltimosTrintasDias(LocalDate data) {
		return inqueritoRepository.countByDataCriacaoAfter(data);
	}
}
