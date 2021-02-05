package com.cozydo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.cozydo.dao.ClinicDao;
import com.cozydo.model.BasicResponse;
import com.cozydo.model.place.Clinic;
import com.cozydo.model.place.ClinicDto;

import lombok.NonNull;

@Repository
public class ClinicService implements ClinicDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Object findClinicByClinicIdx(int idx) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();

		String sql = "SELECT c from Clinic c where c.clinicIdx = :clinicidx";
		TypedQuery<Clinic> query = em.createQuery(sql, Clinic.class);
		query.setParameter("clinicidx", idx);
		Clinic clinic = new Clinic();
		try {
			clinic = query.getSingleResult();
			if (clinic.getClinicIdx() == 0) {
				result.status = false;
				result.data = "잘못된 Index정보 입니다.";
			} else {
				result.status = true;
				result.data = "success";
				result.object = clinic;
			}
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {

		}
		return response;
	}

	public Object GetDistanceByLatandLon(String lat, String lon) {
		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();

		double Lat = Double.parseDouble(lat);
		double Lon = Double.parseDouble(lon);

		String imsi = "SELECT c.clinicIdx,c.clinicName,c.clinicAddress,c.tel," + "(6371*acos(cos(radians(" + Lat
				+ "))*cos(radians(c.clinicLat))*cos(radians(c.clinicLon)" + " -radians(" + Lon + "))+sin(radians(" + Lat
				+ "))*sin(radians(c.clinicLat)))) AS distance" + " FROM Clinic c" + " ORDER BY distance";
		String sql = imsi;

		Query query = em.createQuery(sql);
		List<Object[]> tmp = query.getResultList();
		List<ClinicDto> list = new ArrayList<ClinicDto>();
		tmp.stream().forEach(i -> {
			list.add(new ClinicDto((@NonNull int) i[0], (@NonNull String) i[1], (@NonNull String) i[2],
					(@NonNull String) i[3], (@NonNull Double) i[4]));
		});

		if (list.size() == 0) {
			result.status = false;
			result.data = "주변에 진료소가 존재하지 않습니다.";
		} else {
			result.status = true;
			result.object = list;
			result.data = "success";
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
}
