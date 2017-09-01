package cl.citiaps.spring.backend.rest;

import java.util.Set;

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
@RequestMapping("/films")
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private ActorRepository actorRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Film> getAllUsers() {
		return filmRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Film findOne(@PathVariable("id") Integer id) {
		return filmRepository.findOne(id);
	}
	
	@RequestMapping(value = "/{id}/actors", method = RequestMethod.GET)
	@ResponseBody
	public  Set <Actor> findActors(@PathVariable("id") Integer id) {
		return filmRepository.findOne(id).getActors();
	}
	
	@RequestMapping(
			value = "/{filmId}/actors/{actorId}", 
			method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Set<Actor> addActor( 
			@PathVariable("filmId") Integer filmId,
			@PathVariable("actorId") Integer actorId) {
		Film film = filmRepository.findOne(filmId);
		
		for(Actor filmActor: film.getActors()) {
			if(filmActor.getActorId() == actorId)
				return film.getActors();
		}
		
		Actor actor = actorRepository.findOne(actorId);
		film.addActor(actor);
		actor.addFilm(film);
		actorRepository.save(actor);
		return filmRepository.save(film).getActors();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Film create(@RequestBody Film resource) {
	     return filmRepository.save(resource);
	}
	 
}