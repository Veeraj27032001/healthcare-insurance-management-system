package com.healthcare_server.respositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare_server.model.insuranceProviderPaymentDetail;

@Repository
public interface insuranceProviderPaymentDetailRepository extends JpaRepository<insuranceProviderPaymentDetail,Long> {
	
}
