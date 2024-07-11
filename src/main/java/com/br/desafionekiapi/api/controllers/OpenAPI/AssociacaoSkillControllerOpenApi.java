package com.br.desafionekiapi.api.controllers.OpenAPI;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.desafionekiapi.api.DTO.AssociacaoSkillRequestDTO;
import com.br.desafionekiapi.core.springdoc.PageableParameter;
import com.br.desafionekiapi.domain.filters.SkillsFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AssociacaoSkill")
public interface AssociacaoSkillControllerOpenApi {

    @Operation(summary = "Busca uma associação de skill por ID")
    public ResponseEntity<?> buscarAssociacaoSkillPorId(@PathVariable Integer associacaoSkillId);

    @Operation(summary = "Lista todas as associações de skills")
    public ResponseEntity<?> listarTodasAssociacoesSkill();

    @PageableParameter
    @Operation(summary = "Lista as associações de skills por ID de usuário com paginação",
			parameters = {
					@Parameter(in = ParameterIn.QUERY, name = "skillNome",
							description = "Nome da skill para filtro da pesquisa",
							example = "Java", schema = @Schema(type = "string")),
					@Parameter(in = ParameterIn.QUERY, name = "level",
							description = "Level da skill para filtro da pesquisa",
							example = "10", schema = @Schema(type = "integer"))
			}
	)
    public ResponseEntity<?> listarAssociacoesSkillPorUsuarioId(@PathVariable Integer usuarioId,
    															@Parameter(hidden = true) SkillsFilter filtro,
                                                                @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Associa uma skill a um usuário")
    public ResponseEntity<?> associarSkillAoUsuario(@RequestBody AssociacaoSkillRequestDTO requestDTO);

    @Operation(summary = "Atualiza uma associação de skill")
    public ResponseEntity<?> atualizarAssociacaoSkill(@PathVariable Integer associacaoSkillId,
                                                      @RequestBody AssociacaoSkillRequestDTO requestDTO);

    @Operation(summary = "Deleta uma associação de skill")
    public ResponseEntity<?> deletarAssociacaoSkill(@PathVariable Integer associacaoSkillId);
}
