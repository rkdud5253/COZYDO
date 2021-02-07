package com.cozydo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozydo.model.review.Review;

public interface ReviewDao extends JpaRepository<Review, Long> {

	List<Review> findByUserIdx(int userIdx);

	Optional<Review> findByReviewIdx(Long reviewIdx);

}
