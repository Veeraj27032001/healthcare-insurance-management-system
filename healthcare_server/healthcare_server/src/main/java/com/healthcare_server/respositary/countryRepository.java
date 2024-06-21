package com.healthcare_server.respositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare_server.model.country;

@Repository
public interface countryRepository extends JpaRepository<country,Long> {

}
