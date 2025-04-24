package ccl.inquerito.service;

import org.springframework.stereotype.Service;

import ccl.inquerito.model.InqueritoModel;

@Service
public interface InqueritoService {

	public InqueritoModel novoInquerito(InqueritoModel iquerito);
}
