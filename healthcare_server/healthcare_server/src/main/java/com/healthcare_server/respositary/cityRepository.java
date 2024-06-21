package com.healthcare_server.respositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare_server.model.city;
import com.healthcare_server.model.country;
import com.healthcare_server.model.state;

@Repository
public interface cityRepository extends JpaRepository<city,Long> {
	 List<city> findAllByStateId(int id) ;
}
