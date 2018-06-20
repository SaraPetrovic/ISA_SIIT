package ProjectIsa.bioskop.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ProjectIsa.bioskop.domain.ChangedMovie;
import ProjectIsa.bioskop.domain.MovieOrPerformance;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.domain.UserType;
import ProjectIsa.bioskop.service.MovieOrPerformanceServiceImpl;

@RestController
public class MovieOrPerformanceController {
	
	public final static String  DEFAULT_IMAGE_FOLDER = "src/main/webapp/images/";
	
	@Autowired 
	MovieOrPerformanceServiceImpl serviceMovie;
	@Autowired 
	HttpServletRequest request;
	
	@RequestMapping(
			value = "/api/movies",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MovieOrPerformance>> getMovies() {
	
		Collection<MovieOrPerformance> movies = serviceMovie.getAll();
		
		if(movies == null) {
			return new ResponseEntity<Collection<MovieOrPerformance>>(movies, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Collection<MovieOrPerformance>>(movies, HttpStatus.OK);
		}

	}
	
	@RequestMapping(
			value= "/api/movies/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<MovieOrPerformance> getMovie(@PathVariable("id") Long id){
		
		MovieOrPerformance movie = serviceMovie.findById(id);
		if (movie != null){
			return new ResponseEntity<MovieOrPerformance>(movie, HttpStatus.OK); 
		}else{
			return new ResponseEntity<MovieOrPerformance>(movie, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(
			value = "/api/createMovie",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<String> addMovie(@RequestBody MovieOrPerformance movie){
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null || sessionUser.getUserType() != UserType.CINEMAADMIN) {
			return new ResponseEntity<String>("{\"msg\":\"You are not logged in as cinema admin!\"}", HttpStatus.CONFLICT);
		}
		
		String message = serviceMovie.add(movie);
		
		if(message == null) {
			return new ResponseEntity<String>("{\"msg\":\"Movie/performance is successfully added!\"}", HttpStatus.OK);			
		}else {
			return new ResponseEntity<String>("{\"msg\": \""+message+"\"}", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "api/changeMovie", 
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<String> changeInstitution(@RequestBody ChangedMovie changeMovie){
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null || sessionUser.getUserType() != UserType.CINEMAADMIN) {
			return new ResponseEntity<String>("{\"msg\":\"You are not logged in as cinema admin!\"}", HttpStatus.CONFLICT);
		}
		
		MovieOrPerformance movie = serviceMovie.findByName(changeMovie.getSelectMovie());
		MovieOrPerformance newMovie = new MovieOrPerformance();
		newMovie.setName(changeMovie.getName());
		newMovie.setActors(changeMovie.getActors());
		newMovie.setType(changeMovie.getType());
		newMovie.setProducer(changeMovie.getProducer());
		newMovie.setFilmDuration(changeMovie.getFilmDuration());
		newMovie.setImg(changeMovie.getImg());
		newMovie.setDescription(changeMovie.getDescription());
		
		String message = serviceMovie.changeMovie(movie, newMovie);
		if(message == null) {
			return new ResponseEntity<String>("{\"msg\":\"Movie/performance is successfully changed!\"}", HttpStatus.OK);
		}
		return new ResponseEntity<String>("{\"msg\": \""+message+"\"}", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "api/uploadMovieImage",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile file,
							@RequestParam("itemImage") String imageName) {
		if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(DEFAULT_IMAGE_FOLDER  + imageName )));
                stream.write(bytes);
                stream.close();
                System.out.println("Upload...");

                return "You successfully uploaded " + "images/file.jpg" + "!";
            } catch (Exception e) {
                return "You failed to upload " + "images/file.jpg" + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + "images/file.jpg" + " because the file was empty.";
        }
	}
}
