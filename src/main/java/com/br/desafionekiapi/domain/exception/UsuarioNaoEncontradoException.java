package com.br.desafionekiapi.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {


	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(Integer skillId) {
		this(String.format("Não existe um cadastro de skill com código %d", skillId));
	}

}
