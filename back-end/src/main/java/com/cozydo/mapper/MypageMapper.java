package com.cozydo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cozydo.model.likes.MyLikesResponseDto;
import com.cozydo.model.review.MyReviewResponseDto;

@Mapper
public interface MypageMapper {

	List<MyReviewResponseDto> getMyReivewList(int userIdx);

	List<MyLikesResponseDto> getMyLikesList(int userIdx);

}
