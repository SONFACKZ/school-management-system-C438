package com.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.models.SchoolYear;

public interface SchoolYearsRepository extends JpaRepository<SchoolYear, Integer>{
}
