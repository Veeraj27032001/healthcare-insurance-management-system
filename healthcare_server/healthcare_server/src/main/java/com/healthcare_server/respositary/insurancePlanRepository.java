package com.healthcare_server.respositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare_server.model.city;
import com.healthcare_server.model.country;
import com.healthcare_server.model.insurancePlan;
import com.healthcare_server.model.state;

@Repository
public interface insurancePlanRepository extends JpaRepository<insurancePlan,Long> {
	List<insurancePlan> findAllByStatusAndInsuranceProvider(boolean status,int id);
	List<insurancePlan> findAllByStatus(boolean status);
	public List<insurancePlan> findAllByInsuranceProvider(int insuanceProvider);
	List<insurancePlan> findAllById(long planId);

} 
