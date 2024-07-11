package com.br.desafionekiapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.desafionekiapi.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByLogin(String login);
}
