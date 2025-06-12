package ccl.inquerito.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "inquerito")
public class InqueritoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIquerito;

    private String idade;
    private String nivelAcademico;
    private String genero;
    private String portadorDeDeficiencia;
    private String areaDeResidencia;
    private String areaDeResidenciaField;
    private String actividadeRemunerada;
    private String acupacao;
    private String percepcao;
    private String origemInfo;
    private String proposito;
    private String propositoField;
    private String experiencia;
    private int grauSatisfacaoDeQualidade;
    private int grauSatisfacaoDeAtendimento;
    private int grauSatisfacaoInteracao;
    private int satisfacaoPrecoDoBilhete;
    private String servicoAdicional;
    private String servicoAdicionalField;
    private int grauSatisfacaoCafetaria;
    private int grauSatisfacaoMenuCafetaria;
    private int grauSatisfacaoAtendimentoLoja;
    private int grauSatisfacaoProdutoLoja;
    private int grauSatisfacaoLimpeza;
    private String recomendaCcl;
    private String comentario;
    private String email;
    private LocalDateTime dataCriacao;
    
    private long totalVisitas;

    public InqueritoModel() {}

	public Long getIdIquerito() {
		return idIquerito;
	}

	public void setIdIquerito(Long idIquerito) {
		this.idIquerito = idIquerito;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getOrigemInfo() {
		return origemInfo;
	}

	public void setOrigemInfo(String origemInfo) {
		this.origemInfo = origemInfo;
	}

	public int getGrauSatisfacaoDeQualidade() {
		return grauSatisfacaoDeQualidade;
	}

	public void setGrauSatisfacaoDeQualidade(int grauSatisfacaoDeQualidade) {
		this.grauSatisfacaoDeQualidade = grauSatisfacaoDeQualidade;
	}

	public int getGrauSatisfacaoDeAtendimento() {
		return grauSatisfacaoDeAtendimento;
	}

	public void setGrauSatisfacaoDeAtendimento(int grauSatisfacaoDeAtendimento) {
		this.grauSatisfacaoDeAtendimento = grauSatisfacaoDeAtendimento;
	}

	public int getGrauSatisfacaoInteracao() {
		return grauSatisfacaoInteracao;
	}

	public void setGrauSatisfacaoInteracao(int grauSatisfacaoInteracao) {
		this.grauSatisfacaoInteracao = grauSatisfacaoInteracao;
	}

	public int getSatisfacaoPrecoDoBilhete() {
		return satisfacaoPrecoDoBilhete;
	}

	public void setSatisfacaoPrecoDoBilhete(int satisfacaoPrecoDoBilhete) {
		this.satisfacaoPrecoDoBilhete = satisfacaoPrecoDoBilhete;
	}

	public String getServicoAdicional() {
		return servicoAdicional;
	}

	public void setServicoAdicional(String servicoAdicional) {
		this.servicoAdicional = servicoAdicional;
	}

	public int getGrauSatisfacaoCafetaria() {
		return grauSatisfacaoCafetaria;
	}

	public void setGrauSatisfacaoCafetaria(int grauSatisfacaoCafetaria) {
		this.grauSatisfacaoCafetaria = grauSatisfacaoCafetaria;
	}

	public int getGrauSatisfacaoMenuCafetaria() {
		return grauSatisfacaoMenuCafetaria;
	}

	public void setGrauSatisfacaoMenuCafetaria(int grauSatisfacaoMenuCafetaria) {
		this.grauSatisfacaoMenuCafetaria = grauSatisfacaoMenuCafetaria;
	}

	public int getGrauSatisfacaoAtendimentoLoja() {
		return grauSatisfacaoAtendimentoLoja;
	}

	public void setGrauSatisfacaoAtendimentoLoja(int grauSatisfacaoAtendimentoLoja) {
		this.grauSatisfacaoAtendimentoLoja = grauSatisfacaoAtendimentoLoja;
	}

	public int getGrauSatisfacaoProdutoLoja() {
		return grauSatisfacaoProdutoLoja;
	}

	public void setGrauSatisfacaoProdutoLoja(int grauSatisfacaoProdutoLoja) {
		this.grauSatisfacaoProdutoLoja = grauSatisfacaoProdutoLoja;
	}

	public int getGrauSatisfacaoLimpeza() {
		return grauSatisfacaoLimpeza;
	}

	public void setGrauSatisfacaoLimpeza(int grauSatisfacaoLimpeza) {
		this.grauSatisfacaoLimpeza = grauSatisfacaoLimpeza;
	}

	public String getRecomendaCcl() {
		return recomendaCcl;
	}

	public void setRecomendaCcl(String recomendaCcl) {
		this.recomendaCcl = recomendaCcl;
	}

	public String getProposito() {
		return proposito;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getNivelAcademico() {
		return nivelAcademico;
	}

	public void setNivelAcademico(String nivelAcademico) {
		this.nivelAcademico = nivelAcademico;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPortadorDeDeficiencia() {
		return portadorDeDeficiencia;
	}

	public void setPortadorDeDeficiencia(String portadorDeDeficiencia) {
		this.portadorDeDeficiencia = portadorDeDeficiencia;
	}

	public String getAreaDeResidencia() {
		return areaDeResidencia;
	}

	public void setAreaDeResidencia(String areaDeResidencia) {
		this.areaDeResidencia = areaDeResidencia;
	}

	public String getActividadeRemunerada() {
		return actividadeRemunerada;
	}

	public void setActividadeRemunerada(String actividadeRemunerada) {
		this.actividadeRemunerada = actividadeRemunerada;
	}

	public String getAcupacao() {
		return acupacao;
	}

	public void setAcupacao(String acupacao) {
		this.acupacao = acupacao;
	}

	public String getPercepcao() {
		return percepcao;
	}

	public void setPercepcao(String percepcao) {
		this.percepcao = percepcao;
	}

	public void setProposito(String proposito) {
		this.proposito = proposito;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getTotalVisitas() {
		return totalVisitas;
	}

	public void setTotalVisitas(long totalVisitas) {
		this.totalVisitas = totalVisitas;
	}

	public String getAreaDeResidenciaField() {
		return areaDeResidenciaField;
	}

	public void setAreaDeResidenciaField(String areaDeResidenciaField) {
		this.areaDeResidenciaField = areaDeResidenciaField;
	}

	public String getServicoAdicionalField() {
		return servicoAdicionalField;
	}

	public void setServicoAdicionalField(String servicoAdicionalField) {
		this.servicoAdicionalField = servicoAdicionalField;
	}

	public String getPropositoField() {
		return propositoField;
	}

	public void setPropositoField(String propositoField) {
		this.propositoField = propositoField;
	}
    
}

