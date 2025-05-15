package ccl.inquerito.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ccl.inquerito.DTO.InqueritoDTO;
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
        	
        	InqueritoModel inquerito = new InqueritoModel();
        	inquerito = inqueritoImpl.converterParaEntidade(inquerit);
        	
        	//formatar snake_case
        	inquerito.setNivelAcademico(formatarParaSnakeCase(inquerito.getNivelAcademico()));
        	inquerito.setIdade(formatarFaixaEtaria(inquerito.getIdade()));
        	
        	System.out.println(" --- DADOS ---");
        	//System.out.println(" PERCEPCAO >>> "+inquerito.getPercepcao());
        	System.out.println(inquerito.getActividadeRemunerada());
        	System.out.println(inquerito.getAcupacao());
        	System.out.println(inquerito.getAreaDeResidencia());
        	System.out.println(inquerito.getComentario());
        	System.out.println(inquerito.getEmail());
        	System.out.println(inquerito.getExperiencia());
        	System.out.println(inquerito.getGenero());
        	System.out.println(inquerito.getGrauSatisfacaoAtendimentoLoja());
			System.out.println(inquerito.getGrauSatisfacaoCafetaria());
			System.out.println(inquerito.getGrauSatisfacaoDeAtendimento());
			System.out.println(inquerito.getGrauSatisfacaoDeQualidade());
			System.out.println(inquerito.getGrauSatisfacaoInteracao());
			System.out.println(inquerito.getGrauSatisfacaoLimpeza());
			System.out.println(inquerito.getGrauSatisfacaoMenuCafetaria());
			System.out.println(inquerito.getGrauSatisfacaoProdutoLoja());
        	System.out.println(inquerito.getIdade());
        	System.out.println(inquerito.getNivelAcademico());
        	System.out.println(inquerito.getOrigemInfo());
        	System.out.println(inquerito.getPercepcao());
        	System.out.println(" ----------------");
        	
        
        	//inqueritoImpl.novoInquerito(inquerito);
            return ResponseEntity.ok("Inquérito salvo com sucesso! - Obrigado pela sua opinião sobre a sua experiência connosco");
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
		if (idadeTexto == null || idadeTexto.isBlank()) {
	        return "";
	    }

	    // Remove "anos", espaços extras e substitui " a " por "-"
	    return idadeTexto
	        .replace("anos", "")
	        .trim()
	        .replaceAll("\\s*a\\s*", "-")
	        .trim();
    }

}
