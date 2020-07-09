package com.sms.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.models.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long>{
	Optional<Student> findByRegNumber(String regNum);
	List<Student> findAllByFee_Amount(BigDecimal amount);
	Optional<Student> findByRegNumberAndStatus(String studentRegNum, short ordinal);
}
