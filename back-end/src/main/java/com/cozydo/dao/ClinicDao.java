package com.cozydo.dao;

public interface ClinicDao {
	Object findClinicByClinicIdx(int idx);

	Object GetDistanceByLatandLon(String lat, String lon);
}
