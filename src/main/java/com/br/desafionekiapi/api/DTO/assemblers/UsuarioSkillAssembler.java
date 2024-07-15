package com.br.desafionekiapi.api.DTO.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.br.desafionekiapi.api.DTO.UsuarioSkillDTO;
import com.br.desafionekiapi.domain.entities.AssociacaoSkill;

@Component
public class UsuarioSkillAssembler {

    public UsuarioSkillDTO toDTO(AssociacaoSkill associacaoSkill) {
        UsuarioSkillDTO dto = new UsuarioSkillDTO();
        dto.setId(associacaoSkill.getId());
        dto.setSkillId(associacaoSkill.getSkills().getId());
        dto.setImagem(associacaoSkill.getSkills().getImagem());
        dto.setNome(associacaoSkill.getSkills().getNome());
        dto.setDescricao(associacaoSkill.getSkills().getDescricao());
        dto.setLevel(associacaoSkill.getLevel());
        return dto;
    }

    public List<UsuarioSkillDTO> toCollectionDTO(List<AssociacaoSkill> associacaoSkills) {
        return associacaoSkills.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Page<UsuarioSkillDTO> toPageDTO(Page<AssociacaoSkill> pageAssociacoes) {
        return pageAssociacoes.map(this::toDTO);
    }
}
