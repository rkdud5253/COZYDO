package com.cozydo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozydo.model.likes.Likes;

public interface LikesDao extends JpaRepository<Likes, Long>  {

	Optional<Likes> findByUserIdxAndPlaceIdx(int userIdx, int placeIdx);

	void delete(Likes likes);

	List<Likes> findByUserIdx(int userIdx);

}
