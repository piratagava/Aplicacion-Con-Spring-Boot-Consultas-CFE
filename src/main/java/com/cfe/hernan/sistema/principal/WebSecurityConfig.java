package com.cfe.hernan.sistema.principal;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.cfe.hernan.sistema.configuration.JWTConfigurer;
import com.cfe.hernan.sistema.configuration.TokenProvider;
import com.cfe.hernan.sistema.security.JwtAccessDeniedHandler;
import com.cfe.hernan.sistema.security.JwtAuthenticationEntryPoint;
import com.jayway.jsonpath.internal.Path;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final TokenProvider tokenProvider;
	private final CorsFilter corsFilter;
	private final JwtAuthenticationEntryPoint authenticationErrorHandler;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	public WebSecurityConfig(TokenProvider tokenProvider, CorsFilter corsFilter,
			JwtAuthenticationEntryPoint authenticationErrorHandler, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
		this.tokenProvider = tokenProvider;
		this.corsFilter = corsFilter;
		this.authenticationErrorHandler = authenticationErrorHandler;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}

	// Configure BCrypt password encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Configuracion de rutas y extensiones que permite Spring Security a todos los user Browser
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
				.antMatchers("/",							 
						     "/*.jsp",
						     "/favicon.ico",
						     "/**/*.jsp",
						     "/**/*.css",
						     "/**/*.map",
						     "/**/*.js",
						     "/**/*.svg",
						     "/**/*.jpg",
						     "/**/*.png",
						     "/**/*.jpeg",
						     "/h2-console/**");
	}

	// Configure security settings
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf().disable()
				.cors().and()
				.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
				.authenticationEntryPoint(authenticationErrorHandler).accessDeniedHandler(jwtAccessDeniedHandler)

				.and().headers().frameOptions().sameOrigin()

				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/api/authenticate").permitAll()
				.antMatchers("/Administrador").permitAll()
				.antMatchers("/AltaCliente").permitAll()
				.antMatchers("/AltaRole").permitAll()
				.antMatchers("/AltaComunidad").permitAll()
				.antMatchers("/ClienteRole").permitAll()
				.antMatchers("/DetalleServicio").permitAll()

				// son Matchers pero para obtener recursos de post get put delete
				.antMatchers("/insertarCliente").hasAuthority("ROLE_ADMIN")				
				.antMatchers("/actualizarCliente").hasAuthority("ROLE_ADMIN")
				.antMatchers("/eliminarCliente").hasAuthority("ROLE_ADMIN")
				.antMatchers("/consultaIdClienteReciente").hasAuthority("ROLE_ADMIN")
				.antMatchers("/listaCliente").hasAuthority("ROLE_ADMIN")
				
				.antMatchers("/insertarClienteRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/listarRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/insertarRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/eliminarRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/actualizarRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/consultarIdRole").hasAuthority("ROLE_ADMIN")
				
				.antMatchers("/insertarClienteRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/listarClienteRole").hasAuthority("ROLE_ADMIN")
				.antMatchers("/actualizarClienteRole").hasAuthority("ROLE_ADMIN")
				
				.antMatchers("/insertarComunidad").hasAuthority("ROLE_ADMIN")				
				.antMatchers("/listaComunidad").hasAuthority("ROLE_ADMIN")			
				.antMatchers("/actualizarComunidad").hasAuthority("ROLE_ADMIN")				
				.antMatchers("/eliminarComunidad").hasAuthority("ROLE_ADMIN")
				
				.antMatchers("/insertarDetalleService").hasAuthority("ROLE_ADMIN")					
				.antMatchers("/listaDetalleServicio").hasAuthority("ROLE_ADMIN")					
				.antMatchers("/actualizarDetalleService").hasAuthority("ROLE_ADMIN")	
				.antMatchers("/deleteDetalleService").hasAuthority("ROLE_ADMIN")

				//creo que no sirve para los dos solo uno debe tener ese ROLE
				.antMatchers("/api/cliente").hasAuthority("ROLE_USER")
				.antMatchers("/api/consultaDetalleServicioCliente").hasAuthority("ROLE_USER")
				.anyRequest().authenticated()

				.and().formLogin() // (5)
				.loginPage("/es-login-page-Ing_Hernan") // (5)
				.permitAll().and().logout() // (6)
				.permitAll().and()

				.apply(securityConfigurerAdapter());
	}

	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
