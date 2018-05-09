	function loadMovie(){
		$.ajax({
			type: "GET",
			url: "/api/movies",
			success: function(movies){
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
	
	function loadMovie1(){
		$.ajax({
			type: "GET",
			url: "/api/movies",
			success: function(movies){
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

	function loadHalls(){
		$.ajax({
			type: "GET",
			url: "/api/halls",
			success: function(halls){
				var select = document.getElementById("selectHall");
				if(halls == null){
					alert("You can not add projection before hall.");
					//$("#newProjection").hide();
					//document.getElementById('newProjection').innerHTML = '<span class="emptyHalls"><h1>You must add hall before projection</h1></span>';
				}else{
					$.each(halls, function(i, hall){
						var option = document.createElement("OPTION");
						select.add(option);
						option.value = hall.name;
						option.text = hall.name;
					});
				}
			}
		});
	}
	
	function loadInstitutions(){
		$.ajax({
			type: "GET",
			url: "/api/TheaterOrCinemas",
			success: function(theatersOrCinemas){
				var select = document.getElementById("nameCinemaOrTheater");
				if(theatersOrCinemas == null){
					alert("You can not add hall before theater or cinema.");
				}else{
					$.each(theatersOrCinemas, function(i, cinema){
						var option = document.createElement("OPTION");
						select.add(option);
						option.value = cinema.name;
						option.text = cinema.name;
					});
				}
			}
		});
	}
	function loadInstitutions1(){
		$.ajax({
			type: "GET",
			url: "/api/TheaterOrCinemas",
			success: function(theatersOrCinemas){
				var select1 = document.getElementById("selectInstitution");
				if(theatersOrCinemas == null){
					alert("You can not add hall before theater or cinema.");
				}else{
					$.each(theatersOrCinemas, function(i, cinema){
						var option = document.createElement("OPTION");
						select1.add(option);
						option.value = cinema.name;
						option.text = cinema.name;
					});
				}
			}
		});
	}
	
	
	
	function loadProjections(){
		$.ajax({
			type: "GET",
			url: "/api/projections",
			success: function(projections){
				var select = document.getElementById("selectProjectionForChange");
				if(projections == null){
					alert("You can not add hall before theater or cinema.");
				}else{
					$.each(projections, function(i, projection){
						var option = document.createElement("OPTION");
						select.add(option);
						option.value = projection.movieOrPerformance.name;
						option.text = projection.movieOrPerformance.name;
					});
				}
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
	
	