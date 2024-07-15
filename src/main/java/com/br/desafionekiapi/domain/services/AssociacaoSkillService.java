package com.br.desafionekiapi.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.desafionekiapi.api.DTO.AssociacaoSkillRequestDTO;
import com.br.desafionekiapi.api.DTO.UsuarioSkillDTO;
import com.br.desafionekiapi.api.DTO.assemblers.UsuarioSkillAssembler;
import com.br.desafionekiapi.domain.entities.AssociacaoSkill;
import com.br.desafionekiapi.domain.entities.Skills;
import com.br.desafionekiapi.domain.entities.Usuario;
import com.br.desafionekiapi.domain.exception.AssociacaoNaoEncontradaException;
import com.br.desafionekiapi.domain.exception.SkillNaoEncontradaException;
import com.br.desafionekiapi.domain.exception.UsuarioNaoEncontradoException;
import com.br.desafionekiapi.domain.filters.SkillsFilter;
import com.br.desafionekiapi.domain.repositories.AssociacaoSkillRepository;
import com.br.desafionekiapi.domain.repositories.SkillsRepository;
import com.br.desafionekiapi.domain.repositories.UsuarioRepository;
import com.br.desafionekiapi.domain.specs.SkillsSpecs;

import jakarta.transaction.Transactional;

@Service
public class AssociacaoSkillService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SkillsRepository skillsRepository;

	@Autowired
	private AssociacaoSkillRepository associacaoSkillRepository;

	@Autowired
	private UsuarioSkillAssembler usuarioSkillAssembler;

	public Page<UsuarioSkillDTO> listarSkillsDoUsuario(Integer usuarioId, SkillsFilter filtro, Pageable pageable) {
		usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
		
		Page<AssociacaoSkill> pageAssociacoes = associacaoSkillRepository
				.findAll(SkillsSpecs.usandoFiltro(filtro).and(SkillsSpecs.doUsuario(usuarioId)), pageable);

		return usuarioSkillAssembler.toPageDTO(pageAssociacoes);
	}

	public void associarSkillAoUsuario(AssociacaoSkillRequestDTO requestDTO) {
		// Buscar o usuário pelo ID
		Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
				.orElseThrow(() -> new UsuarioNaoEncontradoException(requestDTO.getUsuarioId()));

		// Buscar a skill pelo ID
		Skills skill = skillsRepository.findById(requestDTO.getSkillId())
				.orElseThrow(() -> new SkillNaoEncontradaException(requestDTO.getSkillId()));

		// Criar uma nova associação de skill
		AssociacaoSkill associacaoSkill = new AssociacaoSkill();
		associacaoSkill.setUsuario(usuario);
		associacaoSkill.setSkills(skill);
		associacaoSkill.setLevel(requestDTO.getLevel());

		// Salvar a associação no banco de dados
		associacaoSkillRepository.save(associacaoSkill);
	}

	@Transactional
	public void atualizarAssociacaoSkill(Integer associacaoSkillId, Integer novoLevel) {
		// Busca a associação de skill pelo ID
		AssociacaoSkill associacaoSkill = associacaoSkillRepository.findById(associacaoSkillId)
				.orElseThrow(() -> new AssociacaoNaoEncontradaException(associacaoSkillId));

		// Atualiza o nível da associação de skill
		associacaoSkill.setLevel(novoLevel);

		// Salva a atualização no banco de dados
		associacaoSkillRepository.save(associacaoSkill);
	}

	@Transactional
	public void excluirAssociacaoSkill(Integer associacaoSkillId) {
		try {
			// Exclui a associação de skill do banco de dados
			associacaoSkillRepository.deleteById(associacaoSkillId);
			associacaoSkillRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new AssociacaoNaoEncontradaException(associacaoSkillId);
		}
	}

	public List<AssociacaoSkill> listarTodasAssociacoesSkill() {
		// Retorna todas as associações de skill no banco de dados
		return associacaoSkillRepository.findAll();
	}

	public AssociacaoSkill buscarAssociacaoSkillPorId(Integer associacaoSkillId) {
		// Busca a associação de skill pelo ID
		return associacaoSkillRepository.findById(associacaoSkillId)
				.orElseThrow(() -> new AssociacaoNaoEncontradaException(associacaoSkillId));
	}
}