package co.com.security.app.models.services;

import co.com.security.app.models.entities.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);

}
