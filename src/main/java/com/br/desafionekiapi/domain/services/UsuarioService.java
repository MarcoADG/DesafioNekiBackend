package com.br.desafionekiapi.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.desafionekiapi.domain.exception.NegocioException;
import com.br.desafionekiapi.domain.entities.Usuario;
import com.br.desafionekiapi.domain.exception.UsuarioNaoEncontradoException;
import com.br.desafionekiapi.domain.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder encoder;

	public List<Usuario> buscarTodosUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario buscarPorId(Integer usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}

	@Transactional
	public Usuario cadastrarUsuario(Usuario usuario) {
		// Verifica se o usuário existe
		Usuario usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin());
		if (usuarioExistente != null) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o login %s", usuario.getLogin()));
		}

		// Salva o usuário na base de dados
		usuario.setLogin(usuario.getLogin());
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

	@Transactional
	public Usuario atualizarUsuario(Integer usuarioId, String novoLogin, String senha) {
		// Busca o usuário pelo ID fornecido
		Usuario usuario = buscarPorId(usuarioId);

		// Atualiza os campos do usuário, se os novos valores não forem nulos
		if (!encoder.matches(senha, usuario.getSenha())) {
			throw new NegocioException("Senha informada não coincide com a senha do usuário.");
		}
		
		usuario.setLogin(novoLogin);

		// Salva a atualização no banco de dados e retorna o usuário atualizado
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void alterarSenha(Integer usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarPorId(usuarioId);
		
		if (!encoder.matches(senhaAtual, usuario.getSenha())) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}
		
		usuario.setSenha(encoder.encode(novaSenha));
	}

	public void deletarUsuario(Integer usuarioId) {
		try {
			usuarioRepository.deleteById(usuarioId);
			usuarioRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(usuarioId);
		}
	}

}