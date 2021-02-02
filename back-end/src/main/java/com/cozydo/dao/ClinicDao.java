package com.cozydo.dao;

import java.util.List;

import com.cozydo.model.place.Clinic;
import com.cozydo.model.place.ClinicDto;

public interface ClinicDao {
	Clinic findClinicByClinicIdx(int idx);
	List<ClinicDto> GetDistanceByLatandLon(String lat, String lon);
}
