package com.cozydo.model.place;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Place {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "place_idx")
	private int placeIdx;

	@NonNull
	@NotNull
	@Column(name = "place_name")
	private String placeName; // 상호명
	
	@NonNull
	@NotNull
	@Column(name = "sub_category")
	private String subCategory; // 업종 분류명
	
	@NonNull
	@NotNull
	@Column(name = "gu")
	private String gu; // 시군구명
	
	@NonNull
	@NotNull
	@Column(name = "dong")
	private String dong; // 법정동명
	
	@NonNull
	@NotNull
	@Column(name = "address_name")
	private String addressName; // 지번주소
	
	@NonNull
	@NotNull
	@Column(name = "road_address_name")
	private String roadAddressName; // 도로명 주소
	
	@NonNull
	@NotNull
	@Column(name = "place_lon")
	private String placeLon; // 경도
	
	@NonNull
	@NotNull
	@Column(name = "place_lat")
	private String placeLat; // 위도
}
