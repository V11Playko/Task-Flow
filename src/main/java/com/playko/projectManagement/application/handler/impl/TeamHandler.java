package com.playko.projectManagement.application.handler.impl;

import com.playko.projectManagement.application.dto.request.team.TeamEmailRequestDto;
import com.playko.projectManagement.application.dto.response.TeamPerformanceReportDto;
import com.playko.projectManagement.application.dto.request.team.TeamRequestDto;
import com.playko.projectManagement.application.handler.ITeamHandler;
import com.playko.projectManagement.application.mapper.request.ITeamRequestMapper;
import com.playko.projectManagement.domain.api.ITeamServicePort;
import com.playko.projectManagement.domain.model.TeamModel;
import com.playko.projectManagement.domain.model.UserModel;
import com.playko.projectManagement.infrastructure.exception.UserNotFoundException;
import com.playko.projectManagement.infrastructure.output.jpa.entity.UserEntity;
import com.playko.projectManagement.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.playko.projectManagement.infrastructure.output.jpa.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamHandler implements ITeamHandler {
    private final ITeamServicePort teamServicePort;
    private final ITeamRequestMapper teamRequestMapper;
    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;
    @Override
    public void saveTeam(TeamRequestDto teamRequestDto) {
        TeamModel teamModel = teamRequestMapper.toTeamRequest(teamRequestDto);

        List<UserEntity> users = mapUsers(teamRequestDto.getUserEmails());

        List<UserModel> userModels = userEntityMapper.toUserModelList(users);
        teamModel.setUsers(userModels);

        teamServicePort.saveTeam(teamModel);
    }

    @Override
    public void addUserToTeam(Long teamId, String emailUser) {
        teamServicePort.addUserToTeam(teamId, emailUser);
    }

    @Override
    public void removeUserFromTeam(Long teamId, String emailUser) {
        teamServicePort.removeUserFromTeam(teamId, emailUser);
    }

    @Override
    public TeamPerformanceReportDto generatePerformanceReport(Long teamId) {
        return teamServicePort.generatePerformanceReport(teamId);
    }

    @Override
    public void sendEmailToTeam(TeamEmailRequestDto emailRequest) {
        teamServicePort.sendEmailToTeam(emailRequest);
    }

    private List<UserEntity> mapUsers(List<String> userEmails) {
        List<UserEntity> users = new ArrayList<>();
        for (String email : userEmails) {
            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(UserNotFoundException::new);
            users.add(user);
        }
        return users;
    }
}
