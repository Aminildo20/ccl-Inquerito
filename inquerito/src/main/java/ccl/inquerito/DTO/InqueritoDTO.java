package ccl.inquerito.DTO;

import java.time.LocalDateTime;

public class InqueritoDTO {


	private Long idIquerito;
	private String idade;
    private String nivelAcademico;
    private String genero;
    private String portadorDeDeficiencia;
    private String areaDeResidencia;
    private String areaDeResidenciaOutro; //textField novo
    private String actividadeRemunerada;
    private String acupacao;
    private String[] percepcao;
    private String origemInfo;
    private String proposito;
    private String experiencia;
    private String grauSatisfacaoDeQualidade;
    private String grauSatisfacaoDeAtendimento;
    private String grauSatisfacaoInteracao;
    private String satisfacaoPrecoDoBilhete;
    private String servicoAdicional;
    private String grauSatisfacaoCafetaria;
    private String grauSatisfacaoMenuCafetaria;
    private String grauSatisfacaoAtendimentoLoja;
    private String grauSatisfacaoProdutoLoja;
    private String grauSatisfacaoLimpeza;
    private String recomendaCcl;
    private String comentario;
    private String email;
    private LocalDateTime dataCriacao;
	
	
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
	public String[] getPercepcao() {
		return percepcao;
	}
	public void setPercepcao(String[] strings) {
		this.percepcao = strings;
	}
	public String getOrigemInfo() {
		return origemInfo;
	}
	public void setOrigemInfo(String origemInfo) {
		this.origemInfo = origemInfo;
	}
	public String getProposito() {
		return proposito;
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
	public String getServicoAdicional() {
		return servicoAdicional;
	}
	public void setServicoAdicional(String servicoAdicional) {
		this.servicoAdicional = servicoAdicional;
	}
	public String getRecomendaCcl() {
		return recomendaCcl;
	}
	public void setRecomendaCcl(String recomendaCcl) {
		this.recomendaCcl = recomendaCcl;
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
	public String getGrauSatisfacaoDeQualidade() {
		return grauSatisfacaoDeQualidade;
	}
	public void setGrauSatisfacaoDeQualidade(String grauSatisfacaoDeQualidade) {
		this.grauSatisfacaoDeQualidade = grauSatisfacaoDeQualidade;
	}
	public String getGrauSatisfacaoDeAtendimento() {
		return grauSatisfacaoDeAtendimento;
	}
	public void setGrauSatisfacaoDeAtendimento(String grauSatisfacaoDeAtendimento) {
		this.grauSatisfacaoDeAtendimento = grauSatisfacaoDeAtendimento;
	}
	public String getGrauSatisfacaoInteracao() {
		return grauSatisfacaoInteracao;
	}
	public void setGrauSatisfacaoInteracao(String grauSatisfacaoInteracao) {
		this.grauSatisfacaoInteracao = grauSatisfacaoInteracao;
	}
	public String getSatisfacaoPrecoDoBilhete() {
		return satisfacaoPrecoDoBilhete;
	}
	public void setSatisfacaoPrecoDoBilhete(String satisfacaoPrecoDoBilhete) {
		this.satisfacaoPrecoDoBilhete = satisfacaoPrecoDoBilhete;
	}
	public String getGrauSatisfacaoCafetaria() {
		return grauSatisfacaoCafetaria;
	}
	public void setGrauSatisfacaoCafetaria(String grauSatisfacaoCafetaria) {
		this.grauSatisfacaoCafetaria = grauSatisfacaoCafetaria;
	}
	public String getGrauSatisfacaoMenuCafetaria() {
		return grauSatisfacaoMenuCafetaria;
	}
	public void setGrauSatisfacaoMenuCafetaria(String grauSatisfacaoMenuCafetaria) {
		this.grauSatisfacaoMenuCafetaria = grauSatisfacaoMenuCafetaria;
	}
	public String getGrauSatisfacaoAtendimentoLoja() {
		return grauSatisfacaoAtendimentoLoja;
	}
	public void setGrauSatisfacaoAtendimentoLoja(String grauSatisfacaoAtendimentoLoja) {
		this.grauSatisfacaoAtendimentoLoja = grauSatisfacaoAtendimentoLoja;
	}
	public String getGrauSatisfacaoProdutoLoja() {
		return grauSatisfacaoProdutoLoja;
	}
	public void setGrauSatisfacaoProdutoLoja(String grauSatisfacaoProdutoLoja) {
		this.grauSatisfacaoProdutoLoja = grauSatisfacaoProdutoLoja;
	}
	public String getGrauSatisfacaoLimpeza() {
		return grauSatisfacaoLimpeza;
	}
	public void setGrauSatisfacaoLimpeza(String grauSatisfacaoLimpeza) {
		this.grauSatisfacaoLimpeza = grauSatisfacaoLimpeza;
	}
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
	public String getAreaDeResidenciaOutro() {
		return areaDeResidenciaOutro;
	}
	public void setAreaDeResidenciaOutro(String areaDeResidenciaOutro) {
		this.areaDeResidenciaOutro = areaDeResidenciaOutro;
	}
	
}
