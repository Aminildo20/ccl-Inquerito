package ccl.inquerito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ccl.inquerito.model.InqueritoModel;

@Repository
public interface InqueritoRepository extends JpaRepository<InqueritoModel, Long>{
	

	 @Query("SELECT AVG(s.grauSatisfacaoDeQualidade + s.grauSatisfacaoDeAtendimento + s.grauSatisfacaoInteracao + s.satisfacaoPrecoDoBilhete + " +
	           "s.grauSatisfacaoCafetaria + s.grauSatisfacaoMenuCafetaria + s.grauSatisfacaoAtendimentoLoja + s.grauSatisfacaoProdutoLoja + " +
	           "s.grauSatisfacaoLimpeza) / 9 FROM InqueritoModel s")
	    double calcularTaxaSatisfacaoMediaGeral();	 
	 
	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.grauSatisfacaoDeQualidade >= 4 AND i.grauSatisfacaoDeAtendimento >= 4 " +
	           "AND i.grauSatisfacaoInteracao >= 4 AND i.satisfacaoPrecoDoBilhete >= 4 AND i.grauSatisfacaoCafetaria >= 4 AND i.grauSatisfacaoMenuCafetaria >= 4 " +
	           "AND i.grauSatisfacaoAtendimentoLoja >= 4 AND i.grauSatisfacaoProdutoLoja >= 4 AND i.grauSatisfacaoLimpeza >= 4")
	    int contarVisitantesSatisfeitos();
	 
	 @Query("SELECT COUNT(i) FROM InqueritoModel i WHERE i.grauSatisfacaoDeQualidade  <= 4 AND i.grauSatisfacaoDeAtendimento <= 2 " +
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
	 
	 
}
