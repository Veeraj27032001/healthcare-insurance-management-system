package com.healthcare_server.respositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.healthcare_server.model.banners;
import com.healthcare_server.model.ndc;

@Repository
public interface ndcRepository extends JpaRepository<ndc,Long> {
Optional<ndc> findByCode(String code);
}
