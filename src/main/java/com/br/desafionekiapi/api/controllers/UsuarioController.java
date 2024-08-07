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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafionekiapi.api.DTO.UsuarioRequestDTO;
import com.br.desafionekiapi.api.DTO.UsuarioResponseDTO;
import com.br.desafionekiapi.api.DTO.assemblers.UsuarioAssembler;
import com.br.desafionekiapi.api.controllers.OpenAPI.UsuarioControllerOpenApi;
import com.br.desafionekiapi.domain.entities.Usuario;
import com.br.desafionekiapi.domain.exception.NegocioException;
import com.br.desafionekiapi.domain.exception.UsuarioNaoEncontradoException;
import com.br.desafionekiapi.domain.services.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioAssembler usuarioAssembler;

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/{id}")
	public UsuarioResponseDTO buscarUsuarioPorId(@PathVariable Integer id) {
		Usuario usuario = usuarioService.buscarPorId(id);

		return usuarioAssembler.toDTO(usuario);

	}

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping
	public List<UsuarioResponseDTO> listarTodosUsuarios() throws Exception {
		List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
		List<UsuarioResponseDTO> usuarioDTOs = usuarioAssembler.toCollectionDTO(usuarios);

		if (!usuarioDTOs.isEmpty()) {
			return usuarioDTOs;
		} else {
			throw new Exception("Nenhum usuário encontrado");
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PutMapping("/{usuarioId}")
	public String atualizarUsuario(@PathVariable Integer usuarioId, @RequestBody @Valid UsuarioRequestDTO requestDTO) {
		try {
			usuarioService.atualizarUsuario(usuarioId, requestDTO.getLogin(), requestDTO.getSenha());
			return "Usuário atualizado com sucesso";
		} catch (UsuarioNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deletarUsuario(@PathVariable Integer usuarioId) {

		usuarioService.deletarUsuario(usuarioId);
		return ResponseEntity.noContent().build();

	}

}
