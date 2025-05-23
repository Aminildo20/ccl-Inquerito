package ccl.inquerito.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String telemovel;
    private String password;
    private String email;
    private String codigoRecuperacao;
    private LocalDateTime expiracaoCodigoRecuperacao;
    
    public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getCodigoRecuperacao() {
		return codigoRecuperacao;
	}



	public void setCodigoRecuperacao(String codigoRecuperacao) {
		this.codigoRecuperacao = codigoRecuperacao;
	}



	public LocalDateTime getExpiracaoCodigoRecuperacao() {
		return expiracaoCodigoRecuperacao;
	}



	public void setExpiracaoCodigoRecuperacao(LocalDateTime expiracaoCodigoRecuperacao) {
		this.expiracaoCodigoRecuperacao = expiracaoCodigoRecuperacao;
	}

	private String role; // ex: ROLE_USER ou ROLE_ADMIN

	public Long getUserId() {
		return userId;
	}

	

	public String getTelemovel() {
		return telemovel;
	}

	public void setTelemovel(String telemovel) {
		this.telemovel = telemovel;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
}
