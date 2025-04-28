package ccl.inquerito.serviceImpl;

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
	public void addVisita() {
		/*
		VisitasModel visita = visitaRepository.findById(1L).orElseThrow();
		visita.incrementarContador();
		visitaRepository.save(visita);*/
		
	}

}
