package com.br.desafionekiapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.desafionekiapi.domain.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByLogin(String login);
}
