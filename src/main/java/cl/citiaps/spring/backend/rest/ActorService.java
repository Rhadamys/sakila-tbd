package cl.citiaps.spring.backend.rest;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.citiaps.spring.backend.entities.Actor;
import cl.citiaps.spring.backend.entities.Film;
import cl.citiaps.spring.backend.repository.ActorRepository;
import cl.citiaps.spring.backend.repository.FilmRepository;

@RestController  
@RequestMapping("/actors")
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private FilmRepository filmRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Actor> getAllUsers() {
		return actorRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Actor findOne(@PathVariable("id") Integer id) {
		return actorRepository.findOne(id);
	}
	
	@RequestMapping(value = "/{id}/films", method = RequestMethod.GET)
	@ResponseBody
	public Set<Film> findFilms(@PathVariable("id") Integer id) {
		return actorRepository.findOne(id).getFilms();
	}
	
	@RequestMapping(
			value = "/{actorId}/films/{filmId}", 
			method = RequestMethod.POST)
	@ResponseBody
	public Set<Film> addFilm(
			@PathVariable("actorId") Integer actorId, 
			@PathVariable("filmId") Integer filmId,
			HttpServletResponse httpResponse) {
		if(!actorRepository.exists(actorId)) {
			httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
			return null;
		}
		Actor actor = actorRepository.findOne(actorId);

		for(Film actorFilm: actor.getFilms()) {
			if(actorFilm.getFilmId() == filmId) {
				httpResponse.setStatus(HttpStatus.NOT_MODIFIED.value());
				return null;
			}
		}

		if(!filmRepository.exists(filmId)) {
			httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
			return null;
		}
		Film film = filmRepository.findOne(filmId);
		
		actor.addFilm(film);
		film.addActor(actor);
		filmRepository.save(film);
		httpResponse.setStatus(HttpStatus.CREATED.value());
		return actorRepository.save(actor).getFilms();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Actor create(@RequestBody Actor resource) {
	     return actorRepository.save(resource);
	}
	 
}