package com.sms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dtos.NewClassData;
import com.sms.dtos.StudentFeePaymentDTO;
import com.sms.dtos.StudentMarkDTO;
import com.sms.services.ClassesService;

@RestController
@RequestMapping("/sms")
public class SClassController {
	private ClassesService classesService;
	
	public SClassController(ClassesService classesService) {
		this.classesService = classesService;
	}
	
	@PostMapping("/ac/ad/api/classes/add")
	public ResponseEntity<?> addClass(@RequestBody NewClassData newClass) {
		classesService.addClass(newClass);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/ac/api/classes/{code}/students")//issues with security
	public ResponseEntity<List<StudentFeePaymentDTO>> getStudentsInClass(@PathVariable String code) {
		return ResponseEntity.ok(classesService.getAllStudents(code));
	}
	
	@PutMapping("/ac/ts/api/classes/{code}/subjects/{code}/marks")
	public ResponseEntity<?> fillMarks(@RequestBody List<StudentMarkDTO> studentMarks) {
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
