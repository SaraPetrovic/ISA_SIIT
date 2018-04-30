
$("#login-btn").click(function() {
	
	
	var loginData = {};
	loginData.username = $("#username-field").val();
	loginData.password = $("#password-field").val();
	loginData.userType = "SYSTEMADMIN";
	var address = { "city" : "", "street" : ""};

	loginData.address = address;
	//loginData["address"] = address;
	loginData.email = "";
	loginData.firstName = "";
	loginData.lastName = "";
	
	var success = false;
	
	
	$.ajax({
		type: "POST",
		url: "/api/login",
		data: JSON.stringify(loginData),
		dataType: "json",
		success: function(response) { 
			$("#username-field").hide();
			$("#password-field").hide();
			$("#admin-button").hide();
			$("#login-btn").hide();
			$("#registration-btn").hide();
			$("#logout-btn").show();
			$("#body").empty(); 
			$("#body").append("<h1><br/><br/>Uspesno ste se ulogovali!</h1>");
			if (response.userType == "SYSTEMADMIN" || response.userType == "FANZONEADMIN" || response.userType == "CINEMAADMIN"){
				if (response.isFirstLogin){
					$.get("changePassModal.html", function( modalPanel ) {
						  $('#body').append(modalPanel);
						  $('#passwordModal').modal({backdrop: 'static', keyboard: false});

						});
				}
			}else{
				alert("admin nije ulogovan");
			}
										
										},
		error: function() {alert("nije uspeo");},
		contentType: "application/json",
		
	});
	
	
	
	
});
