package com.playko.projectManagement.infrastructure.input.rest;

import com.playko.projectManagement.application.dto.request.ProjectRequestDto;
import com.playko.projectManagement.application.dto.request.TaskRequestDto;
import com.playko.projectManagement.application.handler.ITaskHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskRestController {
    private final ITaskHandler taskHandler;

    @Operation(summary = "Save a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "task created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/saveTask")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> saveTask(@Valid @RequestBody TaskRequestDto taskRequestDto) {
        taskHandler.saveTask(taskRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
