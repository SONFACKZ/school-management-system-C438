package com.sms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.models.SClass;

@Repository
public interface ClassesRepository extends JpaRepository<SClass, Integer> {
	Optional<SClass> findByCode(String classCode);
	Optional<SClass> findByCodeAndYear_syFirst(String code, String syFirst);
	Boolean existsByCode(String code);
}
