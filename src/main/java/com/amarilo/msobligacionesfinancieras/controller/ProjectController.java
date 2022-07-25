package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.ProjectSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.response.IProjectConsolidator;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.ProjectDto;
import com.amarilo.msobligacionesfinancieras.domain.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/financial-liabilities/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Permite obtener el listado de proyectos por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de proyectos por filtros",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponseProject.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("search")
    public ResponseEntity<PageResponseDto<ProjectDto>> findAllProjectsBySearchCriteria(@RequestBody PageRequestDto<ProjectSearchCriteria> pageRequestDto) {
        return ResponseEntity.ok(projectService.findAllProjectsBySearchCriteria(pageRequestDto));
    }

    @Operation(summary = "Permite obtener un proyecto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el proyecto por id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProjectDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @Operation(summary = "Permite guardar o actualizar una lista de proyectos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado la lista de proyectos exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("save-all")
    public ResponseEntity saveProjects(@RequestBody List<@Valid ProjectDto> projectList) {
        projectService.saveProjects(projectList);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Permite obtener el listado de consolidadores de proyectos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de consolidadores de proyectos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = IProjectConsolidator.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("find-consolidators")
    public ResponseEntity<List<IProjectConsolidator>> findAllDistinctByConsolidatorName() {
        return ResponseEntity.ok(projectService.findAllDistinctByConsolidatorName());
    }

    @Operation(summary = "Permite obtener el listado de etapas de un proyecto por el código del consolidador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene listado de etapas de un proyecto por el código del consolidador",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProjectDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("consolidator/{consolidatorName}")
    public ResponseEntity<List<ProjectDto>> findAllByConsolidatorCode(@PathVariable("consolidatorName") String consolidatorName) {
        return ResponseEntity.ok(projectService.findAllByConsolidatorCode(consolidatorName));
    }
}

class PageResponseProject extends PageResponseDto<ProjectDto> {
}
