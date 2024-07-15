package com.br.desafionekiapi.api.controllers.OpenAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.desafionekiapi.api.DTO.SkillsRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Skills")
public interface SkillsControllerOpenApi {


	@Operation(summary = "Busca uma skill por ID")
    public ResponseEntity<?> buscarSkillPorId(@PathVariable Integer skillId);

	@Operation(summary = "Busca todas as skills")
    public ResponseEntity<?> listarTodasSkills();

	@Operation(summary = "Cria uma skill")
    public ResponseEntity<?> criarSkill(@RequestBody SkillsRequestDTO requestDTO);

	@Operation(summary = "Atualiza uma skill por ID")
    public ResponseEntity<?> atualizarSkill(@PathVariable Integer skillId, @RequestBody SkillsRequestDTO requestDTO);

	@Operation(summary = "Deleta uma skill por ID")
    public ResponseEntity<?> deletarSkill(@PathVariable Integer skillId);
}
