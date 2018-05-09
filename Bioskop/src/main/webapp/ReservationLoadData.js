function loadCinemas(){
	$.ajax({
		type: "GET",
		url: "/api/TheaterOrCinemas",
		success: function(theatersOrCinemas){
			document.getElementById("reservation1-btn").disabled = false;
			var select = document.getElementById("CorT-select");			
			$.each(theatersOrCinemas, function(i, cinema){
				var option = document.createElement("OPTION");
				select.add(option);
				option.value = cinema.name;
				option.text = cinema.name;
			});
	
		},
		error: function() {
			document.getElementById("reservation1-btn").disabled = true;
			alert("There is no cinemas/theaters added - reservation is disabled!");
		}
	});
}


$("#reservation1-confirm-btn").click(function() {
	document.getElementById("reservation2-btn").disabled = false;
	$('#reservation1').modal('toggle');
});




function confirmReservation1(){
	
}
