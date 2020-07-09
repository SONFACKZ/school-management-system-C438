package com.sms.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.constants.StudentStatus;
import com.sms.dtos.StudentApplicationData;
import com.sms.dtos.StudentDTO;
import com.sms.dtos.StudentFeePaymentDTO;
import com.sms.exceptions.InvalidProcessingState;
import com.sms.exceptions.ResourceNotFound;
import com.sms.models.SClass;
import com.sms.models.Student;
import com.sms.repositories.ClassesRepository;
import com.sms.repositories.StudentsRepository;

@Service
public class StudentService {
	private static final Logger lg = LoggerFactory.getLogger(StudentService.class);
	
	private StudentsRepository studentsRepo;
	private ClassesRepository classesRepo;

	@Autowired
	public StudentService(StudentsRepository studentsRepo, ClassesRepository classesRepo) {
		this.studentsRepo = studentsRepo;
		this.classesRepo = classesRepo;
	}

	public void apply(StudentApplicationData applicationData) {
		Optional<SClass> sclass = classesRepo.findByCode(applicationData.getClassID());

		if (sclass.isEmpty()) {
			lg.warn("Requested class, with id {} was not found", applicationData.getClassID());
			throw new ResourceNotFound(String.format("Can't find requested class %s",
					applicationData.getClassID()));
		} else {
			Student applicant = new Student();
			applicant.setAge(LocalDate.now().getYear()-applicationData.getDob().getYear());
			applicant.setfName(applicationData.getfName());
			applicant.setlName(applicationData.getlName());
			applicant.setAddress(applicationData.getAddress());
			applicant.setCity(applicationData.getCity());
			applicant.setDOB(applicationData.getDob());
			applicant.setPhone(applicationData.getPhone());
			applicant.setStatus((short) StudentStatus.APPLIED.ordinal());
			applicant.setSclass(sclass.get());

			applicant = studentsRepo.save(applicant);
			lg.info("New student application registered {} for class {}",
					applicant.getfName(), applicant.getSclass().getName());
		}
	}

	public void admit(long studentSN) {
		Student student = getAndTidy(studentSN, 
				(short)StudentStatus.APPLIED.ordinal());
		
		student.setStatus((short)StudentStatus.ADMITTED.ordinal());
		
		String year = student.getSclass().getsYear().getSyFirst();
		year = year.substring(2);
		
		String studentSNs = String.valueOf(studentSN);
		String regLastPart=pad2Size(3, studentSNs);
		String regNumber = String.format("R%s%s", year, regLastPart );
		
		student.setRegNumber(regNumber);
		student.setRegDate(LocalDate.now());
		
		studentsRepo.save(student);
		lg.info("Student {} successfully admitted on {}", 
				student.getfName()+student.getlName(), LocalDate.now());
	}
	
	public void dismiss(long studentSN) {
		Student student = getAndTidy(studentSN, 
				(short)StudentStatus.CURRENT.ordinal());
		
		student.setStatus((short)StudentStatus.DISMISSED.ordinal());
		
		String year = student.getSclass().getsYear().getSyFirst();
		year = year.substring(2);
		
		String studentSNs = String.valueOf(studentSN);
		String regLastPart=pad2Size(3, studentSNs);
		String regNumber = String.format("R%s%s", year, regLastPart );
		
		student.setRegNumber(regNumber);
		student.setRegDate(LocalDate.now());
		
		studentsRepo.save(student);
		lg.info("Student {} successfully admitted on {}", 
				student.getfName()+student.getlName(), LocalDate.now());
	}
	
	public List<StudentDTO> getAdmitted() {
		List<StudentDTO> admittedStudents = studentsRepo
		.findAll()
		.stream()
		.filter(stud -> stud.getStatus() == (short)StudentStatus.ADMITTED.ordinal())
		.map(stud -> studentToDTO(stud))
		.collect(Collectors.toList());
		
		Collections.sort(admittedStudents);
		
		return admittedStudents;
	}
	
	public List<StudentDTO> getCurrent() {
		List<StudentDTO> admittedStudents = studentsRepo
		.findAll()
		.stream()
		.filter(stud -> stud.getStatus() == (short)StudentStatus.CURRENT.ordinal())
		.map(stud -> studentToDTO(stud))
		.collect(Collectors.toList());
		
		Collections.sort(admittedStudents);
		
		return admittedStudents;
	}
	
	public List<StudentDTO> getAdmittedByName() {
		List<StudentDTO> admittedStudents = studentsRepo
		.findAll()
		.stream()
		.filter(stud -> stud.getStatus() == (short)StudentStatus.CURRENT.ordinal())
		.map(stud -> studentToDTO(stud))
		.collect(Collectors.toList());
		
		Collections.sort( admittedStudents, new NameComparator());
		
		return admittedStudents;
	}
	
	public List<StudentDTO> getNonAdmitted() {
		List<StudentDTO> students = studentsRepo
		.findAll()
		.stream()
		.filter(stud -> stud.getStatus() == (short)StudentStatus.APPLIED.ordinal())
		.map(stud -> studentToDTO(stud))
		.collect(Collectors.toList());
		
		return students;
	}
	
	private StudentFeePaymentDTO studentToPaymentDTO(Student stud) {
		StudentFeePaymentDTO studDTO = new StudentFeePaymentDTO();
		studDTO.setRegistrationNumber(stud.getRegNumber());
		
		return studDTO;
	}
	
	private StudentDTO studentToDTO(Student stud) {
		StudentDTO studDTO = new StudentDTO();
		studDTO.setDor(stud.getRegDate());
		studDTO.setAddress(stud.getAddress());
		studDTO.setCity(stud.getCity());
		studDTO.setDob(stud.getDOB());
		studDTO.setfName(stud.getfName());
		studDTO.setlName(stud.getlName());
		studDTO.setPhone(stud.getPhone());
		studDTO.setPin(stud.getPin());
		studDTO.setRegNo(stud.getRegNumber());
		studDTO.setRollNo((int)stud.getStudID());
		studDTO.setState(decipherState(stud.getStatus()));
		
		return studDTO;
	}
	
	private String decipherState(short state) {
		String val = null;
		List<StudentStatus> allStatuses = Arrays.asList(StudentStatus.values());
		
		for(StudentStatus s: allStatuses) {
			if((short)s.ordinal() == state) {
				val =  s.toString();
			} else 
				continue;
		}
		
		return val;
	}
	
	private Student getAndTidy(long studentSN, Short status) {
		Optional<Student> student = studentsRepo.findById(studentSN);
		
		if(student.isPresent()) {
			Student studentInfo = student.get();
			if(studentInfo.getStatus() == status) {
				return studentInfo;
			} else {
				lg.error("Expected student with status {} but found status {}",
						status, studentInfo.getStatus());
				throw new InvalidProcessingState(String.format("Student %d ", studentSN));
			}
		} else {
			lg.error("No student found with id {}", studentSN);
			throw new ResourceNotFound(String.format("Student id %d", studentSN));
		}
	}
	
	private String pad2Size(int size, String string) {
		String tmp = string;
		while(tmp.length() < size) {
			tmp = "0"+tmp;
		}
		return tmp;
	}

	
	
	
	private class NameComparator implements Comparator<StudentDTO> {
		@Override
		public int compare(StudentDTO arg0, StudentDTO arg1) {
			String name0 = String.format(" %s %s", arg0.getfName(), arg0.getlName());
			String name1 = String.format(" %s %s", arg1.getfName(), arg1.getlName());
			return name0.compareToIgnoreCase(name1);
		}
		
	}
}
