package com.br.desafionekiapi.repositories.specs;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import com.br.desafionekiapi.entities.AssociacaoSkill;
import com.br.desafionekiapi.repositories.filters.SkillsFilter;

import jakarta.persistence.criteria.Predicate;

public class SkillsSpecs {
	
	public static Specification<AssociacaoSkill> usandoFiltro(SkillsFilter filtro) {
		return (root, query, builder) -> {
			if (AssociacaoSkill.class.equals(query.getResultType())) {
				root.fetch("skills");
			}
			
			var predicates = new ArrayList<Predicate>();
			
			if (filtro.getSkillId() != null) {
				predicates.add(builder.equal(root.get("skills").get("id"), filtro.getSkillId()));
			}
			
			if (filtro.getSkillNome() != null) {
				predicates.add(builder.equal(root.get("skills").get("nome"), filtro.getSkillNome()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
