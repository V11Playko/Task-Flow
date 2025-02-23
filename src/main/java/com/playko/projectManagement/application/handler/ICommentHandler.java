package com.playko.projectManagement.application.handler;

import com.playko.projectManagement.application.dto.request.CommentRequestDto;

public interface ICommentHandler {
    void addComment(CommentRequestDto commentRequestDto);
}
