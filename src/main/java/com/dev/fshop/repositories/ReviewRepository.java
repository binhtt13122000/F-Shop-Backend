package com.dev.fshop.repositories;

import com.dev.fshop.entities.Product;
import com.dev.fshop.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    public List<Review> findReviewsByProductProId(String productId);

}
