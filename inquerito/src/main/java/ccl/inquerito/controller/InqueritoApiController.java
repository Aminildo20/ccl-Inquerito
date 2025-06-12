package ccl.inquerito.controller;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ccl.inquerito.DTO.InqueritoDTO;
import ccl.inquerito.DTO.InqueritoDTOflutterFlow;
import ccl.inquerito.model.InqueritoModel;
import ccl.inquerito.serviceImpl.InqueritoServiceImpl;
import ccl.inquerito.serviceImpl.VisitaServiceImpl;

@RestController
@RequestMapping("/api-ccl")
public class InqueritoApiController {
	
	@Autowired
	private InqueritoServiceImpl inqueritoImpl;

	@Autowired
	private VisitaServiceImpl visitaImpl;
	
	@GetMapping("/contaVisita")
	public ResponseEntity<String> contaVisita() {
		
		if(visitaImpl.contaVisita() <= 0)
			visitaImpl.inserirVisita();
		else
			visitaImpl.atualizaNumVisitas();
		
		return ResponseEntity.ok("OK");
	}
	
	@PostMapping("/inquerito")
	public ResponseEntity<String> salvarInquerito(@RequestBody InqueritoDTO inquerit) {
        try {
        	
        	inquerit.setDataCriacao(LocalDateTime.now());
        	InqueritoModel inquerito = new InqueritoModel();
        	inquerito = inqueritoImpl.converterParaEntidade(inquerit);
        	
        	//formatar snake_case
        	inquerito.setNivelAcademico(formatarParaSnakeCase(inquerito.getNivelAcademico()));
        	inquerito.setIdade(formatarFaixaEtaria(inquerito.getIdade()));
        	
        	System.out.println(" --- DADOS API ---");
        	System.out.println(" ######################## ");
        	System.out.println(" ### --- OUTRO RESIDENCIA --->>>> "+inquerit.getAreaDeResidenciaOutro());
        	System.out.println(" ######################## ");
        	//System.out.println(" PERCEPCAO >>> "+inquerito.getPercepcao());
        	System.out.println("Actividade remunerada - "+inquerito.getActividadeRemunerada());
        	System.out.println("Ocupacao - "+inquerito.getAcupacao());
        	System.out.println("Residencia - "+inquerito.getAreaDeResidencia());
        	System.out.println("Comentario - "+inquerito.getComentario());
        	System.out.println("Email - "+inquerito.getEmail());
        	System.out.println("Experiencia - "+inquerito.getExperiencia());
        	System.out.println("Genero - "+inquerito.getGenero());
        	System.out.println(inquerito.getGrauSatisfacaoAtendimentoLoja());
			System.out.println(inquerito.getGrauSatisfacaoCafetaria());
			System.out.println(inquerito.getGrauSatisfacaoDeAtendimento());
			System.out.println(inquerito.getGrauSatisfacaoDeQualidade());
			System.out.println(inquerito.getGrauSatisfacaoInteracao());
			System.out.println(inquerito.getGrauSatisfacaoLimpeza());
			System.out.println(inquerito.getGrauSatisfacaoMenuCafetaria());
			System.out.println(inquerito.getGrauSatisfacaoProdutoLoja());
        	System.out.println("Idade - "+inquerito.getIdade());
        	System.out.println("Nivel academico - "+inquerito.getNivelAcademico());
        	System.out.println("De Onde ouviu CCL - "+inquerito.getOrigemInfo());
        	System.out.println("descrever CCL (percepcao) - "+inquerito.getPercepcao());
        	System.out.println("data inquirido - "+inquerito.getDataCriacao());
        	System.out.println("#####----------------###");
        	       	
	       // inqueritoImpl.novoInquerito(inquerito);
            return ResponseEntity.ok("Inquérito salvo com sucesso! - Obrigado pela sua opinião sobre a sua experiência connosco ");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro nos dados: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro interno: " + e.getMessage());
        }
    }
	
