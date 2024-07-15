package com.br.desafionekiapi.domain.exception;

public class AssociacaoNaoEncontradaException extends EntidadeNaoEncontradaException {


	private static final long serialVersionUID = 1L;

	public AssociacaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public AssociacaoNaoEncontradaException(Integer associacaoSkillId) {
		this(String.format("Não existe uma Associação de skill a usuario com código %d", associacaoSkillId));
	}

}
