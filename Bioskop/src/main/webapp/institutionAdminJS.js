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
					option.text = hall.name;
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
	
	// change repertoar
	function loadCinemas(){
		var select;
		$.ajax({
			type: "GET",
			url: "/api/TheaterOrCinemas",
			success: function(theatersOrCinemas){
				$("#institutionsSelect").empty();
				select = document.getElementById("institutionsSelect");
				$.each(theatersOrCinemas, function(i, cinema){
					var option = document.createElement("OPTION");
					select.add(option);
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
	
	/*
	function findHall(name){
		var institution;
	
		$.ajax({
			type: "GET",
			url: "/api/halls",
			success: function(halls){
				$.each(halls, function(i, hall){
					if(hall.id === name){
						institution = hall;
					}
				});
				
			}
		});
		return institution;
	}*/
	
	