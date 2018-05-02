package ProjectIsa.bioskop.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.service.ProjectionServiceImpl;

@RestController
public class ProjectionController {
	
	@Autowired
	ProjectionServiceImpl service;
	
	@RequestMapping(
					value = "/api/projections",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Projection>> getProjections() {
		
		
		Collection<Projection> projections = service.getProjections();

		if(projections == null) {
			return new ResponseEntity<Collection<Projection>>(projections, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Collection<Projection>>(projections, HttpStatus.OK);
		}

		
	}
	@RequestMapping(
			value= "/api/projections/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<Projection> getProjection(@PathVariable("id") Long id){
		
		Projection projection = service.getProjection(id);
		if (projection != null){
			return new ResponseEntity<Projection>(projection, HttpStatus.OK); 
		}else{
			return new ResponseEntity<Projection>(projection, HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(
			value = "/api/createProjection",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<Projection> addProjection(@RequestBody Projection projection){
		Projection newProjection = service.addProjection(projection);
		if(newProjection == null) {
			return new ResponseEntity<Projection>(newProjection, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Projection>(newProjection, HttpStatus.OK);
		}
	}
}
