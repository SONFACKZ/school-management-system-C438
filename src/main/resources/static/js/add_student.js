$(document).ready(function(){
	$("#add-student-form").on('submit', function(event) {
		event.preventDefault();
		var form = document.getElementById("add-student-form");
		var formData = new FormData(form);
		
		
		var jsonObject = {};

		for (const [key, value]  of formData) {
		    jsonObject[key] = value;
		}
		
		console.log(jsonObject);
		
		$.ajax({
			method:"POST",
			url:"/sms/api/students/register",
			data:JSON.stringify(jsonObject),
			contentType: 'application/json',
			dataType:'application/json',
			processData:false
		}).done(function(data){
			console.log("success");
		})
	});
})