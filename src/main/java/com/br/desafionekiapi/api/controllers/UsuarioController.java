package com.br.desafionekiapi.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafionekiapi.api.DTO.UsuarioRequestDTO;
import com.br.desafionekiapi.api.DTO.UsuarioResponseDTO;
import com.br.desafionekiapi.api.DTO.assemblers.UsuarioAssembler;
import com.br.desafionekiapi.domain.entities.Usuario;
import com.br.desafionekiapi.domain.services.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioAssembler usuarioAssembler;

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/{id}")
	public UsuarioResponseDTO buscarUsuarioPorId(@PathVariable Integer id) throws Exception {
		Usuario usuario = usuarioService.buscarPorId(id);
		
		return usuarioAssembler.toDTO(usuario);

	}

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosUsuarios() throws Exception {
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
        List<UsuarioResponseDTO> usuarioDTOs = usuarioAssembler.toCollectionDTO(usuarios);
        
        if (!usuarioDTOs.isEmpty()) {
            return ResponseEntity.ok(usuarioDTOs);
        } else {
            throw new Exception("Nenhum usuário encontrado");
        }
    }

	@SecurityRequirement(name = "Bearer Authentication")
	@PutMapping("/{usuarioId}")
	public ResponseEntity<?> atualizarUsuario(@PathVariable Integer usuarioId,
			@RequestBody UsuarioRequestDTO requestDTO) {
		try {
			usuarioService.atualizarUsuario(usuarioId, requestDTO.getLogin(), requestDTO.getSenha());
			return ResponseEntity.ok("Usuário atualizado com sucesso");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Integer usuarioId) {
		try {
			usuarioService.deletarUsuario(usuarioId);
			return ResponseEntity.ok("Usuário deletado com sucesso");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
