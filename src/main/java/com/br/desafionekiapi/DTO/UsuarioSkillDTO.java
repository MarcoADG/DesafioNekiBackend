package com.br.desafionekiapi.DTO;

public class UsuarioSkillDTO {

	private Integer id;
	private Integer skillId;
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

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
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

	public UsuarioSkillDTO(Integer id, Integer skillId, String imagem, String nome, String descricao, Integer level) {
		super();
		this.id = id;
		this.skillId = skillId;
		this.imagem = imagem;
		this.nome = nome;
		this.descricao = descricao;
		this.level = level;
	}

	public UsuarioSkillDTO() {

	}

}
