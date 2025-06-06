package ccl.inquerito.serviceImpl;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ccl.inquerito.DTO.InqueritoDTO;
import ccl.inquerito.DTO.InqueritoDTOflutterFlow;
import ccl.inquerito.model.InqueritoModel;
import ccl.inquerito.repository.InqueritoRepository;
import ccl.inquerito.service.InqueritoService;

@Service
public class InqueritoServiceImpl implements InqueritoService{
	
	@Autowired
	private InqueritoRepository inqueritoRepository;

	@Override
	public InqueritoModel novoInquerito(InqueritoModel inquerito) {
		
		inquerito.setDataCriacao(LocalDateTime.now());
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
	public Double SatisfacaoMediaGeralUltimosDias(LocalDateTime data) {
		return inqueritoRepository.calcularTaxaSatisfacaoMediaGeralUtimosDias(data);
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
	public long NumVisitanteSatisfeitoUltimosDias(LocalDateTime data) {
		return inqueritoRepository.contarVisitantesSatisfeitosUltimosDias(data);
	}

	@Override
	public long NumVisitanteInsatisfeitoUltimosDias(LocalDateTime data) {
		return inqueritoRepository.contarVisitantesInsatisfeitosUltimosDias(data);
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
	public int pessoasComDeficienciaUltimosDias(LocalDateTime data) {
		return inqueritoRepository.contarPessoasComDeficienciaDesde(data);
	}
	

	@Override
	public String FaixaEtariaPredominante() {
		List<String> faixa = List.of("15-19","20-24", "25-29","30-34","35-39","40-44","45-49","50-55","56-59","60-69","70-79","+80");
		return faixa.get(posicaoMaior(totalDeCadaFaixaEtaria()));
	}	
	
	@Override
	public String FaixaEtariaMenor() {		
		List<String> faixa = List.of("15-19","20-24", "25-29","30-34","35-39","40-44","45-49","50-55","56-59","60-69","70-79","+80");		
		return faixa.get(posicaoMenor(totalDeCadaFaixaEtaria()));	
	}
	
	public List<String> listaDeFaixaEtaria(){
	List<String> faixasEtarias = List.of(
		    "15 a 19 anos", "20 a 24 anos", "25 a 29 anos", "30 a 34 anos",
		    "35 a 39 anos", "40 a 44 anos", "45 a 49 anos", "50 a 55 anos",
		    "56 a 59 anos", "60 a 69 anos", "70 a 79 anos", "80 anos ou mais"
		);	
	return faixasEtarias;
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
	
	public List<String> listaDeResidencias(){
		List<String> faixasEtarias = List.of(
				"Zona Central da Cidade de Luanda", "Zona Oeste da Cidade de Luanda","Zona Norte da Cidade de Luanda",
				"Zona Sul da Cidade de Luanda", "Moro noutra província","Moro noutro país"
			);	
		return faixasEtarias;
	}
	
	@Override
	public List<Integer> totalDeCadaResidencia(){
		List<Integer> residencias = List.of(
				inqueritoRepository.countByAreaDeResidencia("Zona Central da Cidade de Luanda"),
				inqueritoRepository.countByAreaDeResidencia("Zona Oeste da Cidade de Luanda"),
				inqueritoRepository.countByAreaDeResidencia("Zona Norte da Cidade de Luanda"),
				inqueritoRepository.countByAreaDeResidencia("Zona Sul da Cidade de Luanda"),
				inqueritoRepository.countByAreaDeResidencia("Moro noutra província"),
				inqueritoRepository.countByAreaDeResidencia("Moro noutro país")
				);		
		
		return residencias;
	}

	
	
	public List<String> listaDeNivelAcademico(){
		List<String> faixasEtarias = List.of(
				"Sem instrução escolar", "Ensino Fundamental incompleto","Ensino Fundamental completo","Ensino Médio incompleto",
				"Ensino Médio completo","Ensino Superior incompleto","Ensino Superior completo","Pós-graduação"
			);	
		return faixasEtarias;
	}
	@Override
	public List<Integer> totalNivelAcademico(){
		List<Integer> niveisAcademico = List.of(
				inqueritoRepository.countByNivelAcademico("sem_instrucao"),
				inqueritoRepository.countByNivelAcademico("ensino_fundamental_incompleto"),
				inqueritoRepository.countByNivelAcademico("ensino_fundamental_completo"),
				inqueritoRepository.countByNivelAcademico("ensino_medio_incompleto"),
				inqueritoRepository.countByNivelAcademico("ensino_medio_completo"),
				inqueritoRepository.countByNivelAcademico("ensino_superior_incompleto"),
				inqueritoRepository.countByNivelAcademico("ensino_superior_completo"),
				inqueritoRepository.countByNivelAcademico("pos_graduacao")
				);		
		return niveisAcademico; 
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
	public int RemuneradasUltimosDias(String valor, LocalDateTime data) {
		return inqueritoRepository.countByActividadeRemuneradaAndDataCriacaoAfter(valor, data);
	}

	@Override
	public double PercentualOcupacao(String valor) {
		return (inqueritoRepository.countByAcupacao(valor) * 100 ) / inqueritoRepository.count();
	}
	
	@Override
	public int OcupacaoUltimosDias(String valor, LocalDateTime data) {
		return inqueritoRepository.countByAcupacaoAndDataCriacaoAfter(valor, data);
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
	public int totalUltimosTrintasDias(LocalDateTime data) {
		return inqueritoRepository.countByDataCriacaoAfter(data);
	}

	@Override
	public Double Ultimos30diasMediaGeral(LocalDateTime data) {
		return inqueritoRepository.taxaMediaGeralUltimoTrintaDias(data);
	}
	
	public List<Double> calcularPercentuais(int valor, long totalEnviados, boolean bilheteiraComValorMenosUm) {

		//'Exposições', 'Guias', 'Bilheteira', 'Loja', 'Cafetaria', 'Limpeza'
	    return List.of(
	        satisfacaoExposicao(valor) * 100.0 / totalEnviados,
	        satisfacaoGuia(valor) * 100.0 / totalEnviados,
	        satisfacaoBilheteira(bilheteiraComValorMenosUm ? valor - 1 : valor) * 100.0 / totalEnviados,
	        satisfacaoLoja(valor) * 100.0 / totalEnviados,
	        satisfacaoCafetaria(valor) * 100.0 / totalEnviados,
	        satisfacaoLimpeza(valor) * 100.0 / totalEnviados
	    );
	}
	
	public List<Double> MesPercentuais(int anoAtual, long totalEnviados) {
		return List.of(
	    		qtdInqueritosDoMes(anoAtual+"-01") * 100.0 / totalEnviados,
	    		qtdInqueritosDoMes(anoAtual+"-02") * 100.0 / totalEnviados,
				qtdInqueritosDoMes(anoAtual+"-03") * 100.0 / totalEnviados,
				qtdInqueritosDoMes(anoAtual+"-04") * 100.0 / totalEnviados,
				qtdInqueritosDoMes(anoAtual+"-05") * 100.0 / totalEnviados,
				qtdInqueritosDoMes(anoAtual+"-06") * 100.0 / totalEnviados,
				qtdInqueritosDoMes(anoAtual+"-07") * 100.0 / totalEnviados,
				qtdInqueritosDoMes(anoAtual+"-08") * 100.0 / totalEnviados,
				qtdInqueritosDoMes(anoAtual+"-09") * 100.0 / totalEnviados,
				qtdInqueritosDoMes(anoAtual+"-10") * 100.0 / totalEnviados,
				qtdInqueritosDoMes(anoAtual+"-11") * 100.0 / totalEnviados,
				qtdInqueritosDoMes(anoAtual+"-12") * 100.0 / totalEnviados
	    );
	}
	
	public List<Double> somarListas(List<Double> lista1, List<Double> lista2) {
	    List<Double> resultado = new ArrayList<>();
	    int tamanho = lista2.size();
	    
	    for (int i = 0; i < tamanho; i++) {
	        resultado.add(lista1.get(i) + lista2.get(i));
	    }
	    
	    return resultado;
	}

	public int converterGrau(String valor) {
	    return switch (valor.toLowerCase()) {
	        case "muito satisfeito" -> 5;
	        case "satisfeito" -> 4;
	        case "indiferente" -> 3;
	        case "insatisfeito" -> 2;
	        case "muito insatisfeito" -> 1;
	        default -> 4; // ou lançar exceção, se necessário
	    };
	}
	
	
	public InqueritoModel converterParaEntidade(InqueritoDTO dto) {
	    InqueritoModel inquerito = new InqueritoModel();

	    inquerito.setIdade(dto.getIdade());
	    inquerito.setNivelAcademico(dto.getNivelAcademico());
	    inquerito.setGenero(dto.getGenero());
	    inquerito.setPortadorDeDeficiencia(dto.getPortadorDeDeficiencia());
	    inquerito.setAreaDeResidencia(dto.getAreaDeResidencia());

	    inquerito.setAcupacao(dto.getAcupacao());	    
	    if(dto.getActividadeRemunerada().equalsIgnoreCase("")){	
		    inquerito.setActividadeRemunerada("Nao");		   
	    }
	    else{
		    inquerito.setAcupacao(dto.getActividadeRemunerada());
		    inquerito.setActividadeRemunerada("sim");
	    }
	    	

	    
	    inquerito.setPercepcao(String.join(",", dto.getPercepcao())); // se quiser guardar como string
	    inquerito.setOrigemInfo(dto.getOrigemInfo());
	    inquerito.setProposito(dto.getProposito());
	    inquerito.setExperiencia(dto.getExperiencia());
	    inquerito.setGrauSatisfacaoDeQualidade(converterGrau(dto.getGrauSatisfacaoDeQualidade()));
	    inquerito.setGrauSatisfacaoDeAtendimento(converterGrau(dto.getGrauSatisfacaoDeAtendimento()));
	    inquerito.setGrauSatisfacaoInteracao(converterGrau(dto.getGrauSatisfacaoInteracao()));
	    inquerito.setSatisfacaoPrecoDoBilhete(converterGrau(dto.getSatisfacaoPrecoDoBilhete()));
	    inquerito.setServicoAdicional(dto.getServicoAdicional());
	    inquerito.setGrauSatisfacaoCafetaria(converterGrau(dto.getGrauSatisfacaoCafetaria()));
	    inquerito.setGrauSatisfacaoMenuCafetaria(converterGrau(dto.getGrauSatisfacaoMenuCafetaria()));
	    inquerito.setGrauSatisfacaoAtendimentoLoja(converterGrau(dto.getGrauSatisfacaoAtendimentoLoja()));
	    inquerito.setGrauSatisfacaoProdutoLoja(converterGrau(dto.getGrauSatisfacaoProdutoLoja()));
	    inquerito.setGrauSatisfacaoLimpeza(converterGrau(dto.getGrauSatisfacaoLimpeza()));
	    inquerito.setRecomendaCcl(dto.getRecomendaCcl());
	    inquerito.setComentario(dto.getComentario());
	    inquerito.setEmail(dto.getEmail());
	    inquerito.setDataCriacao(dto.getDataCriacao());

	    return inquerito;
	}
	
	
	public InqueritoModel converterDTOParaEntidade(InqueritoDTOflutterFlow dto) {
	    InqueritoModel inquerito = new InqueritoModel();

	    inquerito.setIdade(dto.getIdade());
	    inquerito.setNivelAcademico(dto.getNivelAcademico());
	    inquerito.setGenero(dto.getGenero());
	    inquerito.setPortadorDeDeficiencia(dto.getPortadorDeDeficiencia());
	    
	 // Prioriza campos personalizados, se existirem
	    inquerito.setAreaDeResidencia(priorizarCampo(dto.getAreaDeResidenciaOutroPais(), dto.getAreaDeResidenciaOutroProvincia(), dto.getAreaDeResidencia()));

	    //***
	    inquerito.setActividadeRemunerada(priorizarCampo(dto.getActividadeRemuneradaFiled(), dto.getActividadeRemunerada()));
	    inquerito.setAcupacao(priorizarCampo(dto.getAcupacaoField(), dto.getAcupacao()));
	    
	    if(inquerito.getActividadeRemunerada().equalsIgnoreCase("") || inquerito.getActividadeRemunerada().equalsIgnoreCase(null)){	
		    inquerito.setActividadeRemunerada("Nao");		   
	    }
	    else{
		    inquerito.setAcupacao((inquerito.getActividadeRemunerada() != null && !inquerito.getActividadeRemunerada().trim().isEmpty()) ? inquerito.getActividadeRemunerada() : "Não informado");
		    inquerito.setActividadeRemunerada("sim");
	    }//***
	    
	    inquerito.setOrigemInfo(priorizarCampo(dto.getOrigemInfoFiled(), dto.getOrigemInfo()));
	    inquerito.setProposito(priorizarCampo(dto.getPropositoField(), dto.getProposito()));
	    //inquerito.setPercepcao(String.join(",", dto.getPercepcao())); // guardar como string
	    //***
	    String[] percepcoesArray = converterParaVetor(dto.getPercepcao());
	    String percepcaoExtra = dto.getPercepcaoFiled();

	    //System.out.println("***** VALOR DO PERCEPCAO >>>> "+String.join(", ", percepcoesArray));
	    //Verifica se percepcaoExtra tem valor.
	    //Se sim, substitui a última posição do array com esse valor.
	    if (percepcaoExtra != null && !percepcaoExtra.trim().isEmpty() && percepcoesArray != null && percepcoesArray.length > 0) {
	        percepcoesArray[percepcoesArray.length - 1] = percepcaoExtra.trim();
	    }	   
	    //String.join() para unir os elementos do array com vírgula.Se o array for null ou vazio, usa percepcaoExtra como valor final.
	    String percepcoesComoTexto = (percepcoesArray != null && percepcoesArray.length > 0) ? String.join(", ", percepcoesArray)
	            : (percepcaoExtra != null ? percepcaoExtra.trim() : "");
	    
	    //System.out.println("#### VALOR DO PERCEPCAO >>>> "+ percepcoesComoTexto);
	    //System.out.println("#### VALOR DO OUTRO FIELD PERCEPCAO >>>> "+dto.getPercepcaoFiled());

	    inquerito.setPercepcao(percepcoesComoTexto);

	    //***	    
	    inquerito.setExperiencia(dto.getExperiencia());
	    inquerito.setGrauSatisfacaoDeQualidade(converterGrau(dto.getGrauSatisfacaoDeQualidade()));
	    inquerito.setGrauSatisfacaoDeAtendimento(converterGrau(dto.getGrauSatisfacaoDeAtendimento()));
	    inquerito.setGrauSatisfacaoInteracao(converterGrau(dto.getGrauSatisfacaoInteracao()));
	    inquerito.setSatisfacaoPrecoDoBilhete(converterGrau(dto.getSatisfacaoPrecoDoBilhete()));
	    inquerito.setServicoAdicional(priorizarCampo(dto.getServicoAdicionalField(), dto.getServicoAdicional()));	    
	    inquerito.setGrauSatisfacaoCafetaria(converterGrau(dto.getGrauSatisfacaoCafetaria()));
	    inquerito.setGrauSatisfacaoMenuCafetaria(converterGrau(dto.getGrauSatisfacaoMenuCafetaria()));
	    inquerito.setGrauSatisfacaoAtendimentoLoja(converterGrau(dto.getGrauSatisfacaoAtendimentoLoja()));
	    inquerito.setGrauSatisfacaoProdutoLoja(converterGrau(dto.getGrauSatisfacaoProdutoLoja()));
	    inquerito.setGrauSatisfacaoLimpeza(converterGrau(dto.getGrauSatisfacaoLimpeza()));	    
	    inquerito.setRecomendaCcl(dto.getRecomendaCcl());
	    inquerito.setComentario(dto.getComentario());
	    inquerito.setEmail(dto.getEmail());
	    inquerito.setDataCriacao(dto.getDataCriacao());

	    return inquerito;
	}
	
	private String priorizarCampo(String... opcoes) {
	    for (String opcao : opcoes) {
	        if (opcao != null && !opcao.trim().isEmpty()) {
	            return opcao.trim();
	        }
	    }
	    return "";
	}
	
	private String[] converterParaVetor(Object entrada) {
		if (entrada == null) {
	        return new String[0];
	    }
	   
	    if (entrada instanceof String[]) {
	        return (String[]) entrada;
	    }
	    // Se for uma única string separada por vírgulas, converte para vetor
	    if (entrada instanceof String) {
	        String texto = ((String) entrada).trim();
	        if (texto.isEmpty()) {
	            return new String[0];
	        }
	        return texto.split("\\s*,\\s*"); // Divide por vírgula e remove espaços ao redor
	    }
		return null;
	}

	
	
	public String desconverterGrau(int valor) {
	    return switch (valor) {
	        case 5 -> "Muito satisfeito";
	        case 4 -> "Satisfeito";
	        case 3 -> "Indiferente";
	        case 2 -> "Insatisfeito";
	        case 1 -> "Muito insatisfeito";
	        default -> "Satisfeito"; // ou lançar exceção, se necessário
	    };
	}
	
	private String desformatarDeSnakeCase(String texto) {
	    if (texto == null) return null;
	    return Arrays.stream(texto.split("_"))
	                 .map(palavra -> palavra.isEmpty() ? "" :
	                     Character.toUpperCase(palavra.charAt(0)) + palavra.substring(1))
	                 .collect(Collectors.joining(" "));
	}
	
	public String desformatarFaixaEtaria(String texto) {
	    if (texto == null || texto.isBlank()) {
	        return "";
	    }

	    return texto.replace("-", " a ").trim() + " anos";
	}


	
	public InqueritoDTO converterParaDTO(InqueritoModel model) {
	    InqueritoDTO dto = new InqueritoDTO();

	    dto.setIdIquerito(model.getIdIquerito());
	    dto.setIdade(model.getIdade());
	    dto.setNivelAcademico(model.getNivelAcademico());
	    dto.setGenero(model.getGenero());
	    dto.setPortadorDeDeficiencia(model.getPortadorDeDeficiencia());
	    dto.setAreaDeResidencia(model.getAreaDeResidencia());
	    dto.setActividadeRemunerada(model.getActividadeRemunerada());
	    dto.setAcupacao(model.getAcupacao());
	    dto.setPercepcao(model.getPercepcao().split(",")); // se foi salvo como string separada por vírgulas
	    dto.setOrigemInfo(model.getOrigemInfo());
	    dto.setProposito(model.getProposito());
	    dto.setExperiencia(model.getExperiencia());
	    dto.setGrauSatisfacaoDeQualidade(desconverterGrau(model.getGrauSatisfacaoDeQualidade()));
	    dto.setGrauSatisfacaoDeAtendimento(desconverterGrau(model.getGrauSatisfacaoDeAtendimento()));
	    dto.setGrauSatisfacaoInteracao(desconverterGrau(model.getGrauSatisfacaoInteracao()));
	    dto.setSatisfacaoPrecoDoBilhete(desconverterGrau(model.getSatisfacaoPrecoDoBilhete()));
	    dto.setServicoAdicional(model.getServicoAdicional());
	    dto.setGrauSatisfacaoCafetaria(desconverterGrau(model.getGrauSatisfacaoCafetaria()));
	    dto.setGrauSatisfacaoMenuCafetaria(desconverterGrau(model.getGrauSatisfacaoMenuCafetaria()));
	    dto.setGrauSatisfacaoAtendimentoLoja(desconverterGrau(model.getGrauSatisfacaoAtendimentoLoja()));
	    dto.setGrauSatisfacaoProdutoLoja(desconverterGrau(model.getGrauSatisfacaoProdutoLoja()));
	    dto.setGrauSatisfacaoLimpeza(desconverterGrau(model.getGrauSatisfacaoLimpeza()));
	    dto.setRecomendaCcl(model.getRecomendaCcl());
	    dto.setComentario(model.getComentario());
	    dto.setEmail(model.getEmail());	    
	  //desformatar snake_case
    	dto.setNivelAcademico(desformatarDeSnakeCase(model.getNivelAcademico()));
    	dto.setIdade(desformatarFaixaEtaria(model.getIdade()));

	    return dto;
	}


	
	@Override
	public Page<InqueritoModel> listarAssociadoPaginacao(int page, int size) {
		return inqueritoRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Optional<InqueritoModel> buscaPorId(Long idInquerito) {
		return inqueritoRepository.findById(idInquerito);
	}

	@Override
	public Page<InqueritoModel> buscarPorData(int page, int size, LocalDateTime data) {
		LocalDateTime inicioDoDia = data.toLocalDate().atStartOfDay();
		LocalDateTime fimDoDia = data.toLocalDate().atTime(LocalTime.MAX);

		return inqueritoRepository.findByDataCriacaoBetween(PageRequest.of(page, size), inicioDoDia, fimDoDia);
		
	//return inqueritoRepository.findByDataCriacao(PageRequest.of(page, size), data);
	}

	@Override
	public List<String> buscarTodoEmail() {
		return inqueritoRepository.findAllEmail();
	}

}
