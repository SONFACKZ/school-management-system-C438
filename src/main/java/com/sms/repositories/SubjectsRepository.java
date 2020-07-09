package com.sms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.models.Subject;

@Repository
public interface SubjectsRepository extends JpaRepository<Subject, Integer>{
	Boolean existsByCode(String subjectCode);
	Optional<Subject> findByCode(String subCode);
}
