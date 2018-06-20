function printNav(user){
	if (user == null){
		$("#logout-btn").hide();
		$("#profile-btn").hide();
	}else{
			$("#logout-btn").show();
			$("#profile-btn").show();
			$("#username-field").hide();
			$("#password-field").hide();
			$("#login-btn").hide();
			$("#registration-btn").hide();
			var replaceHtml= "";
			if (user.userType == "REGISTEREDUSER"){
				replaceHtml = "RegUserProfilePanel.html";	
			}else if (user.userType == "SYSTEMADMIN"){
				replaceHtml = "sysAdmin.html";
			}else if (user.userType == "FANZONEADMIN"){
				replaceHtml = "fanzoneAdmin.html";
			}else if (user.userType = "CINEMAADMIN"){
				replaceHtml = "institutionAdmin.html";
			}
			$("#profile-btn").click(function(){
				
				window.location.replace(replaceHtml);
			});
		
			
	}
	$("#logout-btn").click(function() {	
		
		$.ajax({
			type: "POST",
			url: "/api/logout",
			success: function(response) { 
				
				
				window.location.replace("index.html");
			},
			error: function() {alert("nije uspeo logout");},
			contentType: "application/json",
			
		});
		
		
		
	});	
	$("#login-btn").click(function() {

		var loginData = {};
		loginData.username = $("#username-field").val();
		loginData.password = $("#password-field").val();
		loginData.userType = "SYSTEMADMIN";
		var itemOffers = [];
		var itemReservations = [];
		loginData.itemOffers = itemOffers;
		loginData.itemReservations = itemReservations;
		var address = { "city" : "", "street" : ""};

		loginData.address = address;
		//loginData["address"] = address;
		loginData.email = "";
		loginData.firstName = "";
		loginData.lastName = "";
		
		var success = false;
		var retUser = null;
		

		$.ajax({
			type: "POST",
			url: "/api/login",
			data: JSON.stringify(loginData),
			dataType: "json",
			
			success: function(response) { 
				swal("Success", "Logged in", "success");
				printNav(response);
				if (response.userType == "SYSTEMADMIN" || response.userType == "FANZONEADMIN" || response.userType == "CINEMAADMIN"){
					if (response.isFirstLogin){
						$.get("changePassModal.html", function( modalPanel ) {
							  $('#body').append(modalPanel);
							  $('#passwordModal').modal({backdrop: 'static', keyboard: false});
							});
					}
				}
				
				//location.reload();
				
											
			},
			error: function() {swal("Error", "Bad username and password combination", "error")},
			contentType: "application/json",
			
		});
		
		
		
		
	});
}


