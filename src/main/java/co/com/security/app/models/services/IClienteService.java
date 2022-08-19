package co.com.security.app.models.services;

import java.util.List;

import co.com.security.app.models.entities.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
}
