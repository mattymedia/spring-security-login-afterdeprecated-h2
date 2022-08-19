package co.com.security.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.security.app.models.entities.Usuario;
import co.com.security.app.models.services.IUsuarioService;

@RestController
@RequestMapping("/api")
public class ControllerUsuario {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/buscar/{username}")
	public Usuario findByUsername(@PathVariable String username) {
		Usuario usuario = usuarioService.findByUsername(username);
		return usuario;
	}
}
