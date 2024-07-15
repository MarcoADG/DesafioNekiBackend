package com.br.desafionekiapi.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.desafionekiapi.domain.entities.Skills;
import com.br.desafionekiapi.domain.exception.NegocioException;
import com.br.desafionekiapi.domain.exception.SkillNaoEncontradaException;
import com.br.desafionekiapi.domain.repositories.SkillsRepository;

@Service
public class SkillsService {

	@Autowired
	private SkillsRepository skillsRepository;

	public List<Skills> listarTodasSkills() {
		// Busca todas as skills no banco de dados
		return skillsRepository.findAll();

		//return skillsAssembler.toCollectionDTO(skills);
	}

	public Skills buscarSkillPorId(Integer skillId) {
		// Busca a skill pelo ID fornecido
		return skillsRepository.findById(skillId).orElseThrow(() -> new RuntimeException("Skill não encontrada"));
	}

	@Transactional
	public Skills criarSkill(String imagem, String nome, String descricao) {
		Skills skillExistente = skillsRepository.findByNome(nome);
		if(skillExistente != null) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o login %s", nome));
		}
		
		// Cria uma nova instância de Skills
		Skills skill = new Skills();
		skill.setImagem(imagem);
		skill.setNome(nome);
		skill.setDescricao(descricao);

		// Salva a nova skill no banco de dados e retorna
		return skillsRepository.save(skill);
	}

	@Transactional
	public Skills atualizarSkill(Integer skillId, String novaImagem, String novoNome, String novaDescricao) {
		// Busca a skill pelo ID fornecido
		Skills skill = buscarSkillPorId(skillId);

		// Atualiza os campos da skill, se os novos valores não forem nulos
		if (novaImagem != null | novoNome != null | novaDescricao != null) {
			skill.setImagem(novaImagem);
			skill.setNome(novoNome);
			skill.setDescricao(novaDescricao);
		}
		
		// Salva a atualização no banco de dados e retorna a skill atualizada
		return skillsRepository.save(skill);
	}

	public void deletarSkill(Integer skillId) {
		try {
			skillsRepository.deleteById(skillId);
			skillsRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new SkillNaoEncontradaException(skillId);
		}
	}
}