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
import cl.citiaps.spring.backend.entities.FilmActor;
import cl.citiaps.spring.backend.repository.ActorRepository;
import cl.citiaps.spring.backend.repository.FilmActorRepository;
import cl.citiaps.spring.backend.repository.FilmRepository;

@RestController  
@RequestMapping("/actors")
public class FilmActorService {
	
	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private FilmActorRepository filmActorRepository;

	@RequestMapping(
			value = "/{actorId}/films/{filmId}", 
			method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Set<Film> addFilm(
			@PathVariable("actorId") Integer actorId, 
			@PathVariable("filmId") Integer filmId) {
		Actor actor = actorRepository.findOne(actorId);

		Set<Film> actorFilms = actor.getFilms();
		
		for(Film actorFilm: actorFilms) {
			if(actorFilm.getFilmId() == filmId)
				return null;
		}

		actor.addFilm(filmRepository.findOne(filmId));
		return actorRepository.save(actor).getFilms();
	}
	 
}