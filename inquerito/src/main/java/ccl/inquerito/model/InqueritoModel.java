package ccl.inquerito.model;

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
    private String actividadeRemunerada;
    private String acupacao;
    private String percepcao;
    private String origem_info;
    private String proposito;
    private String experiencia;
    private String grau_satisfacao_de_qualidade;
    private String grau_satisfacao_de_atendimento;
    private String grau_satisfacao_interacao;
    private String satisfacao_preco_do_bilhete;
    private String servico_adicional;
    private String grau_satisfacao_cafetaria;
    private String grau_satisfacao_menu_cafetaria;
    private String grau_satisfacao_atendimento_loja;
    private String grau_satisfacao_produto_loja;
    private String grau_satisfacao_limpeza;
    private String recomenda_ccl;
    private String comentario;
    private String email;

    public InqueritoModel() {}

	public InqueritoModel(Long idIquerito, String idade, String nivelAcademico, String genero,
			String portadorDeDeficiencia, String areaDeResidencia, String actividadeRemunerada, String acupacao,
			String percepcao, String origem_info, String proposito, String experiencia,
			String grau_satisfacao_de_qualidade, String grau_satisfacao_de_atendimento,
			String grau_satisfacao_interacao, String satisfacao_preco_do_bilhete, String servico_adicional,
			String grau_satisfacao_cafetaria, String grau_satisfacao_menu_cafetaria,
			String grau_satisfacao_atendimento_loja, String grau_satisfacao_produto_loja,
			String grau_satisfacao_limpeza, String recomenda_ccl, String comentario, String email) {
		super();
		this.idIquerito = idIquerito;
		this.idade = idade;
		this.nivelAcademico = nivelAcademico;
		this.genero = genero;
		this.portadorDeDeficiencia = portadorDeDeficiencia;
		this.areaDeResidencia = areaDeResidencia;
		this.actividadeRemunerada = actividadeRemunerada;
		this.acupacao = acupacao;
		this.percepcao = percepcao;
		this.origem_info = origem_info;
		this.proposito = proposito;
		this.experiencia = experiencia;
		this.grau_satisfacao_de_qualidade = grau_satisfacao_de_qualidade;
		this.grau_satisfacao_de_atendimento = grau_satisfacao_de_atendimento;
		this.grau_satisfacao_interacao = grau_satisfacao_interacao;
		this.satisfacao_preco_do_bilhete = satisfacao_preco_do_bilhete;
		this.servico_adicional = servico_adicional;
		this.grau_satisfacao_cafetaria = grau_satisfacao_cafetaria;
		this.grau_satisfacao_menu_cafetaria = grau_satisfacao_menu_cafetaria;
		this.grau_satisfacao_atendimento_loja = grau_satisfacao_atendimento_loja;
		this.grau_satisfacao_produto_loja = grau_satisfacao_produto_loja;
		this.grau_satisfacao_limpeza = grau_satisfacao_limpeza;
		this.recomenda_ccl = recomenda_ccl;
		this.comentario = comentario;
		this.email = email;
	}

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

	public String getOrigem_info() {
		return origem_info;
	}

	public void setOrigem_info(String origem_info) {
		this.origem_info = origem_info;
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

	public String getGrau_satisfacao_de_qualidade() {
		return grau_satisfacao_de_qualidade;
	}

	public void setGrau_satisfacao_de_qualidade(String grau_satisfacao_de_qualidade) {
		this.grau_satisfacao_de_qualidade = grau_satisfacao_de_qualidade;
	}

	public String getGrau_satisfacao_de_atendimento() {
		return grau_satisfacao_de_atendimento;
	}

	public void setGrau_satisfacao_de_atendimento(String grau_satisfacao_de_atendimento) {
		this.grau_satisfacao_de_atendimento = grau_satisfacao_de_atendimento;
	}

	public String getGrau_satisfacao_interacao() {
		return grau_satisfacao_interacao;
	}

	public void setGrau_satisfacao_interacao(String grau_satisfacao_interacao) {
		this.grau_satisfacao_interacao = grau_satisfacao_interacao;
	}

	public String getSatisfacao_preco_do_bilhete() {
		return satisfacao_preco_do_bilhete;
	}

	public void setSatisfacao_preco_do_bilhete(String satisfacao_preco_do_bilhete) {
		this.satisfacao_preco_do_bilhete = satisfacao_preco_do_bilhete;
	}

	public String getServico_adicional() {
		return servico_adicional;
	}

	public void setServico_adicional(String servico_adicional) {
		this.servico_adicional = servico_adicional;
	}

	public String getGrau_satisfacao_cafetaria() {
		return grau_satisfacao_cafetaria;
	}

	public void setGrau_satisfacao_cafetaria(String grau_satisfacao_cafetaria) {
		this.grau_satisfacao_cafetaria = grau_satisfacao_cafetaria;
	}

	public String getGrau_satisfacao_menu_cafetaria() {
		return grau_satisfacao_menu_cafetaria;
	}

	public void setGrau_satisfacao_menu_cafetaria(String grau_satisfacao_menu_cafetaria) {
		this.grau_satisfacao_menu_cafetaria = grau_satisfacao_menu_cafetaria;
	}

	public String getGrau_satisfacao_atendimento_loja() {
		return grau_satisfacao_atendimento_loja;
	}

	public void setGrau_satisfacao_atendimento_loja(String grau_satisfacao_atendimento_loja) {
		this.grau_satisfacao_atendimento_loja = grau_satisfacao_atendimento_loja;
	}

	public String getGrau_satisfacao_produto_loja() {
		return grau_satisfacao_produto_loja;
	}

	public void setGrau_satisfacao_produto_loja(String grau_satisfacao_produto_loja) {
		this.grau_satisfacao_produto_loja = grau_satisfacao_produto_loja;
	}

	public String getGrau_satisfacao_limpeza() {
		return grau_satisfacao_limpeza;
	}

	public void setGrau_satisfacao_limpeza(String grau_satisfacao_limpeza) {
		this.grau_satisfacao_limpeza = grau_satisfacao_limpeza;
	}

	public String getRecomenda_ccl() {
		return recomenda_ccl;
	}

	public void setRecomenda_ccl(String recomenda_ccl) {
		this.recomenda_ccl = recomenda_ccl;
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
    
}

