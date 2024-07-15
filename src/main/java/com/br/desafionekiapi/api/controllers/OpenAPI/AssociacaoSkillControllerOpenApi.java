package com.br.desafionekiapi.api.controllers.OpenAPI;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.desafionekiapi.api.DTO.AssociacaoSkillRequestDTO;
import com.br.desafionekiapi.api.DTO.UsuarioSkillDTO;
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
    public UsuarioSkillDTO buscarAssociacaoSkillPorId(@PathVariable Integer associacaoSkillId);

    @Operation(summary = "Lista todas as associações de skills")
    public List<UsuarioSkillDTO> listarTodasAssociacoesSkill();

    @PageableParameter
    @Operation(summary = "Lista as associações de skills por ID de usuário com paginação",
			parameters = {
					@Parameter(in = ParameterIn.QUERY, name = "skillNome",
							description = "Nome da skill para filtro da pesquisa",
							example = "Java", schema = @Schema(ref = "Problema")),
					@Parameter(in = ParameterIn.QUERY, name = "level",
							description = "Level da skill para filtro da pesquisa",
							example = "10", schema = @Schema(ref = "Problema"))
			}
	)
    public Page<UsuarioSkillDTO> listarAssociacoesSkillPorUsuarioId(@PathVariable Integer usuarioId,
    															@Parameter(hidden = true) SkillsFilter filtro,
                                                                @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Associa uma skill a um usuário")
    public String associarSkillAoUsuario(@RequestBody AssociacaoSkillRequestDTO requestDTO);

    @Operation(summary = "Atualiza uma associação de skill")
    public String atualizarAssociacaoSkill(@PathVariable Integer associacaoSkillId,
                                                      @RequestBody AssociacaoSkillRequestDTO requestDTO);

    @Operation(summary = "Deleta uma associação de skill")
    public ResponseEntity<Void> deletarAssociacaoSkill(@PathVariable Integer associacaoSkillId);
}
