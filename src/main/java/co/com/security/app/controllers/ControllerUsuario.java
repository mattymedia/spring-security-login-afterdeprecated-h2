package co.com.security.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import co.com.security.app.models.entities.Usuario;
import co.com.security.app.models.services.IUsuarioService;

@RestController
@RequestMapping("/auth")
public class ControllerUsuario {
	
	//injectamos la clase AuthenticationManager con el fin de usar sus metodos abstractos
	@Autowired
	private AuthenticationManager authenticationManager;
	 
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/buscar/{username}")
	public Usuario findByUsername(@PathVariable String username) {
		Usuario usuario = usuarioService.findByUsername(username);
		return usuario;
	}
	
    @PostMapping("/ingresar")
    public ResponseEntity<String> authenticateUser(@RequestBody Usuario usuario){
    	
    	//creamos un objeto token authentication, nos valemos de la clase.metodo abstracto (authenticationManager.authenticate)
    	//asignamos las credenciales(username y password)
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                usuario.getUsername(), usuario.getPassword()));

        //asignamos los roles al token y damos acceso al usuario a la app mediante el(token).
        SecurityContextHolder.getContext().setAuthentication(authentication);
		String actualUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        return new ResponseEntity<>("Usuario [" + actualUsername + "], inicio sesion correctamente!", HttpStatus.OK);
    }
    
    
}
