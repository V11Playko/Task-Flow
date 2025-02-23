package com.playko.projectManagement.infrastructure.input.rest;

import com.playko.projectManagement.application.dto.request.CommentRequestDto;
import com.playko.projectManagement.application.handler.ICommentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentRestController {
    private final ICommentHandler commentHandler;

    @Operation(summary = "Add a comment to a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment added successfully"),
            @ApiResponse(responseCode = "404", description = "Task or User not found")
    })
    @PostMapping
    public ResponseEntity<Void> addComment(@Valid @RequestBody CommentRequestDto request) {
        commentHandler.addComment(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
