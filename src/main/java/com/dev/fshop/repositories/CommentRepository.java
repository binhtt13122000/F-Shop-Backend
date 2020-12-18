package com.dev.fshop.repositories;

import com.dev.fshop.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {



    @Query("update CommentEntity  u set u.content = :content where u.commentId = :commentId")
    public CommentEntity updateContentByCommentId(String content, String commentId);

    public List<CommentEntity> findCommentEntitiesByProductEntity(String name);

    //  public List<CommentEntity> findCommentEntitiesByByName(String name);
//    @Transactional
//    public CommentEntity insertCommentWithEntityManager(CommentEntity commentEntity);


}
