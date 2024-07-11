package com.br.desafionekiapi.DTO;

public class SkillResponseDTO {

	private Integer id;

	private String imagem;

	private String nome;

	private String descricao;

	private Integer level;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public SkillResponseDTO(Integer id, String imagem, String nome, String descricao, Integer level) {
		super();
		this.id = id;
		this.imagem = imagem;
		this.nome = nome;
		this.descricao = descricao;
		this.level = level;
	}

	public SkillResponseDTO() {
	}

}
