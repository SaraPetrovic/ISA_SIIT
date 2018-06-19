package ProjectIsa.bioskop.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ProjectIsa.bioskop.domain.Hall;
import ProjectIsa.bioskop.domain.PoluHall;
import ProjectIsa.bioskop.domain.TheaterOrCinema;
import ProjectIsa.bioskop.service.HallServiceImpl;
import ProjectIsa.bioskop.service.TheaterOrCinemaService;

@RestController
public class HallController {
	@Autowired
	private HallServiceImpl service;
	@Autowired
	private TheaterOrCinemaService cinemaService;

	@RequestMapping(
			value = "/api/halls",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Hall>> getHalls() {
		
		Collection<Hall> halls = service.getHalls();
		if (halls != null){
			return new ResponseEntity<Collection<Hall>>(halls, HttpStatus.OK); 
		}else{
			return new ResponseEntity<Collection<Hall>>(halls, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(
			value= "/api/hall/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<Hall> getHallById(@PathVariable("id") String id){
		
		Hall hall = service.getHallByName(id);
		if (hall != null){
			return new ResponseEntity<Hall>(hall, HttpStatus.OK); 
		}else{
			return new ResponseEntity<Hall>(hall, HttpStatus.NOT_FOUND);
		}
	}

	
	@RequestMapping(
			value = "/api/addHall",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<String> addHall1(@RequestBody PoluHall poluHall){
		Hall hall = new Hall();
		hall.setName(poluHall.getId());
		
		int row = poluHall.getRows();
		int col = poluHall.getColumns();
		
		hall.setMaxRow(row);
		hall.setMaxColumn(col);
		
		List<TheaterOrCinema> cinemas = cinemaService.getTheaterOrCinemas();
		
		
		TheaterOrCinema cinema = new TheaterOrCinema();
		for(TheaterOrCinema c : cinemas) {
			if(c.getName().equals(poluHall.getName())){
				cinema = c;
			}
		}
		hall.setTheaterOrCinema(cinema);
		String message = service.addHall(hall);
		if(message == null) {
			return new ResponseEntity<String>("{\"msg\":\"Hall is successfully added!\"}", HttpStatus.OK);
		}
		return new ResponseEntity<String>("{\"msg\": \""+message+"\"}", HttpStatus.BAD_REQUEST);
	}
}
