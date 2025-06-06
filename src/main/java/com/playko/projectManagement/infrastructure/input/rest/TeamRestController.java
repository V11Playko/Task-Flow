package com.playko.projectManagement.infrastructure.input.rest;

import com.playko.projectManagement.application.dto.request.team.AddUserToTeamRequestDto;
import com.playko.projectManagement.application.dto.request.team.RemoveUserFromTeamRequestDto;
import com.playko.projectManagement.application.dto.request.team.TeamEmailRequestDto;
import com.playko.projectManagement.application.dto.response.TeamPerformanceReportDto;
import com.playko.projectManagement.application.dto.request.team.TeamRequestDto;
import com.playko.projectManagement.application.handler.ITeamHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
public class TeamRestController {
    private final ITeamHandler teamHandler;

    @Operation(summary = "Add a new Team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Team created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/saveTeam")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<Void> saveTeam(@Valid @RequestBody TeamRequestDto teamRequestDto) {
        teamHandler.saveTeam(teamRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a user to the team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User added to the team", content = @Content),
            @ApiResponse(responseCode = "404", description = "Team or User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PostMapping("/addUserToTeam")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<Void> addUserToTeam(@Valid @RequestBody AddUserToTeamRequestDto addUserToTeamRequestDto) {
        teamHandler.addUserToTeam(addUserToTeamRequestDto.getTeamId(), addUserToTeamRequestDto.getEmailUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Remove a user from the team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User removed from the team", content = @Content),
            @ApiResponse(responseCode = "404", description = "Team or User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @DeleteMapping("/removeUserFromTeam")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public ResponseEntity<Void> removeUserFromTeam(@Valid @RequestBody RemoveUserFromTeamRequestDto removeUserFromTeamRequestDto) {
        teamHandler.removeUserFromTeam(removeUserFromTeamRequestDto.getTeamId(), removeUserFromTeamRequestDto.getEmailUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Generate team performance report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report generated successfully"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @GetMapping("/performance/{teamId}")
    public ResponseEntity<TeamPerformanceReportDto> getTeamPerformance(@PathVariable Long teamId) {
        return ResponseEntity.ok(teamHandler.generatePerformanceReport(teamId));
    }

    @Operation(summary = "Send a message to all team members by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emails sent successfully"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendTeamEmail(@RequestBody TeamEmailRequestDto request) {
        teamHandler.sendEmailToTeam(request);
        return ResponseEntity.ok("Emails sent to team members successfully.");
    }
}
