package ccl.inquerito.serviceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccl.inquerito.model.VisitasModel;
import ccl.inquerito.repository.VisitaRepository;
import ccl.inquerito.service.VisistaService;

@Service
public class VisitaServiceImpl implements VisistaService {
	
	@Autowired
	public VisitaRepository visitaRepository;

	@Override
	public void atualizaNumVisitas() {
		
		VisitasModel visita = visitaRepository.findByIdVisita(1L).orElseThrow();
		visita.incrementarContador();
		visitaRepository.save(visita);		
	}

	@Override
	public Long contaVisita() {
		return visitaRepository.count();
	}
	
	@Override
	public int totalVisita() {
		VisitasModel visita = visitaRepository.findByIdVisita(1L).orElseThrow();		
		return visita.getTotalVisita();
	}
	
	public int inserirVisita(){
		
		VisitasModel visita = new VisitasModel();
		visita.setDateUltimaVisita(LocalDate.now());
		visita.setTotalVisita(1);
		visitaRepository.save(visita);
		
		return 1;
	}

}
