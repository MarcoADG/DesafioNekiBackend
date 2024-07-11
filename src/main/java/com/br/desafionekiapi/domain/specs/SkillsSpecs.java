package com.br.desafionekiapi.domain.specs;

import com.br.desafionekiapi.domain.entities.AssociacaoSkill;
import com.br.desafionekiapi.domain.filters.SkillsFilter;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;

public class SkillsSpecs {
	
	public static Specification<AssociacaoSkill> usandoFiltro(SkillsFilter filtro) {
		return (root, query, builder) -> {
			if (AssociacaoSkill.class.equals(query.getResultType())) {
				root.fetch("skills");
			}
			
			var predicates = new ArrayList<Predicate>();
			
			if (filtro.getLevel() != null) {
                predicates.add(builder.equal(root.get("level"), filtro.getLevel()));
            }
			
			if (filtro.getSkillNome() != null) {
				predicates.add(builder.equal(root.get("skills").get("nome"), filtro.getSkillNome()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

    public static Specification<AssociacaoSkill> doUsuario(Integer usuarioId) {
        return (root, query, builder) -> builder.equal(root.get("usuario").get("id"), usuarioId);
    }
}
