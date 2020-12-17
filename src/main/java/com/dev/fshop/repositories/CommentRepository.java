package com.dev.fshop.repositories;

import com.dev.fshop.embedded.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    public List<Comment> findCommentByName(String name);
    public boolean deleteComment( String commentId);
    public Comment updateCommentContent(Comment comment, String commentId);
    public List<Comment> findCommentByProduct(String proId);
    @Transactional
    public Comment insertCommentWithEntityManager(Comment comment);
}
