package com.cozydo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cozydo.dao.ClinicDao;
import com.cozydo.model.place.Clinic;
import com.cozydo.model.place.ClinicDto;

import lombok.NonNull;

@Repository
public class ClinicDaompl implements ClinicDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Clinic findClinicByClinicIdx(int idx) {
		String sql = "SELECT c from Clinic c where c.clinicIdx = :clinicidx";
		TypedQuery<Clinic> query = em.createQuery(sql, Clinic.class);
		query.setParameter("clinicidx", idx);
		Clinic clinic = new Clinic();
		try {
			clinic = query.getSingleResult();
		} catch (Exception e) {

		}
		return clinic;
	}

	public List<ClinicDto> GetDistanceByLatandLon(String lat, String lon) {
		double Lat = Double.parseDouble(lat);
		double Lon = Double.parseDouble(lon);
		
		String imsi = "SELECT c.clinicIdx,c.clinicName,c.clinicAddress,c.tel,"
				+ "(6371*acos(cos(radians("+Lat+"))*cos(radians(c.clinicLat))*cos(radians(c.clinicLon)"
				+ " -radians("+Lon+"))+sin(radians("+Lat+"))*sin(radians(c.clinicLat)))) AS distance"
				+ " FROM Clinic c" + " ORDER BY distance";
		String sql = imsi;

		Query query = em.createQuery(sql);
		List<Object[]> tmp = query.getResultList();
		List<ClinicDto> list = new ArrayList<ClinicDto>();
		tmp.stream().forEach(i -> {
			list.add(new ClinicDto((@NonNull int) i[0], (@NonNull String) i[1], (@NonNull String) i[2],
					(@NonNull String) i[3], (@NonNull Double) i[4]));
		});
		return list;
	}
}
