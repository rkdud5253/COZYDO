
package com.cozydo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozydo.model.place.Clinic;

public interface ClinicDao extends JpaRepository<Clinic, Long> {
	Clinic findClinicByClinicIdx(int clinicIdx);
	
//	List<Clinic> findClinicOrderByGuDesc(String gu);
	
}
