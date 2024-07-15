package com.br.desafionekiapi.domain.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@NotNull
    @Size(max = 50)
	@Column(name = "login", nullable = false, unique = true)
	private String login;

	@NotNull
    @Size(max = 255)
	@Column(name = "senha", nullable = false)
	private String senha;

	@OneToMany(mappedBy = "usuario")
	private List<AssociacaoSkill> associacaoSkill;

}
