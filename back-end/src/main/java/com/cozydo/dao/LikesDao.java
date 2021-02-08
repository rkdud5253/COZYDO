package com.cozydo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozydo.model.likes.Likes;

public interface LikesDao extends JpaRepository<Likes, Long>  {

	Optional<Likes> findByUserIdxAndPlaceIdx(int userIdx, int placeIdx);

	void delete(Likes likes);

}
