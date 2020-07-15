$(document).ready(function(){
	$("#add-class-form").on('submit', function(event) {
		event.preventDefault();
		var form = document.getElementById("add-class-form");
		var formData = new FormData(form);
		
		
		var jsonObject = {};

		for (const [key, value]  of formData) {
		    jsonObject[key] = value;
		}
		
		$.ajax({
			method:"POST",
			url:"/sms/ac/ad/api/classes/add",
			data:JSON.stringify(jsonObject),
			contentType: 'application/json',
			dataType:'application/json',
			processData:false
		}).done(function(data){
			console.log("success");
		})
	});
})