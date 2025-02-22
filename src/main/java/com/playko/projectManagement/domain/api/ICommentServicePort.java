package com.playko.projectManagement.domain.api;

import com.playko.projectManagement.domain.model.CommentModel;

public interface ICommentServicePort {
    void addComment(CommentModel commentModel);
}
