package com.br.desafionekiapi.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.br.desafionekiapi.domain.exception.NegocioException;
import com.br.desafionekiapi.domain.exception.SkillNaoEncontradaException;
import com.br.desafionekiapi.api.DTO.AssociacaoSkillRequestDTO;
import com.br.desafionekiapi.api.DTO.UsuarioSkillDTO;
import com.br.desafionekiapi.api.DTO.assemblers.UsuarioSkillAssembler;
import com.br.desafionekiapi.api.controllers.OpenAPI.AssociacaoSkillControllerOpenApi;
import com.br.desafionekiapi.domain.entities.AssociacaoSkill;
import com.br.desafionekiapi.domain.exception.UsuarioNaoEncontradoException;
import com.br.desafionekiapi.domain.filters.SkillsFilter;
import com.br.desafionekiapi.domain.services.AssociacaoSkillService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/associacoes")
public class AssociacaoSkillController implements AssociacaoSkillControllerOpenApi {

	@Autowired
	private AssociacaoSkillService associacaoSkillService;

	@Autowired
	private UsuarioSkillAssembler usuarioSkillAssembler;

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/{associacaoSkillId}")
	public UsuarioSkillDTO buscarAssociacaoSkillPorId(@PathVariable Integer associacaoSkillId) {
		AssociacaoSkill skill = associacaoSkillService.buscarAssociacaoSkillPorId(associacaoSkillId);

		return usuarioSkillAssembler.toDTO(skill);
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping
	public List<UsuarioSkillDTO> listarTodasAssociacoesSkill() {
		List<AssociacaoSkill> skill = associacaoSkillService.listarTodasAssociacoesSkill();

		return usuarioSkillAssembler.toCollectionDTO(skill);

	}

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/usuario/{usuarioId}/skills")
	public Page<UsuarioSkillDTO> listarAssociacoesSkillPorUsuarioId(@PathVariable Integer usuarioId,
			SkillsFilter filtro, Pageable pageable) {
		try {
			return associacaoSkillService.listarSkillsDoUsuario(usuarioId, filtro, pageable);
		} catch (UsuarioNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PostMapping("/associar")
	@ResponseStatus(HttpStatus.CREATED)
	public String associarSkillAoUsuario(@RequestBody @Valid AssociacaoSkillRequestDTO requestDTO) {
		try {
			associacaoSkillService.associarSkillAoUsuario(requestDTO);
			return "Associação de skill realizada com sucesso";
		} catch (UsuarioNaoEncontradoException | SkillNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PutMapping("/{associacaoSkillId}")
	public String atualizarAssociacaoSkill(@PathVariable Integer associacaoSkillId,
			@RequestBody @Valid AssociacaoSkillRequestDTO requestDTO) {
		try {
			associacaoSkillService.atualizarAssociacaoSkill(associacaoSkillId, requestDTO.getLevel());
			return "Associação de skill atualizada com sucesso";
		} catch (UsuarioNaoEncontradoException | SkillNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@DeleteMapping("/{associacaoSkillId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deletarAssociacaoSkill(@PathVariable Integer associacaoSkillId) {

		associacaoSkillService.excluirAssociacaoSkill(associacaoSkillId);
		return ResponseEntity.noContent().build();
	}
}
