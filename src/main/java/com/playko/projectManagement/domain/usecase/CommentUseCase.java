package com.playko.projectManagement.domain.usecase;

import com.playko.projectManagement.domain.api.ICommentServicePort;
import com.playko.projectManagement.domain.model.CommentModel;
import com.playko.projectManagement.domain.spi.ICommentPersistencePort;

public class CommentUseCase implements ICommentServicePort {
    private final ICommentPersistencePort commentPersistencePort;

    public CommentUseCase(ICommentPersistencePort commentPersistencePort) {
        this.commentPersistencePort = commentPersistencePort;
    }

    @Override
    public void addComment(CommentModel commentModel) {
        commentPersistencePort.addComment(commentModel);
    }
}
