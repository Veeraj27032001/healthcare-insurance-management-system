package com.healthcare_server.respositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare_server.model.loginDetails;

@Repository
public interface loginRepository extends JpaRepository<loginDetails,Integer> {
 Optional<loginDetails> findByEmailAndPassword(String email, String Password);
 void deleteById(int id);
 Optional<loginDetails> findByEmail(String email);
}

