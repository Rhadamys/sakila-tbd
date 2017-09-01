package cl.citiaps.spring.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the film_actor database table.
 * 
 */
@Entity
@Table(name="film_actor")
@NamedQuery(name="FilmActor.findAll", query="SELECT fa FROM Film_Actor fa")
public class FilmActor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="actor_id", unique=true, nullable=false)
	private int actorId;

	@Column(name="film_id", unique=true, nullable=false)
	private int filmId;

	public FilmActor() {
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	
	
}