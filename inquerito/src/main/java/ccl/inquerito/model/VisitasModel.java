package ccl.inquerito.model;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class VisitasModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVisita;

    private int totalVisita = 0;
    private LocalDateTime dateUltimaVisita;
    
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
	public LocalDateTime getDateUltimaVisita() {
		return dateUltimaVisita;
	}
	public void setDateUltimaVisita(LocalDateTime dateUltimaVisita) {
		this.dateUltimaVisita = dateUltimaVisita;
	}
	
}
