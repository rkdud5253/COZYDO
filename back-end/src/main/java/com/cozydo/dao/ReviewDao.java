package com.cozydo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozydo.model.place.Review;

@Repository
public interface ReviewDao extends JpaRepository<Review, String> {
	List<Review> getReviewByplaceIdx(String placeIdx);
}