package com.br.desafionekiapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafionekiapi.api.DTO.JwtResponseDTO;
import com.br.desafionekiapi.api.DTO.LoginDTO;
import com.br.desafionekiapi.core.security.JwtUtils;
import com.br.desafionekiapi.core.security.UserDetailsImpl;
import com.br.desafionekiapi.domain.entities.Usuario;
import com.br.desafionekiapi.domain.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
    private UsuarioService usuarioService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
;

		String jwt = jwtUtils.generateToken(userDetails.getUsername(), userDetails.getId());

		return ResponseEntity.ok(new JwtResponseDTO(jwt, userDetails.getId(), userDetails.getUsername()));
	}
}
