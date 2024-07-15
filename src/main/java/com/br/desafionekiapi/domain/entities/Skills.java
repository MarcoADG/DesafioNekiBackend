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
@Table(name = "skills")
public class Skills {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@NotNull
    @Size(max = 2000)
	@Column(name = "imagem", nullable = false)
	private String imagem;
	
	@NotNull
    @Size(max = 100)
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	@NotNull
    @Size(max = 100)
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@OneToMany(mappedBy = "skills")
	private List<AssociacaoSkill> associacaoSkill;

	
}
