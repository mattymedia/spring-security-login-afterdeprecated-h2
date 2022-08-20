package co.com.security.app.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.security.app.models.dao.IUsuarioDao;
import co.com.security.app.models.entities.Usuario;

@Service
public class ImplUsuarioService implements UserDetailsService,  IUsuarioService {

	private Logger log = LoggerFactory.getLogger(IUsuarioService.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	
	//metodo sobreescrito de la interfaz "UserDetailsService", toma un objeto usuario y crea con el un user.security y le asigna credenciales
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("error al iniciar sesion, no exsite el usuario: " + username);
		}
		
		List<GrantedAuthority> rolesUsuario = usuario.getRole()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName()))//.map(funcion(valor){ return valor + instruccion})
				.peek(auth -> log.info("roles: " + auth.getAuthority()))//mostrar en la consola
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), rolesUsuario);
		
	}

}
