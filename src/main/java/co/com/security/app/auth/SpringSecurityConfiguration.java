package co.com.security.app.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration {

	//metodo que transforma un password en en el request y lo codifica para compararlo con el password que esta encriptado en la bd
	@Bean
	PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*	bean de autenticacionque se apoya de la interfaz UserDetailsService (Metodo abstracto "loadUserByUsername") para llamar 
	su implementacion declarada en "ImplUsuarioService" y obtener las credenciales del ususario de la bd y guardar estas en un 
	objeto sesion para la app.*/
	@Bean
	AuthenticationManager authManager(HttpSecurity http, UserDetailsService userDetailService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailService)
				.passwordEncoder(bCryptPasswordEncoder()).and().build();
	}

	//filtros de seguridad en las rutas
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/listar", "/api/micuenta").permitAll()
				.antMatchers(HttpMethod.POST, "/api/guardar").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/editar").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/eliminar").hasRole("ADMIN")
				.antMatchers("/auth/**").permitAll()
				.anyRequest().authenticated()
				.and().httpBasic()
				.and()
				.csrf().disable()
				;

		return http.build();
	}

}
