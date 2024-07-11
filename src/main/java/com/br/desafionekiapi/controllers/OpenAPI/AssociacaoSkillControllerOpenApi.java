package com.br.desafionekiapi.controllers.OpenAPI;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.desafionekiapi.DTO.AssociacaoSkillRequestDTO;
import com.br.desafionekiapi.repositories.filters.SkillsFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

public interface AssociacaoSkillControllerOpenApi {

	public ResponseEntity<?> buscarAssociacaoSkillPorId(@PathVariable Integer associacaoSkillId);

	public ResponseEntity<?> listarTodasAssociacoesSkill();

	@Parameter(in = ParameterIn.QUERY, name = "page", description = "Número da página (0..N)", schema = @Schema(type = "integer", defaultValue = "0"))
	@Parameter(in = ParameterIn.QUERY, name = "size", description = "Quantidade de elementos por página", schema = @Schema(type = "integer", defaultValue = "10"))
	@Parameter(in = ParameterIn.QUERY, name = "sort", description = "Critério de ordenação: propriedade(asc|desc).", examples = {
			@ExampleObject("nome"), @ExampleObject("nome,asc"), @ExampleObject("nome,desc") })
	public ResponseEntity<?> listarAssociacoesSkillPorUsuarioId(@PathVariable Integer usuarioId,
			@Parameter(hidden = true) Pageable pageable);

	public ResponseEntity<?> associarSkillAoUsuario(@RequestBody AssociacaoSkillRequestDTO requestDTO);

	public ResponseEntity<?> atualizarAssociacaoSkill(@PathVariable Integer associacaoSkillId,
			@RequestBody AssociacaoSkillRequestDTO requestDTO);

	public ResponseEntity<?> deletarAssociacaoSkill(@PathVariable Integer associacaoSkillId);
}
