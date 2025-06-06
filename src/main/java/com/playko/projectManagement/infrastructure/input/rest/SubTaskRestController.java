package com.playko.projectManagement.infrastructure.input.rest;

import com.playko.projectManagement.application.dto.request.SubTaskRequestDto;
import com.playko.projectManagement.application.dto.response.SubTaskResponseDto;
import com.playko.projectManagement.application.handler.ISubTaskHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subtasks")
@RequiredArgsConstructor
public class SubTaskRestController {
    private final ISubTaskHandler subTaskHandler;

    @Operation(summary = "Add a subtask to a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subtask added successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PostMapping("/add/{taskId}")
    public ResponseEntity<Void> addSubTask(@PathVariable Long taskId, @RequestBody SubTaskRequestDto request) {
        subTaskHandler.addSubTask(taskId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all subtasks for a specific task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of subtasks retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PreAuthorize("hasAnyRole('MANAGER', 'CONTRIBUTOR', 'USER', 'ADMIN')")
    @GetMapping("/{taskId}")
    public ResponseEntity<List<SubTaskResponseDto>> getSubTasksByTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(subTaskHandler.getSubTasksByTask(taskId));
    }

    @Operation(summary = "Update a subtask")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subtask updated successfully"),
            @ApiResponse(responseCode = "404", description = "Subtask not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PutMapping("/update/{subTaskId}")
    public ResponseEntity<Void> updateSubTask(@PathVariable Long subTaskId, @RequestBody SubTaskRequestDto request) {
        subTaskHandler.updateSubTask(subTaskId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete a subtask")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Subtask deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Subtask not found")
    })
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @DeleteMapping("/delete/{subTaskId}")
    public ResponseEntity<Void> deleteSubTask(@PathVariable Long subTaskId) {
        subTaskHandler.deleteSubTask(subTaskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
