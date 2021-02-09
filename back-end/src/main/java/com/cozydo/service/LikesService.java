package com.cozydo.service;

import java.util.List;

import com.cozydo.model.likes.Likes;

public interface LikesService {

	Long save(Likes likes);

	void delete(int userIdx, int placeIdx);
}
