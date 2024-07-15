package com.br.desafionekiapi.domain.exception;

public class UsuarioJaExisteException extends EntidadeNaoEncontradaException {


	private static final long serialVersionUID = 1L;

	public UsuarioJaExisteException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioJaExisteException(Integer skillId) {
		this(String.format("Não existe um Usuario com código %d", skillId));
	}

}
