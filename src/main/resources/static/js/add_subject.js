var formID= "add-subject-form";

$(document).ready(function(){
	$("#"+formID).on('submit', function(event) {
		event.preventDefault();
		
		submitSubjectCreationData();
	});
})

function submitSubjectCreationData() {
	var form = document.getElementById(formID);
	var formData = new FormData(form);
	
	
	var jsonObject = {};

	for (const [key, value]  of formData) {
	    jsonObject[key] = value;
	}
	
	console.log(jsonObject);
	
	$.ajax({
		method:"POST",
		url:"/sms/ac/ad/api/subjects/add",
		data:JSON.stringify(jsonObject),
		contentType: 'application/json',
		dataType:'application/json',
		processData:false
	}).done(function(data){
		console.log("success");
	}).catch(function(e){
		console.log(e);
	})
}