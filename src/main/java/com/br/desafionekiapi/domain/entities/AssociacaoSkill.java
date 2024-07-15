package com.br.desafionekiapi.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "associacao_skills")
public class AssociacaoSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "level_skill", nullable = false)
	private Integer level;

	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable= false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "skills_id", referencedColumnName = "id", nullable= false)
	private Skills skills;

	
}