	@PostMapping("/inquerito-Teste")
	public ResponseEntity<String> salvarInqueritoAPI(@RequestBody InqueritoDTOflutterFlow inquerit) {
        try {
        	
        	inquerit.setDataCriacao(LocalDateTime.now());
        	InqueritoModel inquerito = new InqueritoModel();
        	inquerito = inqueritoImpl.converterDTOParaEntidade(inquerit);
        	
        	//formatar snake_case
        	inquerito.setNivelAcademico(formatarParaSnakeCase(inquerito.getNivelAcademico()));
        	inquerito.setIdade(formatarFaixaEtaria(inquerito.getIdade()));
        	
        	System.out.println("\n\n\n --- DADOS API ---");
        	System.out.println("idade: " + inquerito.getIdade());
        	System.out.println("nivelAcademico: " + inquerito.getNivelAcademico());
        	System.out.println("genero: " + inquerito.getGenero());
        	System.out.println("portadorDeDeficiencia: " + inquerito.getPortadorDeDeficiencia());
        	System.out.println("areaDeResidencia: " + inquerito.getAreaDeResidencia());
        	System.out.println("areaDeResidenciaField: " + inquerito.getAreaDeResidenciaField());
        	System.out.println("actividadeRemunerada: " + inquerito.getActividadeRemunerada());
        	System.out.println("acupacao: " + inquerito.getAcupacao());
        	System.out.println("percepcao: " + inquerito.getPercepcao());
        	System.out.println("origemInfo: " + inquerito.getOrigemInfo());
        	System.out.println("proposito: " + inquerito.getProposito());
        	System.out.println("experiencia: " + inquerito.getExperiencia());
        	System.out.println("grauSatisfacaoDeQualidade: " + inquerito.getGrauSatisfacaoDeQualidade());
        	System.out.println("grauSatisfacaoDeAtendimento: " + inquerito.getGrauSatisfacaoDeAtendimento());
        	System.out.println("grauSatisfacaoInteracao: " + inquerito.getGrauSatisfacaoInteracao());
        	System.out.println("satisfacaoPrecoDoBilhete: " + inquerito.getSatisfacaoPrecoDoBilhete());
        	System.out.println("servicoAdicional: " + inquerito.getServicoAdicional());
        	System.out.println("servicoAdicionalField: " + inquerito.getServicoAdicionalField());
        	System.out.println("grauSatisfacaoCafetaria: " + inquerito.getGrauSatisfacaoCafetaria());
        	System.out.println("grauSatisfacaoMenuCafetaria: " + inquerito.getGrauSatisfacaoMenuCafetaria());
        	System.out.println("grauSatisfacaoAtendimentoLoja: " + inquerito.getGrauSatisfacaoAtendimentoLoja());
        	System.out.println("grauSatisfacaoProdutoLoja: " + inquerito.getGrauSatisfacaoProdutoLoja());
        	System.out.println("grauSatisfacaoLimpeza: " + inquerito.getGrauSatisfacaoLimpeza());
        	System.out.println("recomendaCcl: " + inquerito.getRecomendaCcl());
        	System.out.println("comentario: " + inquerito.getComentario());
        	System.out.println("email: " + inquerito.getEmail());
        	System.out.println("dataCriacao: " + inquerito.getDataCriacao());
        	System.out.println("#####----------------###\n\n\n");      
        	       	
	        inqueritoImpl.novoInquerito(inquerito);
            return ResponseEntity.ok("Inquérito salvo com sucesso! - Obrigado pela sua opinião sobre a sua experiência connosco ");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro nos dados: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro interno: " + e.getMessage());
        }
    }
	
	private String formatarParaSnakeCase(String texto) {
        if (texto == null) return null;
        return texto.toLowerCase().replace(" ", "_").trim().replaceAll("\\s+", "_");
    }
	
	public String formatarFaixaEtaria(String idadeTexto) {
		if(idadeTexto.equalsIgnoreCase("80 anos ou mais")) return "+80";
		
		if (idadeTexto == null || idadeTexto.isBlank()) return "";

	    // Remove "anos", espaços extras e substitui " a " por "-"
	    return idadeTexto
	        .replace("anos", "")
	        .trim()
	        .replaceAll("\\s*a\\s*", "-")
	        .trim();
    }
}
