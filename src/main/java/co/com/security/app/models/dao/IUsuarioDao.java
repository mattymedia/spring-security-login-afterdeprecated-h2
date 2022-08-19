package co.com.security.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.security.app.models.entities.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);
}
