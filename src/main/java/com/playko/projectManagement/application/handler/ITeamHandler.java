package com.playko.projectManagement.application.handler;

import com.playko.projectManagement.application.dto.request.team.TeamEmailRequestDto;
import com.playko.projectManagement.application.dto.response.TeamPerformanceReportDto;
import com.playko.projectManagement.application.dto.request.team.TeamRequestDto;

public interface ITeamHandler {
    void saveTeam(TeamRequestDto teamRequestDto);
    void addUserToTeam(Long teamId, String emailUser);
    void removeUserFromTeam(Long teamId, String emailUser);
    TeamPerformanceReportDto generatePerformanceReport(Long teamId);
    void sendEmailToTeam(TeamEmailRequestDto emailRequest);
}
