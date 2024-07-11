package com.br.desafionekiapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.desafionekiapi.DTO.AssociacaoSkillRequestDTO;
import com.br.desafionekiapi.DTO.UsuarioSkillDTO;
import com.br.desafionekiapi.entities.AssociacaoSkill;
import com.br.desafionekiapi.entities.Skills;
import com.br.desafionekiapi.entities.Usuario;
import com.br.desafionekiapi.repositories.AssociacaoSkillRepository;
import com.br.desafionekiapi.repositories.SkillsRepository;
import com.br.desafionekiapi.repositories.UsuarioRepository;
import com.br.desafionekiapi.repositories.filters.SkillsFilter;
import com.br.desafionekiapi.repositories.specs.SkillsSpecs;

@Service
public class AssociacaoSkillService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SkillsRepository skillsRepository;

	@Autowired
	private AssociacaoSkillRepository associacaoSkillRepository;

	public List<UsuarioSkillDTO> listarSkillsDoUsuario(Integer usuarioId, Pageable pageable) {
	    List<UsuarioSkillDTO> usuarioSkills = new ArrayList<>();

	    // Busca todas as associações de skill do usuário pelo ID com paginação
	    Page<AssociacaoSkill> pageAssociacoes = associacaoSkillRepository.findByUsuarioId(usuarioId, pageable);

	    // Converte as associações de skill para UsuarioSkillDTO
	    for (AssociacaoSkill associacao : pageAssociacoes.getContent()) {
	        UsuarioSkillDTO usuarioSkillDTO = new UsuarioSkillDTO();
	        usuarioSkillDTO.setId(associacao.getId());
	        usuarioSkillDTO.setSkillId(associacao.getSkills().getId());
	        usuarioSkillDTO.setImagem(associacao.getSkills().getImagem());
	        usuarioSkillDTO.setNome(associacao.getSkills().getNome());
	        usuarioSkillDTO.setDescricao(associacao.getSkills().getDescricao());
	        usuarioSkillDTO.setLevel(associacao.getLevel());

	        usuarioSkills.add(usuarioSkillDTO);
	    }

	    return usuarioSkills;
	}

	public void associarSkillAoUsuario(AssociacaoSkillRequestDTO requestDTO) {
		// Buscar o usuário pelo ID
		Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		// Buscar a skill pelo ID
		Skills skill = skillsRepository.findById(requestDTO.getSkillId())
				.orElseThrow(() -> new RuntimeException("Skill não encontrada"));

		// Criar uma nova associação de skill
		AssociacaoSkill associacaoSkill = new AssociacaoSkill();
		associacaoSkill.setUsuario(usuario);
		associacaoSkill.setSkills(skill);
		associacaoSkill.setLevel(requestDTO.getLevel());

		// Salvar a associação no banco de dados
		associacaoSkillRepository.save(associacaoSkill);
	}

	public void atualizarAssociacaoSkill(Integer associacaoSkillId, Integer novoLevel) {
		// Busca a associação de skill pelo ID
		AssociacaoSkill associacaoSkill = associacaoSkillRepository.findById(associacaoSkillId)
				.orElseThrow(() -> new RuntimeException("Associação de skill não encontrada"));

		// Atualiza o nível da associação de skill
		associacaoSkill.setLevel(novoLevel);

		// Salva a atualização no banco de dados
		associacaoSkillRepository.save(associacaoSkill);
	}

	public void excluirAssociacaoSkill(Integer associacaoSkillId) {
		// Busca a associação de skill pelo ID
		AssociacaoSkill associacaoSkill = associacaoSkillRepository.findById(associacaoSkillId)
				.orElseThrow(() -> new RuntimeException("Associação de skill não encontrada"));

		// Exclui a associação de skill do banco de dados
		associacaoSkillRepository.delete(associacaoSkill);
	}

	public List<AssociacaoSkill> listarTodasAssociacoesSkill() {
		// Retorna todas as associações de skill no banco de dados
		return associacaoSkillRepository.findAll();
	}

	public AssociacaoSkill buscarAssociacaoSkillPorId(Integer associacaoSkillId) {
		// Busca a associação de skill pelo ID
		return associacaoSkillRepository.findById(associacaoSkillId)
				.orElseThrow(() -> new RuntimeException("Associação de skill não encontrada"));
	}
}