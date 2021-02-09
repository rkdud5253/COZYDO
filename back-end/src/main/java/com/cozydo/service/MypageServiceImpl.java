package com.cozydo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozydo.mapper.MypageMapper;
import com.cozydo.model.likes.MyLikesResponseDto;
import com.cozydo.model.review.MyReviewResponseDto;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	MypageMapper mypageMapper;
	
	@Override
	public List<MyReviewResponseDto> getMyReivewList(int userIdx) {
		return mypageMapper.getMyReivewList(userIdx);
	}

	@Override
	public List<MyLikesResponseDto> getMyLikesList(int userIdx) {
		return mypageMapper.getMyLikesList(userIdx);
	}

}
