package cl.citiaps.spring.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="film_id", unique=true, nullable=false)
	private int filmId;

	@Column(name="title", nullable=false, length=255)
	private String title;

	@Column(name="description", nullable=true)
	private String description;

	@Column(name="release_year", nullable=false)
	private int releaseYear;
	
	@Column(name="rental_duration", nullable=false)
	private int rentalDuration;
	
	@Column(name="rental_rate", nullable=false)
	private double rentalRatio;
	
	@Column(name="length", nullable=true)
	private int length;
	
	@Column(name="replacement_cost", nullable=false)
	private double replacementCost;
	
	@Column(name="rating", nullable=true)
	private String rating;
	
	@Column(name="special_features", nullable=true)
	private String specialFeatures;
	
	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;
	
	@ManyToMany
	@JoinTable(name="film_actor",
		joinColumns=
			@JoinColumn(name="film_id"),
		inverseJoinColumns=
			@JoinColumn(name="actor_id")
	)
	private Set<Actor> actors;

	public Film() {
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRatio() {
		return rentalRatio;
	}

	public void setRentalRatio(double rentalRatio) {
		this.rentalRatio = rentalRatio;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public Rating getRating() {
		return Rating.getByName(rating);
	}

	public void setRating(Rating rating) {
		this.rating = rating.getName();
	}

	public Set<String> getSpecialFeatures() {
	   if(specialFeatures == null)
	      return Collections.emptySet();
	   else
	      return Collections.unmodifiableSet(
	    		  new HashSet<String>(Arrays.asList(specialFeatures.split(",")))
	    		  );
	}

	public void setSpecialFeatures(Set<String> genre) {
		if(genre == null)
	    	specialFeatures = null;
	    else
	    	specialFeatures = String.join(",", genre);
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
}