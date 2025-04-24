package ccl.inquerito.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccl.inquerito.model.InqueritoModel;
import ccl.inquerito.repository.InqueritoRepository;
import ccl.inquerito.service.InqueritoService;

@Service
public class InqueritoServiceImpl implements InqueritoService{
	
	@Autowired
	private InqueritoRepository inqueritoRepository;

	@Override
	public InqueritoModel novoInquerito(InqueritoModel inquerito) {
		
		return inqueritoRepository.save(inquerito);
	}

	
}
