package com.cozydo.model.place;

import java.util.List;

import com.cozydo.model.review.ReviewResponseDto;

public class PlaceResponseDto {

	private int placeIdx;
	private String placeName; // 상호명
	private String subCategory; // 업종 분류명
	private String gu; // 시군구명
	private String dong; // 법정동명
	private String addressName; // 지번주소
	private String roadAddressName; // 도로명 주소
	private String placeLon; // 경도
	private String placeLat; // 위도
	private String tel;
	private String distance;
	private String rating;
	private int isLike;
	private int notFood;
	private int seatOneApart;
	private int meetProhibition;
	private int deadlineNine;
	private int qrCode;
	private int nomorepeopleNine;
	private int twopeopleOnehour;
	private int showerOnly;
	private int lessthan100;
	private int lessthan50;
	private int familyOnly;
	private int notShower;
	private List<ReviewResponseDto> reviewList;

	
	public PlaceResponseDto(PlaceDto placeDto, String rating) {
		super();
		this.placeIdx = placeDto.getPlaceIdx();
		this.placeName = placeDto.getPlaceName();
		this.subCategory = placeDto.getSubCategory();
		this.gu = placeDto.getGu();
		this.dong = placeDto.getDong();
		this.addressName = placeDto.getAddressName();
		this.roadAddressName = placeDto.getRoadAddressName();
		this.placeLon = placeDto.getPlaceLon();
		this.placeLat = placeDto.getPlaceLat();
		this.tel = placeDto.getTel();
		this.distance = placeDto.getDistance();
		this.rating = rating;
		this.isLike = placeDto.getIsLike();
		this.notFood = placeDto.getNotFood();
		this.seatOneApart = placeDto.getSeatOneApart();
		this.meetProhibition = placeDto.getMeetProhibition();
		this.deadlineNine = placeDto.getDeadlineNine();
		this.qrCode = placeDto.getQrCode();
		this.nomorepeopleNine = placeDto.getNomorepeopleNine();
		this.twopeopleOnehour = placeDto.getTwopeopleOnehour();
		this.showerOnly = placeDto.getShowerOnly();
		this.lessthan100 = placeDto.getLessthan100();
		this.lessthan50 = placeDto.getLessthan50();
		this.familyOnly = placeDto.getFamilyOnly();
		this.notShower = placeDto.getNotShower();
		this.reviewList = placeDto.getReviewList();
	}
	
	public PlaceResponseDto(int placeIdx, String placeName, String subCategory, String gu, String dong, String addressName,
			String roadAddressName, String placeLon, String placeLat, String tel, String distance, int isLike,
			int notFood, int seatOneApart, int meetProhibition, int deadlineNine, int qrCode, int nomorepeopleNine,
			int twopeopleOnehour, int showerOnly, int lessthan100, int lessthan50, int familyOnly, int notShower,
			List<ReviewResponseDto> reviewList) {
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
		this.tel = tel;
		this.distance = distance;
		this.isLike = isLike;
		this.notFood = notFood;
		this.seatOneApart = seatOneApart;
		this.meetProhibition = meetProhibition;
		this.deadlineNine = deadlineNine;
		this.qrCode = qrCode;
		this.nomorepeopleNine = nomorepeopleNine;
		this.twopeopleOnehour = twopeopleOnehour;
		this.showerOnly = showerOnly;
		this.lessthan100 = lessthan100;
		this.lessthan50 = lessthan50;
		this.familyOnly = familyOnly;
		this.notShower = notShower;
		this.reviewList = reviewList;
	}

	public PlaceResponseDto() {
		super();
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getIsLike() {
		return isLike;
	}

	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}

	public int getNotFood() {
		return notFood;
	}

	public void setNotFood(int notFood) {
		this.notFood = notFood;
	}

	public int getSeatOneApart() {
		return seatOneApart;
	}

	public void setSeatOneApart(int seatOneApart) {
		this.seatOneApart = seatOneApart;
	}

	public int getMeetProhibition() {
		return meetProhibition;
	}

	public void setMeetProhibition(int meetProhibition) {
		this.meetProhibition = meetProhibition;
	}

	public int getDeadlineNine() {
		return deadlineNine;
	}

	public void setDeadlineNine(int deadlineNine) {
		this.deadlineNine = deadlineNine;
	}

	public int getQrCode() {
		return qrCode;
	}

	public void setQrCode(int qrCode) {
		this.qrCode = qrCode;
	}

	public int getNomorepeopleNine() {
		return nomorepeopleNine;
	}

	public void setNomorepeopleNine(int nomorepeopleNine) {
		this.nomorepeopleNine = nomorepeopleNine;
	}

	public int getTwopeopleOnehour() {
		return twopeopleOnehour;
	}

	public void setTwopeopleOnehour(int twopeopleOnehour) {
		this.twopeopleOnehour = twopeopleOnehour;
	}

	public int getShowerOnly() {
		return showerOnly;
	}

	public void setShowerOnly(int showerOnly) {
		this.showerOnly = showerOnly;
	}

	public int getLessthan100() {
		return lessthan100;
	}

	public void setLessthan100(int lessthan100) {
		this.lessthan100 = lessthan100;
	}

	public int getLessthan50() {
		return lessthan50;
	}

	public void setLessthan50(int lessthan50) {
		this.lessthan50 = lessthan50;
	}

	public int getFamilyOnly() {
		return familyOnly;
	}

	public void setFamilyOnly(int familyOnly) {
		this.familyOnly = familyOnly;
	}

	public int getNotShower() {
		return notShower;
	}

	public void setNotShower(int notShower) {
		this.notShower = notShower;
	}

	public List<ReviewResponseDto> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<ReviewResponseDto> reviewList) {
		this.reviewList = reviewList;
	}

}
