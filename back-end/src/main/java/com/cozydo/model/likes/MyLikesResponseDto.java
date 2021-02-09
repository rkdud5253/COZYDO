package com.cozydo.model.likes;

public class MyLikesResponseDto {

	private int userIdx;
	private int placeIdx;
	private String placeName;
	
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
	
	public MyLikesResponseDto(int userIdx, int placeIdx, String placeName) {
		super();
		this.userIdx = userIdx;
		this.placeIdx = placeIdx;
		this.placeName = placeName;
	}
	
}
