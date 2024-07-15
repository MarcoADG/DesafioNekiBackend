package com.br.desafionekiapi.api.DTO.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.desafionekiapi.api.DTO.SkillResponseDTO;
import com.br.desafionekiapi.domain.entities.Skills;

@Component
public class SkillAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public SkillResponseDTO toDTO(Skills skill) {
		return modelMapper.map(skill, SkillResponseDTO.class);
	}
	
	public List<SkillResponseDTO> toCollectionDTO(List<Skills> skills){
		return skills.stream()
				.map(skill -> modelMapper.map(skill, SkillResponseDTO.class))
				.collect(Collectors.toList());
	}
}
