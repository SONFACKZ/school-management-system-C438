function payFees() {
	$.ajax({
		method:"PATCH",
		url:"/sms/ac/st/api/fees/student/R20001/pay",
		contentType:"application/json",
		dataType:"application/json"
	})
}

function getResults() {
	var regNum= "R20001";
	var fYear = "2020";
	
	$.ajax({
		method:"GET",
		url:"/sms/ac/st/api/student_marks/student/"+regNum+"/results/"+fYear,
		contentType:"application/json",
		dataType:"application/json"
	}).done(function(data) {
		console.log(data);
	});
}