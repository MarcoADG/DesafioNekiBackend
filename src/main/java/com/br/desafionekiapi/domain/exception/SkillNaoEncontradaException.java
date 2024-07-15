package com.br.desafionekiapi.domain.exception;

public class SkillNaoEncontradaException extends EntidadeNaoEncontradaException {


	private static final long serialVersionUID = 1L;

	public SkillNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public SkillNaoEncontradaException(Integer associacaoSkillId) {
		this(String.format("Não existe uma skill com código %d", associacaoSkillId));
	}

}
