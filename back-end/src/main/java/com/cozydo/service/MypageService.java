package com.cozydo.service;

import java.util.List;

import com.cozydo.model.likes.MyLikesResponseDto;
import com.cozydo.model.review.MyReviewResponseDto;

public interface MypageService {

	List<MyReviewResponseDto> getMyReivewList(int userIdx);

	List<MyLikesResponseDto> getMyLikesList(int userIdx);

}
