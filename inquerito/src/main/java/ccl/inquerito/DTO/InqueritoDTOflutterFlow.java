package ccl.inquerito.DTO;

import java.time.LocalDateTime;

public class InqueritoDTOflutterFlow {

	private Long idIquerito;
	private String idade; //1
    private String nivelAcademico;//2
    private String genero;//3
    private String portadorDeDeficiencia;//4
    //5
    private String areaDeResidencia;
    private String areaDeResidenciaOutroProvincia;
    private String areaDeResidenciaOutroPais; //textField outro
    //7
    private String actividadeRemunerada;
    private String actividadeRemuneradaFiled;
    //8
    private String acupacao;
    private String acupacaoField;
    //9
    private String percepcao;
    private String percepcaoFiled;
    //10
    private String origemInfo;
    private String origemInfoFiled;
    
    //11
    private String proposito;
    private String propositoField;
    
    private String experiencia;//12
    //13 - 16
    private String grauSatisfacaoDeQualidade;
    private String grauSatisfacaoDeAtendimento;
    private String grauSatisfacaoInteracao;
    private String satisfacaoPrecoDoBilhete;
    //17
    private String servicoAdicional;
    private String servicoAdicionalField;
    //18 - 23   
    private String grauSatisfacaoCafetaria;
    private String grauSatisfacaoMenuCafetaria;
    private String grauSatisfacaoAtendimentoLoja;
    private String grauSatisfacaoProdutoLoja;
    private String grauSatisfacaoLimpeza;
    
    private String recomendaCcl;
    private String comentario;
    private String email;
    private LocalDateTime dataCriacao;
    
	public Long getIdIquerito() {
		return idIquerito;
	}
	public void setIdIquerito(Long idIquerito) {
		this.idIquerito = idIquerito;
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
	public String getAreaDeResidenciaOutroProvincia() {
		return areaDeResidenciaOutroProvincia;
	}
	public void setAreaDeResidenciaOutroProvincia(String areaDeResidenciaOutroProvincia) {
		this.areaDeResidenciaOutroProvincia = areaDeResidenciaOutroProvincia;
	}
	public String getAreaDeResidenciaOutroPais() {
		return areaDeResidenciaOutroPais;
	}
	public void setAreaDeResidenciaOutroPais(String areaDeResidenciaOutroPais) {
		this.areaDeResidenciaOutroPais = areaDeResidenciaOutroPais;
	}
	public String getActividadeRemunerada() {
		return actividadeRemunerada;
	}
	public void setActividadeRemunerada(String actividadeRemunerada) {
		this.actividadeRemunerada = actividadeRemunerada;
	}
	public String getActividadeRemuneradaFiled() {
		return actividadeRemuneradaFiled;
	}
	public void setActividadeRemuneradaFiled(String actividadeRemuneradaFiled) {
		this.actividadeRemuneradaFiled = actividadeRemuneradaFiled;
	}
	public String getAcupacao() {
		return acupacao;
	}
	public void setAcupacao(String acupacao) {
		this.acupacao = acupacao;
	}
	public String getAcupacaoField() {
		return acupacaoField;
	}
	public void setAcupacaoField(String acupacaoField) {
		this.acupacaoField = acupacaoField;
	}
	public String getPercepcao() {
		return percepcao;
	}
	public void setPercepcao(String percepcao) {
		this.percepcao = percepcao;
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
	public String getPropositoField() {
		return propositoField;
	}
	public void setPropositoField(String propositoField) {
		this.propositoField = propositoField;
	}
	public String getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
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
	public String getServicoAdicional() {
		return servicoAdicional;
	}
	public void setServicoAdicional(String servicoAdicional) {
		this.servicoAdicional = servicoAdicional;
	}
	public String getServicoAdicionalField() {
		return servicoAdicionalField;
	}
	public void setServicoAdicionalField(String servicoAdicionalField) {
		this.servicoAdicionalField = servicoAdicionalField;
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
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}		
	public String getPercepcaoFiled() {
		return percepcaoFiled;
	}
	public void setPercepcaoFiled(String percepcaoFiled) {
		this.percepcaoFiled = percepcaoFiled;
	}
	public String getOrigemInfoFiled() {
		return origemInfoFiled;
	}
	public void setOrigemInfoFiled(String origemInfoFiled) {
		this.origemInfoFiled = origemInfoFiled;
	}
	@Override
	public String toString() {
		return "InqueritoDTOflutterFlow [idade=" + idade + ", nivelAcademico=" + nivelAcademico + ", genero=" + genero
				+ ", portadorDeDeficiencia=" + portadorDeDeficiencia + ", areaDeResidencia=" + areaDeResidencia
				+ ", areaDeResidenciaOutroProvincia=" + areaDeResidenciaOutroProvincia + ", areaDeResidenciaOutroPais="
				+ areaDeResidenciaOutroPais + ", actividadeRemunerada=" + actividadeRemunerada
				+ ", actividadeRemuneradaFiled=" + actividadeRemuneradaFiled + ", acupacao=" + acupacao
				+ ", acupacaoField=" + acupacaoField + ", percepcao=" + percepcao + ", origemInfo="
				+ origemInfo + ", proposito=" + proposito + ", propositoField=" + propositoField + ", experiencia="
				+ experiencia + ", grauSatisfacaoDeQualidade=" + grauSatisfacaoDeQualidade
				+ ", grauSatisfacaoDeAtendimento=" + grauSatisfacaoDeAtendimento + ", grauSatisfacaoInteracao="
				+ grauSatisfacaoInteracao + ", satisfacaoPrecoDoBilhete=" + satisfacaoPrecoDoBilhete
				+ ", servicoAdicional=" + servicoAdicional + ", servicoAdicionalField=" + servicoAdicionalField
				+ ", grauSatisfacaoCafetaria=" + grauSatisfacaoCafetaria + ", grauSatisfacaoMenuCafetaria="
				+ grauSatisfacaoMenuCafetaria + ", grauSatisfacaoAtendimentoLoja=" + grauSatisfacaoAtendimentoLoja
				+ ", grauSatisfacaoProdutoLoja=" + grauSatisfacaoProdutoLoja + ", grauSatisfacaoLimpeza="
				+ grauSatisfacaoLimpeza + ", recomendaCcl=" + recomendaCcl + ", comentario=" + comentario + ", email="
				+ email + ", dataCriacao=" + dataCriacao + "]";
	}
	
    
    
}
