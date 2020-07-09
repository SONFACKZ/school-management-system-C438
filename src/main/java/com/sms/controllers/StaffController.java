package com.sms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.constants.StaffTypes;
import com.sms.dtos.NewStaffData;
import com.sms.dtos.StaffDTO;
import com.sms.services.StaffService;

@RestController
@RequestMapping("/sms")
public class StaffController {
	private StaffService staffService;
	
	public StaffController(StaffService staffService) {
		this.staffService = staffService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addStaff(@RequestBody NewStaffData staffData) {
		staffService.add(staffData);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{sn}/update")
	public ResponseEntity<?> updateStaffInfo(@RequestBody NewStaffData staffData, @PathVariable int sn) {
		
		staffService.update(sn, staffData);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping("/{sn}/dismiss")
	public ResponseEntity<?> dismiss(@PathVariable int sn) {
		
		staffService.dismiss(sn);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/get_all")
	public ResponseEntity<List<StaffDTO>> getAllStaff() {
		return new ResponseEntity<>(staffService.getAll(null), HttpStatus.OK);
	}
	
	@GetMapping("/get_teaching")
	public ResponseEntity<List<StaffDTO>> getAllTeachingStaff() {
		return new ResponseEntity<>(staffService.getAll(StaffTypes.TEACHING.toString()), HttpStatus.OK);
	}
	
	@GetMapping("/get_non_teaching")
	public ResponseEntity<List<StaffDTO>> getAllNonTeachingStaff() {
		return new ResponseEntity<>(staffService.getAll(StaffTypes.NON_TEACHING.toString()), HttpStatus.OK);
	}
	
	@GetMapping("/get_teaching/sorted/doj")
	public ResponseEntity<List<StaffDTO>> getAllTeachingStaffOrdered() {
		return new ResponseEntity<>(staffService.getTeachingSorted(StaffTypes.NON_TEACHING.toString(), "doj"), HttpStatus.OK);
	}
}
