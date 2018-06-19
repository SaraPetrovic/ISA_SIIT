//fast rez ticket
function loadProjectionForCinema(cinemaId){
		$.ajax({
			type: "GET",
			url: "/api/projections",
			success: function(projections){
				var proj = 0;
				$("#selectProjectionForTicket").empty();
				var select = document.getElementById("selectProjectionForTicket");
				$.each(projections, function(m, projection){
					if(projection.theaterOrCinema.id == cinemaId){
						proj = 1;
						var option = document.createElement("OPTION");
						select.add(option);
						option.value = projection.id;
						option.text = projection.name;	
					}
				});
				if(proj == 0){
					swal("Sorry,", "You must create projections before you create ticket!", "info");
					$("#newTicket").modal("hide");
				}
				
				if(projections.length == 1){
					$("#seatSelect").empty();
					var i, j;
					var select = document.getElementById("seatSelect");
					
					$.each(projections, function(i, projection){
						var maxRow = projection.hall.maxRow;
						var maxColumn = projection.hall.maxColumn;
						if(projection.tickets == null){
							for (i = 1; i < maxRow + 1; i++) {
							    for (j = 1; j < maxColumn + 1; j++) {
							    	var option = document.createElement("OPTION");
							    	option.value = i + "-" + j;
									option.text = i + "-" + j;
							    	select.add(option);
									
							    }
							}
						}else{
							for (i = 1; i < maxRow + 1; i++) {
							    for (j = 1; j < maxColumn + 1; j++) {
							    	$.each(tickets, function(i, ticket){
										if(ticket.red != i && ticket.kolona != j){
											var option = document.createElement("OPTION");
											select.add(option);
											option.value = i + "-" + j;
											option.text = i + "-" + j;
										}
									});  
							    }
							}
						}
					});
				}
			},
			error: function(err){
				swal("Sorry,", "You must create projections before you create ticket!", "info");
		    	$("#newTicket").modal("hide");
			},
		});
	}

//new projection
	function loadMovie(){
		$.ajax({
			type: "GET",
			url: "/api/movies",
			success: function(movies){
				$("#selectMovie").empty();
				var selectMovie = document.getElementById("selectMovie");
				$.each(movies, function(i, movie){
					var optionMovie = document.createElement("OPTION");
					selectMovie.add(optionMovie);
					optionMovie.value = movie.name;
					optionMovie.text = movie.name;
				});
			}
		});
	}
	
	//change projection
	function loadMovie2(){
		$.ajax({
			type: "GET",
			url: "/api/movies",
			success: function(movies){
				$("#selectNewMovie").empty();
				var selectMovie = document.getElementById("selectNewMovie");
				$.each(movies, function(i, movie){
					var optionMovie = document.createElement("OPTION");
					selectMovie.add(optionMovie);
					optionMovie.value = movie.name;
					optionMovie.text = movie.name;
				});
			}
		});
	}
	//change movie
	function loadMovie1(){
		$.ajax({
			type: "GET",
			url: "/api/movies",
			success: function(movies){
				$("#selectMovieForChange").empty();
				var selectMovie = document.getElementById("selectMovieForChange");
				$.each(movies, function(i, movie){
					var optionMovie = document.createElement("OPTION");
					selectMovie.add(optionMovie);
					optionMovie.value = movie.name;
					optionMovie.text = movie.name;
				});
			}
		});
	}
	//change Projection
	function loadHalls2(){
		$.ajax({
			type: "GET",
			url: "/api/halls",
			success: function(halls){
				$("#selectNewHall").empty();
				var select = document.getElementById("selectNewHall");
				$.each(halls, function(i, hall){
					var option = document.createElement("OPTION");
					select.add(option);
					option.value = hall.name;
					option.text = hall.name;
				});
			}
		});
	}
	function loadHalls(){
		$.ajax({
			type: "GET",
			url: "/api/halls",
			success: function(halls){
				$("#selectHall").empty();
				var select = document.getElementById("selectHall");
				$.each(halls, function(i, hall){
					var option = document.createElement("OPTION");
					select.add(option);
					option.value = hall.name;
					option.text = hall.name + " (" + hall.theaterOrCinema.name + ")";
				});
				
			}
		});
	}
	
	
	function loadInstitutions(){
		$.ajax({
			type: "GET",
			url: "/api/TheaterOrCinemas",
			success: function(theatersOrCinemas){
				$("#nameCinemaOrTheater").empty();
				var select = document.getElementById("nameCinemaOrTheater");
				$.each(theatersOrCinemas, function(i, cinema){
					var option = document.createElement("OPTION");
					select.add(option);
					option.value = cinema.name;
					option.text = cinema.name;
				});
			}
		});
	}
	function loadInstitutions1(){
		$.ajax({
			type: "GET",
			url: "/api/TheaterOrCinemas",
			success: function(theatersOrCinemas){
				$("#selectInstitution").empty();
				var select1 = document.getElementById("selectInstitution");
				$.each(theatersOrCinemas, function(i, cinema){
					var option = document.createElement("OPTION");
					select1.add(option);
					option.value = cinema.name;
					option.text = cinema.name;
				});
			}
		});
	}
	
	//new projection
	function loadInstitutions2(){
		$.ajax({
			type: "GET",
			url: "/api/TheaterOrCinemas",
			success: function(theatersOrCinemas){
				$("#selectCinema").empty();
				var select1 = document.getElementById("selectCinema");
				$.each(theatersOrCinemas, function(i, cinema){
					var option = document.createElement("OPTION");
					select1.add(option);
					option.value = cinema.name;
					option.text = cinema.name;
				});
			}
		});
	}
	
	function loadRepertoar(){
		
		var institutionName = document.getElementById("institutionsSelect");
		
		$.ajax({
			type: "GET",
			url: "/api/TheaterOrCinemas",
			success: function(theatersOrCinemas){
				$.each(theatersOrCinemas, function(i, cinema){
					if(cinema.name == institutionName){
						var institution = cinema;
					}
				});
				$("#projectionForRemoveSelect").empty();
				var projectionsSelect = document.getElementById("projectionForRemoveSelect");
				$.each(institution.projections, function(i, projection){
					var option = document.createElement("OPTION");
					projectionsSelect.add(option);
					option.value = projection.id;
					option.text = projection.name;
				});
			}
		});
	}
	
	//change Projection
	function loadProjections(){
		$.ajax({
			type: "GET",
			url: "/api/projections",
			success: function(projections){
				$("#selectProjectionForChange").empty();
				var select = document.getElementById("selectProjectionForChange");
				$.each(projections, function(i, projection){
					var option = document.createElement("OPTION");
					select.add(option);
					option.value = projection.name;
					option.text = projection.name;
				});
			}
		});
	}
	
	