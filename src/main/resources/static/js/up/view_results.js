$(document).ready(function() {
	getResults();
});



function getResults() {
	var regNum= "R20001";
	var fYear = "2020";
	
	$.ajax({
		method:"GET",
		url:"/sms/ac/st/api/student_marks/student/"+regNum+"/results/"+fYear,
//		dataType:"json",
		contentType:"application/json"
		
	}).done(function(data){
		console.log(data);
		displayResults(data);
	}).catch(function(e) {
		console.log(e);
	});
}

function displayResults(data) {
	var markRows = $.map(data, function(entry) {
		return buildRow(entry);
	});
	
	$("tbody")[0].innerHTML = markRows.join();
}

function buildRow(entry) {
	return  "<tr><th>"+entry.code+"</th>"+
    	"<th>"+entry.title+"</th>"+
    	"<th>"+entry.mark+"</th></tr>";
}