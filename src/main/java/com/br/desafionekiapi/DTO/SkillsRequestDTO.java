package com.br.desafionekiapi.DTO;

public class SkillsRequestDTO {

	private String imagem;
	private String nome;
	private String descricao;

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SkillsRequestDTO(String imagem, String nome, String descricao) {
		super();
		this.imagem = imagem;
		this.nome = nome;
		this.descricao = descricao;
	}

	public SkillsRequestDTO() {

	}

}
