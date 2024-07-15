package com.br.desafionekiapi.api.DTO.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.desafionekiapi.api.DTO.UsuarioResponseDTO;
import com.br.desafionekiapi.domain.entities.Usuario;

@Component
public class UsuarioAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioResponseDTO toDTO(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioResponseDTO.class);
	}

	public List<UsuarioResponseDTO> toCollectionDTO(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }
}
