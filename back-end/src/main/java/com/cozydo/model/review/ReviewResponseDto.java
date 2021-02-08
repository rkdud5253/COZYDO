package com.cozydo.model.review;

import java.time.LocalDateTime;

public class ReviewResponseDto {
	
	private long reviewIdx;
	private int placeIdx;
	private int userIdx;
	private String userNickname;
	private int reviewScore;
	private String content; 
	private LocalDateTime  writeTime;
	
	
	public ReviewResponseDto() {
		super();
	}

	public ReviewResponseDto(long reviewIdx, int placeIdx, int userIdx, String userNickname, int reviewScore,
			String content, LocalDateTime writeTime) {
		super();
		this.reviewIdx = reviewIdx;
		this.placeIdx = placeIdx;
		this.userIdx = userIdx;
		this.userNickname = userNickname;
		this.reviewScore = reviewScore;
		this.content = content;
		this.writeTime = writeTime;
	}

	public long getReviewIdx() {
		return reviewIdx;
	}

	public void setReviewIdx(long reviewIdx) {
		this.reviewIdx = reviewIdx;
	}

	public int getPlaceIdx() {
		return placeIdx;
	}

	public void setPlaceIdx(int placeIdx) {
		this.placeIdx = placeIdx;
	}

	public int getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public int getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(LocalDateTime writeTime) {
		this.writeTime = writeTime;
	}
}
