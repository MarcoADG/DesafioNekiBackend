package com.br.desafionekiapi.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafionekiapi.api.DTO.SkillResponseDTO;
import com.br.desafionekiapi.api.DTO.SkillsRequestDTO;
import com.br.desafionekiapi.api.DTO.assemblers.SkillAssembler;
import com.br.desafionekiapi.api.controllers.OpenAPI.SkillsControllerOpenApi;
import com.br.desafionekiapi.domain.entities.Skills;
import com.br.desafionekiapi.domain.exception.NegocioException;
import com.br.desafionekiapi.domain.exception.SkillNaoEncontradaException;
import com.br.desafionekiapi.domain.services.SkillsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/skills")
public class SkillsController implements SkillsControllerOpenApi {

	@Autowired
	private SkillsService skillsService;

	@Autowired
	private SkillAssembler skillsAssembler;

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/{skillId}")
	public SkillResponseDTO buscarSkillPorId(@PathVariable Integer skillId) {
		Skills skill = skillsService.buscarSkillPorId(skillId);
		return skillsAssembler.toDTO(skill);
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping
	public List<SkillResponseDTO> listarTodasSkills() {
		List<Skills> skills = skillsService.listarTodasSkills();
		return skillsAssembler.toCollectionDTO(skills);
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PostMapping("/criar")
	@ResponseStatus(HttpStatus.CREATED)
	public String criarSkill(@RequestBody @Valid SkillsRequestDTO requestDTO) {
		try {
			skillsService.criarSkill(requestDTO.getImagem(), requestDTO.getNome(), requestDTO.getDescricao());
			return "Skill criada com sucesso";
		} catch (RuntimeException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PutMapping("/{skillId}")
	public String atualizarSkill(@PathVariable Integer skillId, @RequestBody @Valid SkillsRequestDTO requestDTO) {
		try {
			skillsService.atualizarSkill(skillId, requestDTO.getImagem(), requestDTO.getNome(),
					requestDTO.getDescricao());
			return "Skill atualizada com sucesso";
		} catch (SkillNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@DeleteMapping("/{skillId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deletarSkill(@PathVariable Integer skillId) {

		skillsService.deletarSkill(skillId);
		return ResponseEntity.noContent().build();
	}
}
