function loadApplications(){
	// get all unadmitted students and populate
	$.ajax({
		method:"GET",
		url:"/sms/ac/ad/api/students/get_all/applied"
	}).done(function (result) {
		console.log(result);
		var table = $('#tb-students');
		var row="";
		
		$.each(result, function(index, student) {
			console.log(student);
// student = JSON.parse(student);
			row = "<tr>"+
			"<td> REGNO</td>"+
			"<td> ROLLNO</td>"+
            "<td>"+ student.rollNo +"</td>"+
            "<td>CLASS</td>"+
            "<td> NAME</td>"+
            "<td>"+ student.fName +"</td>"+
            "<td>"+ student.lName +"</td>"+
            "<td>"+ student.dob +"</td>"+
            "<td>DOR</td>"+
            "<td>"+ student.address +"</td>"+
            "<td>"+ student.city +"</td>"+
            "<td>STATE</td>"+
            "<td>PIN</td>"+
            "<td>"+ student.phone+"</td>"+
            "<td><button onclick='admitStudent("+student.rollNo+")'>Admit</button></td>"
            "</tr>";
			table.append(row);
		});
	}).catch(function(e) {
		console.log(e);
	})
};

function admitStudent(rollNo) {
	$.ajax({
		method:"PATCH",
		url:"/sms/ac/ad/api/students/accept/"+rollNo,
	}).done(function(){
		console.log("Successfully admitted student");
	});
}

function dismissStudent(rollNo) {
	$.ajax({
		method:"PATCH",
		url:"/sms/ac/ad/api/students/dismiss/"+rollNo,
	}).done(function(){
		console.log("Successfully dismissed student");
	});
}

function admittedStudents() {
	$.ajax({
		method:"GET",
		url:"/sms/ac/ad/api/students/get_all/admitted"
	}).done(function (result) {
		console.log(result);
		var table = $('#tb-admitted-students');
		var row="";
		
		$.each(result, function(index, student) {
			console.log(student);
			row = "<tr>"+
			"<td> REGNO</td>"+
			"<td> ROLLNO</td>"+
            "<td>"+ student.rollNo +"</td>"+
            "<td>CLASS</td>"+
            "<td> NAME</td>"+
            "<td>"+ student.fName +"</td>"+
            "<td>"+ student.lName +"</td>"+
            "<td>"+ student.dob +"</td>"+
            "<td>DOR</td>"+
            "<td>"+ student.address +"</td>"+
            "<td>"+ student.city +"</td>"+
            "<td>STATE</td>"+
            "<td>PIN</td>"+
            "<td>"+ student.phone+"</td>"+
            "<td><button onclick='dismissStudent("+student.rollNo+")'>Admit</button></td>"
            "</tr>";
			table.append(row);
		});
	}).catch(function(e) {
		console.log(e);
	})
}