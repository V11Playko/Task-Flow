package com.playko.projectManagement.domain.spi;

import com.playko.projectManagement.domain.model.CommentModel;

public interface ICommentPersistencePort {
    void addComment(CommentModel commentModel);

}
