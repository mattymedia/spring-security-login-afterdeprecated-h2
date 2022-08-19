package co.com.security.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.security.app.models.entities.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Integer> {
	
}
