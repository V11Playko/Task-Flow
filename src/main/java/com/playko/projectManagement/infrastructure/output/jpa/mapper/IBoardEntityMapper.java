package com.playko.projectManagement.infrastructure.output.jpa.mapper;

import com.playko.projectManagement.domain.model.BoardModel;
import com.playko.projectManagement.infrastructure.output.jpa.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBoardEntityMapper {
    BoardEntity toEntity(BoardModel boardModel);
    @Mapping(target = "project.boards", ignore = true)
    BoardModel toResponse(BoardEntity board);
}
