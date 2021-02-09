package com.cozydo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cozydo.dao.LikesDao;
import com.cozydo.model.likes.Likes;

@Service
public class LikesServiceImpl implements LikesService {

	@Autowired
	LikesDao likesDao;

	/** 리뷰 - 등록 */
	@Transactional
	public Long save(Likes likes) {

		return (long)likesDao.save(likes).getPlaceIdx();
	}

	/** 리뷰 - 삭제 */
	@Transactional
	public void delete(int userIdx, int placeIdx) {
		Likes likes = likesDao.findByUserIdxAndPlaceIdx(userIdx, placeIdx)
				.orElseThrow(() -> new IllegalAccessError("[placeIdx=" + placeIdx + "] 해당 게시글이 존재하지 않습니다."));

		likesDao.delete(likes);
	}

}
