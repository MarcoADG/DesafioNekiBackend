package com.br.desafionekiapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.desafionekiapi.entities.AssociacaoSkill;
import com.br.desafionekiapi.repositories.filters.SkillsFilter;

public interface AssociacaoSkillRepository extends JpaRepository<AssociacaoSkill, Integer>, JpaSpecificationExecutor<AssociacaoSkill> {

	//@Query("SELECT a FROM AssociacaoSkill a WHERE a.usuario.id = :usuarioId")
	Page<AssociacaoSkill> findByUsuarioId(Integer usuarioId, Pageable pageable);
}
