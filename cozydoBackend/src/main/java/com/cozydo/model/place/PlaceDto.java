package com.cozydo.model.place;

public class PlaceDto {

	private int placeIdx;
	private String placeName; // 상호명
	private String subCategory; // 업종 분류명
	private String gu; // 시군구명
	private String dong; // 법정동명
	private String addressName; // 지번주소
	private String roadAddressName; // 도로명 주소
	private String placeLon; // 경도
	private String placeLat; // 위도
	private String distance;

	
	public PlaceDto() {
		super();
	}

	public PlaceDto(int placeIdx, String placeName, String subCategory, String gu, String dong, String addressName,
			String roadAddressName, String placeLon, String placeLat, String distance) {
		super();
		this.placeIdx = placeIdx;
		this.placeName = placeName;
		this.subCategory = subCategory;
		this.gu = gu;
		this.dong = dong;
		this.addressName = addressName;
		this.roadAddressName = roadAddressName;
		this.placeLon = placeLon;
		this.placeLat = placeLat;
		this.distance = distance;
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

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getRoadAddressName() {
		return roadAddressName;
	}

	public void setRoadAddressName(String roadAddressName) {
		this.roadAddressName = roadAddressName;
	}

	public String getPlaceLon() {
		return placeLon;
	}

	public void setPlaceLon(String placeLon) {
		this.placeLon = placeLon;
	}

	public String getPlaceLat() {
		return placeLat;
	}

	public void setPlaceLat(String placeLat) {
		this.placeLat = placeLat;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

}
