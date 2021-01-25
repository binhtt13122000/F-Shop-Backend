package com.dev.fshop.repositories;

import com.dev.fshop.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    public Page<Comment> findCommentsByProduct_ProductId(String productId, Pageable pageable);

    public Page<Comment> findCommentsByProduct_ProductIdAndAccount_UserIdAndStatusGreaterThanEqual(String productId, String userId, int status, Pageable pageable);

    public Comment findCommentByCommentIdAndAccount_UserIdAndStatusGreaterThanEqual(String commentId, String userId, int status);
}
