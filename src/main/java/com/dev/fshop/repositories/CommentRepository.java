package com.dev.fshop.repositories;

import com.dev.fshop.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    public Page<Comment> findCommentsByProduct_ProductIdAndParent_CommentId(String productId, String commentId, Pageable pageable);

    @Query("SELECT u FROM Comment u WHERE u.product.productId = :productId and (:commentId is null or u.parent.commentId = :commentId)  and u.status >= :status ORDER BY u.createTime ASC")
    public Page<Comment> findCommentsByProduct_ProductIdAndParent_CommentIdAndStatusGreaterThanEqualAndOrderByCreateTimeAsc(String productId, String commentId, int status, Pageable pageable);

    @Query("SELECT u FROM Comment u WHERE u.commentId = :commentId and u.account.userId = :userId and u.status >= :status ORDER BY u.createTime ASC ")
    public Comment findCommentByCommentIdAndAccount_UserIdAndStatusGreaterThanEqualAndOrderByCreateTimeAsc(String commentId, String userId, int status);
}
