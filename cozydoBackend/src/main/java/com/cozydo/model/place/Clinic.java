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
public class Clinic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clinic_idx")
	private int clinicIdx; //autoincrase

	@NonNull
	@NotNull
	@Column(name = "clinic_name")
	private String clinicName; // 보건소명

	@NonNull
	@NotNull
	@Column(name = "city")
	private String city; //시

	@NonNull
	@NotNull
	private String gu; //구

	@NonNull
	@NotNull
	@Column(name = "clinic_address_name")
	private String clinicAddress; //주소

	@NonNull
	@NotNull
	@Column(name = "week_time")
	private String week; //평일시간

	@NonNull
	@NotNull
	@Column(name = "sat_time")
	private String sat; //토요일시간

	@NonNull
	@NotNull
	@Column(name = "sun_time")
	private String sun; //일요일공휴일시간

	@NonNull
	@NotNull
	private String tel; //전화번호

	@NonNull
	@NotNull
	@Column(name = "clinic_lon")
	private String clinicLon; //경도

	@NonNull
	@NotNull
	@Column(name = "clinic_lat")
	private String clinicLat; // 위도
}
