package ccl.inquerito.service;

import org.springframework.stereotype.Service;

@Service
public interface VisistaService {

	public void atualizaNumVisitas();
	public Long contaVisita();
	public int totalVisita();
}
