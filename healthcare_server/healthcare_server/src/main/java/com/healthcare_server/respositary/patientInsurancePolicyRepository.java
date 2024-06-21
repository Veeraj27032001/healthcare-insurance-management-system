package com.healthcare_server.respositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare_server.model.adminFeatures;
import com.healthcare_server.model.insuranceproviderFeatures;
import com.healthcare_server.model.patientInsurancePolicy;


@Repository
public interface patientInsurancePolicyRepository extends JpaRepository<patientInsurancePolicy,Integer> {
 
	
	 Optional<patientInsurancePolicy> findAllByPolicyNo(String policyNo);
	 List<patientInsurancePolicy> findAllByPatientId(int patientId);
}


