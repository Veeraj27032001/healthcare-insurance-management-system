package com.healthcare_server.respositary;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare_server.model.adminFeatures;
import com.healthcare_server.model.insuranceRenewal;
import com.healthcare_server.model.insuranceproviderFeatures;
import com.healthcare_server.model.patientInsurancePolicy;


@Repository
public interface insuranceRenewalRepository extends JpaRepository<insuranceRenewal,Integer> {
 
	
	 List<insuranceRenewal> findAllByPaymentDate(Date paymentDate);
	 
	 List<insuranceRenewal> findAllByPatientPolicyNoAndRenewalShowAndPaymentStatus(String patientPolicyNo,boolean RenewalShow,boolean PaymentStatus );
}


