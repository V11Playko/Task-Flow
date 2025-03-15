package com.playko.projectManagement.domain.api;

import com.playko.projectManagement.application.dto.response.ProjectStatsDto;
import com.playko.projectManagement.domain.model.ProjectModel;

import java.time.LocalDate;

public interface IProjectServicePort {
    void createProject(ProjectModel projectModel);
    void updateProjectDeadline(Long projectId, LocalDate deadline);
    void archiveProject(Long projectId);
    ProjectStatsDto getProjectStats(Long projectId);
    void restrictUserFromProject(Long projectId, String email);
}
