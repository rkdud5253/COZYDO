package com.cozydo.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cozydo.dao.ReviewDao;
import com.cozydo.model.review.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewDao reviewDao;
	
    /** 리뷰 - 등록 */
    @Transactional
    public Long save(Review review) {
 
        return reviewDao.save(review).getReviewIdx();
    }
 
    /** 리뷰 - 삭제 */
    @Transactional
    public void delete(Long reviewIdx) {
 
        Review review = reviewDao.findByReviewIdx(reviewIdx)
                                     .orElseThrow(() -> new IllegalAccessError("[reviewIdx=" + reviewIdx + "] 해당 게시글이 존재하지 않습니다."));
 
        reviewDao.delete(review);
    }
}
