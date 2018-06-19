package ProjectIsa.bioskop.controller;

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

import ProjectIsa.bioskop.domain.ChangedInstitution;
import ProjectIsa.bioskop.domain.TheaterOrCinema;
import ProjectIsa.bioskop.service.TheaterOrCinemaService;

@RestController
public class TheaterOrCinemaController {

	@Autowired
	private TheaterOrCinemaService service;

	@RequestMapping(
			value = "/api/TheaterOrCinemas",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TheaterOrCinema>> getTheaterOrCinemas() {
		
		List<TheaterOrCinema> cinemas = service.getTheaterOrCinemas();

		if (cinemas != null){
			return new ResponseEntity<List<TheaterOrCinema>>(cinemas, HttpStatus.OK); 
		}else{
			return new ResponseEntity<List<TheaterOrCinema>>(cinemas, HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(
			value= "/api/TheaterOrCinema/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<TheaterOrCinema> getTheaterOrCinema(@PathVariable("id") Long id){
		
		TheaterOrCinema cinema = service.getTheaterOrCinema(id);
		if (cinema != null){
			return new ResponseEntity<TheaterOrCinema>(cinema, HttpStatus.OK); 
		}else{
			return new ResponseEntity<TheaterOrCinema>(cinema, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(
			value = "/api/createTheaterOrCinema",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<String> addTheaterOrCinema(@RequestBody TheaterOrCinema item){
		String message = service.addTheaterOrCinema(item);
		if(message == null) {
			return new ResponseEntity<String>("{\"msg\":\"Cinema/theater is successfully added!\"}", HttpStatus.OK);
		}
		return new ResponseEntity<String>("{\"msg\": \""+message+"\"}", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "api/changeInstitution", 
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<String> changeInstitution(@RequestBody ChangedInstitution changeInstitution){
		
		TheaterOrCinema institution = service.findByName(changeInstitution.getSelectInstitution());
		TheaterOrCinema newInstitution = new TheaterOrCinema();
		newInstitution.setName(changeInstitution.getName());
		newInstitution.setAdress(changeInstitution.getAdress());
		newInstitution.setDescription(changeInstitution.getDescription());
		
		String message = service.changeInstitution(institution, newInstitution);
		if(message == null) {
			return new ResponseEntity<String>("{\"msg\":\"Cinema/theater is successfully changed!\"}", HttpStatus.OK);
		}
		return new ResponseEntity<String>("{\"msg\": \""+message+"\"}", HttpStatus.BAD_REQUEST);
	}
	
	
	@RequestMapping(
			value = "/api/changeRepertoar",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<String> changeRepertoar(@RequestBody String item){
		
		String[] splitResult = item.split("\"");
		Long projectionId = Long.parseLong(splitResult[3].split(" ")[0]);
		Long cinemaId = Long.parseLong(splitResult[3].split(" ")[1]);
		
		TheaterOrCinema cinema = service.getTheaterOrCinema(cinemaId);
		
		String message = service.changeRepertoar(cinema, projectionId);
		if(message == null) {
			return new ResponseEntity<String>("{\"msg\":\"Projection is successfully removed!\"}", HttpStatus.OK);
		}
		return new ResponseEntity<String>("{\"msg\": \""+message+"\"}", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(
			value = "/api/TheaterOrCinema/{cinemaID}/projections",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<List<Projection>> getCinemasProjections(@PathVariable("cinemaID") Long id) {
		TheaterOrCinema t = service.getTheaterOrCinema(id);
		List<Projection> ret = null;
		if (t != null) {
			ret = t.getProjections();
		}
		if (ret == null) {
			return new ResponseEntity<List<Projection>>(ret, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<Projection>>(ret, HttpStatus.OK);
	}
}
