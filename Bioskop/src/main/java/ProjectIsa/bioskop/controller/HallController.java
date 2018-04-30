package ProjectIsa.bioskop.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;

import ProjectIsa.bioskop.domain.Hall;
import ProjectIsa.bioskop.service.HallServiceImpl;

public class HallController {
	@Autowired
	private HallServiceImpl service;

	@RequestMapping(
			value = "/api/halls",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Hall>> getHall() {
		
		Collection<Hall> halls = service.getHalls();

		return new ResponseEntity<Collection<Hall>>(halls,
				HttpStatus.OK);
	}
	
	@RequestMapping(
			value= "/api/hall/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<Hall> getHall(@PathVariable("id") String id){
		
		Hall hall = service.getHall(id);
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
	public ResponseEntity<Hall> addHall(@RequestBody Hall hall){
		
		Hall newHall = service.addHall(hall);
		return new ResponseEntity<Hall>(newHall, HttpStatus.OK);
	}
}
