package co.com.security.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.security.app.models.entities.Cliente;
import co.com.security.app.models.services.IClienteService;

@RestController
@RequestMapping("/api")
public class ControllerCliente {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/listar")
	public List<Cliente> listar(){
		return clienteService.findAll();
	}
	
	@PostMapping("/guardar")
	public Cliente save(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@GetMapping("/micuenta")
	public ResponseEntity<String> cuenta() {
		/*String actualUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		String actualRoleUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		System.out.println("username: "+actualUsername);*/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication instanceof AnonymousAuthenticationToken) {
			return new ResponseEntity<>("no se ha iniciado una sesion.", HttpStatus.NOT_FOUND);
		} else {
	        return new ResponseEntity<>("[Username]: " + authentication.getName().concat(" [Roles]:" + authentication.getAuthorities()), HttpStatus.OK);
		}
		
	}
}
