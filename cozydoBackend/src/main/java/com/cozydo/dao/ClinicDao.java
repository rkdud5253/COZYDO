
package com.cozydo.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cozydo.model.place.Clinic;

public interface ClinicDao extends JpaRepository<Clinic, Long> {
	Clinic findClinicByClinicIdx(int clinicIdx);
}
