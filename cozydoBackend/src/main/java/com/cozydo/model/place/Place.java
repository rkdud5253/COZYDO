package com.cozydo.model.place;

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
	private int place_idx;

	@NonNull
	@NotNull
	private String place_name; // 상호명
	
	@NonNull
	@NotNull
	private String sub_category; // 업종 분류명
	
	@NonNull
	@NotNull
	private String gu; // 시군구명
	
	@NonNull
	@NotNull
	private String dong; // 법정동명
	
	@NonNull
	@NotNull
	private String address_name; // 지번주소
	
	@NonNull
	@NotNull
	private String road_address_name; // 도로명 주소
	
	@NonNull
	@NotNull
	private String place_lon; // 경도
	
	@NonNull
	@NotNull
	private String place_lat; // 위도
}
