package com.br.desafionekiapi.api.controllers.OpenAPI;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.desafionekiapi.api.DTO.UsuarioRequestDTO;
import com.br.desafionekiapi.api.DTO.UsuarioResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario")
public interface UsuarioControllerOpenApi {

	@Operation(summary = "Busca um Usuario por ID")
	public UsuarioResponseDTO buscarUsuarioPorId(@PathVariable Integer id);

	@Operation(summary = "Lista todos Usuarios")
	public List<UsuarioResponseDTO> listarTodosUsuarios() throws Exception;

	@Operation(summary = "Atualiza um Usuario")
	public String atualizarUsuario(@PathVariable Integer usuarioId,
			@RequestBody UsuarioRequestDTO requestDTO);

	@Operation(summary = "Delete um Usuario")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Integer usuarioId);

}
