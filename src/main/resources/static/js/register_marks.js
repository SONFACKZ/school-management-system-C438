var formID= "register-marks-form";
var loadStudsForm = "load-classstuds-form";

$(document).ready(function(){
	
	$("#"+loadStudsForm).on('submit', function(event) {
		event.preventDefault();
		
		queryForStudentsIn(getClassCodeFromHTML());
	});
	
	$("#"+formID).on('submit', function(event) {
		event.preventDefault();
		
		submitMarks();
	});
})


function submitMarks() {
	data=[];
	marks = $("td.mark");
	regs = $("td.regNum");

	for (i=0,j=0; i<marks.length, j<regs.length; i++,j++) {
	    data.push({"mark":Number(marks[i].innerText), "registrationNumber":regs[j].innerText});
	}
	
	console.log(data);
	var subCode = $("input#subCode")[0].value;
	var cCode = getClassCodeFromHTML();
	
	$.ajax({
		method:"PUT",
		url:"/sms/ac/ts/api/student_marks/class/"+cCode+"/subject/"+subCode+"/fill",
		data:JSON.stringify(data),
		contentType: 'application/json',
		dataType:'application/json'
	}).done(function(data){
		console.log("success");
	}).catch(function(e){
		console.log(e);
	})
}

function getClassCodeFromHTML() {
	return $("select#classCode")[0].value;
}

function queryForStudentsIn(cCode) {
	
	$.ajax({
		method:"GET",
		url:"/sms/ac/api/classes/"+cCode+"/students",
		contentType: 'application/json',
	}).done(function(data){
		
		showLoadedStudents(data);
		
		console.log(data);
	}).catch(function(e){
		console.log(e);
	})
}

function showLoadedStudents(data) {
	var studentRows = [];
	
	//table body
	var tb = $("table")[0].tBodies[0];
	
	studentRows = $.map(data, function(student) {
		return studentToStudRow(student);
	});
	
	tb.innerHTML=studentRows.join();
}

function studentToStudRow(student) {
	return "<tr><td>"+student.name+"</td>" +
			"<td  class='regNum'>"+student.regNum+"</td>" +
					"<td  class='mark' contenteditable='true'>0</td>" +
					"</tr>";	
}
