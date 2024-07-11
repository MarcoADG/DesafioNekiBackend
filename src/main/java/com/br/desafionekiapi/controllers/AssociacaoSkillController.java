package com.br.desafionekiapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.br.desafionekiapi.DTO.AssociacaoSkillRequestDTO;
import com.br.desafionekiapi.controllers.OpenAPI.AssociacaoSkillControllerOpenApi;
import com.br.desafionekiapi.repositories.filters.SkillsFilter;
import com.br.desafionekiapi.services.AssociacaoSkillService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/associacoes")
public class AssociacaoSkillController implements AssociacaoSkillControllerOpenApi {

	@Autowired
	private AssociacaoSkillService associacaoSkillService;

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/{associacaoSkillId}")
	public ResponseEntity<?> buscarAssociacaoSkillPorId(@PathVariable Integer associacaoSkillId) {
		try {
			return ResponseEntity.ok(associacaoSkillService.buscarAssociacaoSkillPorId(associacaoSkillId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping
	public ResponseEntity<?> listarTodasAssociacoesSkill() {
		try {
			return ResponseEntity.ok(associacaoSkillService.listarTodasAssociacoesSkill());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> listarAssociacoesSkillPorUsuarioId(@PathVariable Integer usuarioId, Pageable pageable) {
		try {

			return ResponseEntity.ok(associacaoSkillService.listarSkillsDoUsuario(usuarioId, pageable));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PostMapping("/associar")
	public ResponseEntity<?> associarSkillAoUsuario(@RequestBody AssociacaoSkillRequestDTO requestDTO) {
		try {
			associacaoSkillService.associarSkillAoUsuario(requestDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Associação de skill realizada com sucesso");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PutMapping("/{associacaoSkillId}")
	public ResponseEntity<?> atualizarAssociacaoSkill(@PathVariable Integer associacaoSkillId,
			@RequestBody AssociacaoSkillRequestDTO requestDTO) {
		try {
			associacaoSkillService.atualizarAssociacaoSkill(associacaoSkillId, requestDTO.getLevel());
			return ResponseEntity.ok("Associação de skill atualizada com sucesso");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@DeleteMapping("/{associacaoSkillId}")
	public ResponseEntity<?> deletarAssociacaoSkill(@PathVariable Integer associacaoSkillId) {
		try {
			associacaoSkillService.excluirAssociacaoSkill(associacaoSkillId);
			return ResponseEntity.ok("Associação de skill deletada com sucesso");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
