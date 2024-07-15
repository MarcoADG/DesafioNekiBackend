package com.br.desafionekiapi.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.desafionekiapi.api.DTO.SkillResponseDTO;
import com.br.desafionekiapi.api.DTO.assemblers.SkillAssembler;
import com.br.desafionekiapi.domain.entities.Skills;
import com.br.desafionekiapi.domain.entities.Usuario;
import com.br.desafionekiapi.domain.repositories.SkillsRepository;
import com.br.desafionekiapi.domain.repositories.UsuarioRepository;

@Service
public class SkillsService {

	@Autowired
	private SkillsRepository skillsRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SkillAssembler skillsAssembler;

	public List<SkillResponseDTO> listarTodasSkills() {
		// Busca todas as skills no banco de dados
		List<Skills> skills = skillsRepository.findAll();

		return skillsAssembler.toCollectionDTO(skills);
	}

	public Skills buscarSkillPorId(Integer skillId) {
		// Busca a skill pelo ID fornecido
		return skillsRepository.findById(skillId).orElseThrow(() -> new RuntimeException("Skill não encontrada"));
	}

	public Skills criarSkill(String imagem, String nome, String descricao) {
		// Cria uma nova instância de Skills
		Skills skill = new Skills();
		skill.setImagem(imagem);
		skill.setNome(nome);
		skill.setDescricao(descricao);

		// Salva a nova skill no banco de dados e retorna
		return skillsRepository.save(skill);
	}

	public Skills atualizarSkill(Integer skillId, String novaImagem, String novoNome, String novaDescricao) {
		// Busca a skill pelo ID fornecido
		Skills skill = skillsRepository.findById(skillId)
				.orElseThrow(() -> new RuntimeException("Skill não encontrada"));

		// Atualiza os campos da skill, se os novos valores não forem nulos
		if (novaImagem != null) {
			skill.setImagem(novaImagem);
		}
		if (novoNome != null) {
			skill.setNome(novoNome);
		}
		if (novaDescricao != null) {
			skill.setDescricao(novaDescricao);
		}

		// Salva a atualização no banco de dados e retorna a skill atualizada
		return skillsRepository.save(skill);
	}

	public void deletarSkill(Integer skillId) {
		// Busca a skill pelo ID fornecido
		Skills skill = skillsRepository.findById(skillId)
				.orElseThrow(() -> new RuntimeException("Skill não encontrada"));

		// Exclui a skill do banco de dados
		skillsRepository.delete(skill);
	}
}