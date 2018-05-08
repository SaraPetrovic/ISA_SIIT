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
	}
	
	
	
	