package com.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.models.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{
}
