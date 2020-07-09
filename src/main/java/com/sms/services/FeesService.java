package com.sms.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.constants.StudentStatus;
import com.sms.dtos.StudentFeePaymentDTO;
import com.sms.exceptions.InvalidProcessingState;
import com.sms.exceptions.ResourceNotFound;
import com.sms.models.Fees;
import com.sms.models.Student;
import com.sms.repositories.FeesRepository;
import com.sms.repositories.StudentsRepository;

@Service
public class FeesService {
	private static final Logger lg = LoggerFactory.getLogger(FeesService.class);
	private static final BigDecimal TOTAL_SCHOOL_FEE = BigDecimal.valueOf(45000);
	
	private FeesRepository feesRepo;
	private StudentsRepository studentsRepo;
	
	@Autowired
	public FeesService (FeesRepository feesRepo, StudentsRepository studentsRepo) {
		this.feesRepo = feesRepo;
		this.studentsRepo = studentsRepo;
	}

	public void payFor(String regNum) {
		Student student = findStudent(regNum);
		
		Fees fee = new Fees();
		fee.setAmount(TOTAL_SCHOOL_FEE);
		fee.setPaymentDate(LocalDate.now());
		fee= feesRepo.save(fee);
		
		student.setFee(fee);
		student.setStatus((short)StudentStatus.CURRENT.ordinal());
		studentsRepo.save(student);
	}

	
	public List<StudentFeePaymentDTO> getStudentsCompletedFee() {
		return studentsRepo.findAllByFee_Amount(TOTAL_SCHOOL_FEE)
		.stream()
		.map(student -> studentToDTO(student))
		.collect(Collectors.toList());
	}
	
	private Student findStudent(String registrationNum) {
		Optional<Student> student = studentsRepo.findByRegNumber(registrationNum);
		
		if(student.isPresent()) {
			Student studentInfo = student.get();
			if(studentInfo.getStatus() == (short)StudentStatus.ADMITTED.ordinal()) {
				return studentInfo;
			} else {
				throw new InvalidProcessingState(String.format("Student %s",
						registrationNum));
			}
		} else {
			lg.error("Could not locate student with registrationNUm {}", registrationNum);
			throw new ResourceNotFound(String.format("Student %s", 
					registrationNum)); 
		}
	}
	
	private StudentFeePaymentDTO studentToDTO(Student stud) {
		StudentFeePaymentDTO out = new StudentFeePaymentDTO();
		out.setClassCode(stud.getSclass().getCode());
		out.setPayementDate(stud.getFee().getPaymentDate());
		out.setRegistrationNumber(stud.getRegNumber());
		
		return out;
	}

}
