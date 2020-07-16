package com.sms.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sms.dtos.ClassStudentDTO;
import com.sms.dtos.NewClassData;
import com.sms.dtos.StudentFeePaymentDTO;
import com.sms.exceptions.ResourceNotFound;
import com.sms.models.SClass;
import com.sms.models.SchoolYear;
import com.sms.models.Student;
import com.sms.repositories.ClassesRepository;
import com.sms.repositories.SchoolYearsRepository;
import com.sms.repositories.StudentsRepository;

@Service
public class ClassesService {
	private static final Logger lg = LoggerFactory.getLogger(ClassesService.class);
	
	private SchoolYearsRepository schoolYearsRepository;
	private ClassesRepository classesRepository;
	private StudentsRepository studentsRepository;
	
	public ClassesService(ClassesRepository classesRepository,
			SchoolYearsRepository schoolYearsRepository, StudentsRepository studentsRepository) {
		this.classesRepository= classesRepository;
		this.schoolYearsRepository = schoolYearsRepository;
		this.studentsRepository = studentsRepository;
	}
	
	public void addClass(NewClassData data) {
		SchoolYear year = new SchoolYear();
		year.setSyFirst(data.getSyFirst());
		year.setSySecond(data.getSySecond());
		
		lg.info("Saving new school year {}", year.toString());
		year = schoolYearsRepository.save(year);
		
		SClass newClass = new SClass();
		newClass.setCode(data.getCode());
		newClass.setName(data.getName());
		newClass.setsYear(year);
		
		lg.info("Registering new class {} for the {} academic year", 
				newClass.getCode(), year.toString());
		classesRepository.save(newClass);
	}

	public List<ClassStudentDTO> getAllStudents(String code) {
		return getForYear(code, "2020")
				.getStudents()
				.stream()
				.map(student -> studentToCSDTO(student))
				.collect(Collectors.toList());
	}
	
	private SClass getForYear(String code, String syFirst) {
		Optional<SClass> sClass = classesRepository.findByCodeAndYear_syFirst(code, syFirst);
		
		if(sClass.isPresent())
			return sClass.get();
		else
			throw new ResourceNotFound("Class: "+code);
	}
	private StudentFeePaymentDTO studentToDTO(Student student) {
		StudentFeePaymentDTO stud = new StudentFeePaymentDTO();
		stud.setRegistrationNumber(student.getRegNumber());	
		return stud;
	}
	private ClassStudentDTO studentToCSDTO(Student student) {
		ClassStudentDTO stud = new ClassStudentDTO();
		stud.setName(String.format("%s %s", student.getfName(), student.getlName()));
		stud.setRegNum(student.getRegNumber());
		return stud;
	}
}
