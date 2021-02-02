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

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor

public class ClinicDto {
	@NonNull
	private int clinicIdx; // 보건소명
	@NonNull
	private String clinicName; // 보건소명
	@NonNull
	private String clinicAddress; // 주소
	@NonNull
	private String tel; // 전화번호
	@NonNull
	private Double distance; // 거리
}
