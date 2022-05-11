package lt.ku.SportsClub.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="workouts")
public class Workout {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	private java.sql.Date date;
	
	private Integer places;
	
	private String location;

	public Workout() {
	}

	public Workout(String name, Date date, Integer places, String location) {
		this.name = name;
		this.date = date;
		this.places = places;
		this.location = location;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
