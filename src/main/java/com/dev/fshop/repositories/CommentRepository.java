package com.dev.fshop.repositories;

import com.dev.fshop.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {



//    @Query("update Comment  u set u.content = :content where u.commentId = :commentId")
//    public Comment updateContentByCommentId(String content, String commentId);
//
//    public List<Comment> findCommentEntitiesByProductEntity(String name);

    //  public List<CommentEntity> findCommentEntitiesByByName(String name);
//    @Transactional
//    public CommentEntity insertCommentWithEntityManager(CommentEntity commentEntity);


}
