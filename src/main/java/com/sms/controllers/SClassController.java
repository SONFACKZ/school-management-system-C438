package com.sms.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dtos.ClassStudentDTO;
import com.sms.dtos.NewClassData;
import com.sms.dtos.StudentFeePaymentDTO;
import com.sms.dtos.NewStudentMarkDTO;
import com.sms.services.ClassesService;

@RestController
@RequestMapping("/sms")
public class SClassController {
	private ClassesService classesService;
	private static final Logger lg = LoggerFactory.getLogger(SClassController.class);
	
	public SClassController(ClassesService classesService) {
		this.classesService = classesService;
	}
	
	
	@PostMapping("/ac/ad/api/classes/add")
	public ResponseEntity<?> addClass(@RequestBody NewClassData newClass) {
		lg.info("In controller, saving {}", newClass.getName());
		classesService.addClass(newClass);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/ac/api/classes/{code}/students")//issues with security
	public ResponseEntity<List<ClassStudentDTO>> getStudentsInClass(@PathVariable String code) {
		return ResponseEntity.ok(classesService.getAllStudents(code));
	}
	
	@PutMapping("/ac/ts/api/classes/{code}/subjects/{code}/marks")
	public ResponseEntity<?> fillMarks(@RequestBody List<NewStudentMarkDTO> studentMarks) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


