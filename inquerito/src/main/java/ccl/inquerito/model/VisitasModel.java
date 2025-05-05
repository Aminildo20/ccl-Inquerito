package ccl.inquerito.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "visitas")
public class VisitasModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVisita;

    private int totalVisita = 0;
    private LocalDate dateUltimaVisita;
    
 // Método para incrementar
    public void incrementarContador() {
        this.totalVisita++;
    }
    
	public Long getIdVisita() {
		return idVisita;
	}
	public void setIdVisita(Long idVisita) {
		this.idVisita = idVisita;
	}
	public int getTotalVisita() {
		return totalVisita;
	}
	public void setTotalVisita(int totalVisita) {
		this.totalVisita = totalVisita;
	}
	public LocalDate getDateUltimaVisita() {
		return dateUltimaVisita;
	}
	public void setDateUltimaVisita(LocalDate dateUltimaVisita) {
		this.dateUltimaVisita = dateUltimaVisita;
	}
	
}
