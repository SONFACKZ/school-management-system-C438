package com.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.models.StudentSubject;

@Repository
public interface StudentMarksRepository extends JpaRepository<StudentSubject, Long>{
}
