package co.com.security.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
