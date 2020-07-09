package com.sms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dtos.NewSubjectData;
import com.sms.services.SubjectsService;

@RestController
@RequestMapping("/sms")
public class SubjectsController {
	private SubjectsService subjectsService;
	
	public SubjectsController(SubjectsService subjectsService) {
		this.subjectsService = subjectsService;
	}
	
	@PostMapping("/ac/ad/api/subjects/add") 
	public ResponseEntity<?> addSubject(@RequestBody NewSubjectData data) {
		subjectsService.add(data);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/ac/ad/api/subjects/getAll")
	public ResponseEntity<List<NewSubjectData>> getAllSubjects() {
		return new ResponseEntity<>(subjectsService.getAll(), HttpStatus.OK);
	}
}
