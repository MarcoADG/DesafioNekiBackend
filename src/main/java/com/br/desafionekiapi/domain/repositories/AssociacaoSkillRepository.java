package com.br.desafionekiapi.domain.repositories;

import com.br.desafionekiapi.domain.entities.AssociacaoSkill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssociacaoSkillRepository extends JpaRepository<AssociacaoSkill, Integer>, JpaSpecificationExecutor<AssociacaoSkill> {
	
    Page<AssociacaoSkill> findByUsuarioId(Integer usuarioId, Pageable pageable);
}
