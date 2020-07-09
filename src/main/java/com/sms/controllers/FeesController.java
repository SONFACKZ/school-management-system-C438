package com.sms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.dtos.StudentFeePaymentDTO;
import com.sms.services.FeesService;

@RestController
@RequestMapping("/sms")
public class FeesController {

	private FeesService feeService;
	
	public FeesController(FeesService feeService) {
		this.feeService = feeService;
	}
	
	@PatchMapping("/ac/st/api/fees/student/{regNum}/pay")
	public ResponseEntity<?> payFees(@PathVariable String regNum) {
		feeService.payFor(regNum);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/ac/ad/api/fees/completed")
	public ResponseEntity<List<StudentFeePaymentDTO>> getStudentsCompletedFees() {	
		return new ResponseEntity<>(feeService.getStudentsCompletedFee(),
				HttpStatus.OK);
	}
}
