package com.healthcare_server.respositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare_server.model.adminFeatures;


@Repository
public interface adminFeaturesRepository extends JpaRepository<adminFeatures,Integer> {
 
	
	
}


