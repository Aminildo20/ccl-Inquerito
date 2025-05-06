package ccl.inquerito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ccl.inquerito.model.InqueritoModel;

@Repository
public interface InqueritoRepository extends JpaRepository<InqueritoModel, Long>{
	

	 @Query("SELECT AVG(s.grauSatisfacaoDeQualidade + s.grauSatisfacaoDeAtendimento + s.grauSatisfacaoInteracao + s.satisfacaoPrecoDoBilhete + " +
	           "s.grauSatisfacaoCafetaria + s.grauSatisfacaoMenuCafetaria + s.grauSatisfacaoAtendimentoLoja + s.grauSatisfacaoProdutoLoja + " +
	           "s.grauSatisfacaoLimpeza) FROM InqueritoModel s")
	    Double calcularTaxaSatisfacaoMediaGeral();	 
	 
	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.grauSatisfacaoDeQualidade >= 3 AND i.grauSatisfacaoDeAtendimento >= 3 " +
	           "AND i.grauSatisfacaoInteracao >= 3 AND i.satisfacaoPrecoDoBilhete >= 3 AND i.grauSatisfacaoCafetaria >= 3 AND i.grauSatisfacaoMenuCafetaria >= 3 " +
	           "AND i.grauSatisfacaoAtendimentoLoja >= 3 AND i.grauSatisfacaoProdutoLoja >= 3 AND i.grauSatisfacaoLimpeza >= 3")
	    int contarVisitantesSatisfeitos();
	 
	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.grauSatisfacaoDeQualidade  <= 2 AND i.grauSatisfacaoDeAtendimento <= 2 " +
	           "AND i.grauSatisfacaoInteracao <= 2 AND i.satisfacaoPrecoDoBilhete <= 2 AND i.grauSatisfacaoCafetaria <= 2 " +
	           "AND i.grauSatisfacaoMenuCafetaria <= 2 AND i.grauSatisfacaoAtendimentoLoja <= 2 AND i.grauSatisfacaoProdutoLoja <= 2 " +
	           "AND i.grauSatisfacaoLimpeza <= 2")
	    int contarVisitantesInsatisfeitos();
	 
	 //SATISFACAO POR CATEGORIA
	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.grauSatisfacaoDeQualidade = :valor")
	    int VisitantesExposicao(@Param("valor") int valor);	 

	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.grauSatisfacaoInteracao = :valor")
	    int VisitantesGuia(@Param("valor") int valor);
	 
	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.satisfacaoPrecoDoBilhete = :valor")
	    int VisitantesBilheiteria(@Param("valor") int valor);

	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.grauSatisfacaoAtendimentoLoja = :valor AND i.grauSatisfacaoProdutoLoja = :valor")
	    int VisitantesLoja(@Param("valor") int valor);

	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.grauSatisfacaoCafetaria = :valor AND i.grauSatisfacaoMenuCafetaria = :valor")
	    int VisitantesCafetaria(@Param("valor") int valor);

	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.grauSatisfacaoLimpeza = :valor")
	    int VisitantesLimpeza(@Param("valor") int valor);
	 
	 //VISITANTES
	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.portadorDeDeficiencia <> '0'")
	 	int pessoasComDeficiencia();

	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.genero = :valor")
	 	int contaGenero(String valor);
	 
	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.idade = :valor")
	 	int contaFaixaEtaria(String valor);
	 
	// @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.idade = :valor")
	 	//int contaFaixaEtaria(String valor);
	 int countByRecomendaCcl(String valor);
	 
	 //RELATORIO PROFISSIONAL
	 int countByActividadeRemunerada(String valor);
	 int countByAcupacao(String valor);
	 int countByGeneroAndActividadeRemunerada(String genero, String actividade);
	 int countByGeneroAndAcupacao(String genero, String ocupacao);
	 int countByActividadeRemuneradaAndNivelAcademico(String valor, String nivel);
	 int countByNivelAcademico(String nivel);
	 
	 //RELATORIO GERAL
	 @Query("SELECT r FROM InqueritoModel r WHERE CAST(r.dataCriacao AS string) LIKE CONCAT(:mesAno, '%')")
	 List<InqueritoModel> buscarPorMesAno(@Param("mesAno") String mesAno);
	 
	 @Query("SELECT COUNT(r) FROM InqueritoModel r WHERE CAST(r.dataCriacao AS string) LIKE CONCAT(:mesAno, '%')")
	 int contarPorMesAno(@Param("mesAno") String mesAno);

	 
}
