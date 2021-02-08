package com.cozydo.service;

import com.cozydo.model.review.Review;

public interface ReviewService {

	Long save(Review review);

	void delete(Long reviewIdx);

}
