package com.br.desafionekiapi.DTO;

public class AssociacaoSkillRequestDTO {

	private Integer usuarioId;
	private Integer skillId;
	private Integer level;

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public AssociacaoSkillRequestDTO(Integer usuarioId, Integer skillId, Integer level) {
		super();
		this.usuarioId = usuarioId;
		this.skillId = skillId;
		this.level = level;
	}

	public AssociacaoSkillRequestDTO() {

	}
}
