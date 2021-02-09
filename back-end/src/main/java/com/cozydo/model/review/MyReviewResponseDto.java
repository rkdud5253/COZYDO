package com.cozydo.model.review;

public class MyReviewResponseDto {

	private int userIdx;
	private int placeIdx;
	private String placeName;
	private String content;
	
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public int getPlaceIdx() {
		return placeIdx;
	}
	public void setPlaceIdx(int placeIdx) {
		this.placeIdx = placeIdx;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public MyReviewResponseDto(int userIdx, int placeIdx, String placeName, String content) {
		super();
		this.userIdx = userIdx;
		this.placeIdx = placeIdx;
		this.placeName = placeName;
		this.content = content;
	}
	
}
