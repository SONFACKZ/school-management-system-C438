package com.sms.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sms.dtos.NewStudentMarkDTO;
import com.sms.dtos.StudentMarkDTO;
import com.sms.exceptions.ResourceNotFound;
import com.sms.models.Student;
import com.sms.models.StudentSubject;
import com.sms.models.Subject;
import com.sms.repositories.ClassesRepository;
import com.sms.repositories.StudentMarksRepository;
import com.sms.repositories.StudentsRepository;
import com.sms.repositories.SubjectsRepository;

@Service
public class StudentMarksService {
	private static final Logger lg = LoggerFactory.getLogger(StudentMarksService.class);
	
	private StudentMarksRepository studMarksRepo;
	private ClassesRepository classesRepository;
	private StudentsRepository studentsRepository;
	private SubjectsRepository subjectsRepository;
	
	public StudentMarksService(StudentMarksRepository studMarksRepo, ClassesRepository classesRepository,
			StudentsRepository studentsRepository, SubjectsRepository subjectRepository) {
		this.studMarksRepo = studMarksRepo;
		this.studentsRepository = studentsRepository;
		this.classesRepository = classesRepository;
		this.subjectsRepository = subjectRepository;
	}

	public void fillMarks(String cCode, String subCode, List<NewStudentMarkDTO> studSub) {
		
		if(classesRepository.existsByCode(cCode)) {//verify that the specified class exists
			lg.info("verifying availability of subject {}", subCode);
			if(subjectsRepository.existsByCode(subCode)) {//verify that the specified subject exists
				studSub
				.stream()
				.map(studMark -> registerMark(cCode, subCode, studMark))
				.collect(Collectors.toList());
			} else {
				throw new ResourceNotFound("Invalid subject "+subCode);
			}
		} else {
			throw new ResourceNotFound("Invalid class "+cCode);
		}
		
	}
	
	/**
	 * 
	 * @param schoolYear: The last part of the academic year
	 * for example suppose the academic year: 1980-1991, schoolYear will be 1991
	 * @param studentRegNum
	 * @return
	 * @apiNote Does not consider past years, but only the current years
	 */
	public List<StudentMarkDTO> getResults(String schoolYear, String studentRegNum) {
		Optional<Student> student = studentsRepository.findByRegNumber(studentRegNum);
		
		if(student.isPresent()) {
			Student studentDetails = student.get();
			
//			if(studentDetails.getSclass().getsYear().getSySecond() == schoolYear) 
			{
				return studentDetails.getStudSubs()
						.stream().map(pair -> studentToSMDTO(pair))
						.collect(Collectors.toList());
			}
		} else {
			throw new ResourceNotFound("Student "+studentRegNum);
		}
	}
	
	private StudentSubject registerMark(String cCode, String subCode, NewStudentMarkDTO set) {
		
		Optional<Student> student = studentsRepository.findByRegNumber(set.getRegistrationNumber());
		
		if(student.isPresent()) {
			Student studentData = student.get();
			
			Subject subject = subjectsRepository.findByCode(subCode)
					.get();//there was already a check of presence in caller method
			
			StudentSubject newRecord = new StudentSubject();
			newRecord.setMark(set.getMark());
			newRecord.setStudent(studentData);
			newRecord.setSubject(subject);
			newRecord = studMarksRepo.save(newRecord);
			
			
			List<StudentSubject> subjects = studentData.getStudSubs();
			subjects.add(newRecord);
			studentData.setStudSubs(subjects);
			
			studentsRepository.save(studentData);
			
			return newRecord;
		} else {
			throw new ResourceNotFound(String.format("Student %s", set.getRegistrationNumber()));
		}
	}

	private NewStudentMarkDTO studentToDTO(StudentSubject studSub) {
		NewStudentMarkDTO studMark = new NewStudentMarkDTO();
		
		studMark.setMark(studSub.getMark());
		studMark.setRegistrationNumber(studSub.getSubject().getTitle());
		
		return studMark;
	}
	
	private StudentMarkDTO studentToSMDTO(StudentSubject studSub) {
		StudentMarkDTO studMark = new StudentMarkDTO();
		
		studMark.setMark(studSub.getMark());
		studMark.setCode(studSub.getSubject().getCode());
		studMark.setTitle(studSub.getSubject().getTitle());
		
		return studMark;
	}
}
