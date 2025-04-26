package com.playko.projectManagement.infrastructure.output.jpa.mapper;

import com.playko.projectManagement.application.dto.response.BoardResponseDto;
import com.playko.projectManagement.domain.model.BoardColumnModel;
import com.playko.projectManagement.domain.model.BoardModel;
import com.playko.projectManagement.domain.model.ProjectModel;
import com.playko.projectManagement.infrastructure.output.jpa.entity.BoardColumnEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.BoardEntity;
import com.playko.projectManagement.infrastructure.output.jpa.entity.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBoardEntityMapper {

    BoardEntity toEntity(BoardModel boardModel);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "columns", source = "columns") // Asegúrate de tener el mapper para esto
    @Mapping(target = "project", source = "project")
    BoardModel toResponse(BoardEntity board);

    @Mapping(target = "board", ignore = true)
    BoardColumnModel toModel(BoardColumnEntity column);

    @Mapping(target = "boards", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "restrictedUsers", ignore = true)
    ProjectModel toModel(ProjectEntity project);

    List<BoardColumnModel> toColumnModelList(List<BoardColumnEntity> columns);

}
