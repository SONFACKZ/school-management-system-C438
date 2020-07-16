package com.sms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dtos.NewStudentMarkDTO;
import com.sms.dtos.StudentMarkDTO;
import com.sms.services.StudentMarksService;

@RestController
@RequestMapping("/sms")
public class StudentMarksController {
	private StudentMarksService smService;
	
	public StudentMarksController(StudentMarksService smService) {
		this.smService = smService;
	}
	
	@PutMapping("/ac/ts/api/student_marks/class/{cCode}/subject/{subCode}/fill")
	public ResponseEntity<?> fillStudentMarks(
			@PathVariable String cCode,
			@PathVariable String subCode,
			@RequestBody List<NewStudentMarkDTO> studSub) {
		
		smService.fillMarks(cCode, subCode, studSub);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/ac/st/api/student_marks/student/{registrationNum}/results/{schoolYear}")
	public ResponseEntity<List<StudentMarkDTO>> getStudentResults(@PathVariable("schoolYear") String schoolYear,
			@PathVariable("registrationNum") String regNum) {
//		return ResponseEntity.ok(smService.getResults(schoolYear, regNum));
		return new ResponseEntity<>(smService.getResults(schoolYear, regNum), HttpStatus.OK);
	}
}


