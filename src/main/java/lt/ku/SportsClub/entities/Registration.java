package lt.ku.SportsClub.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="registrations")
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "client_id")
	private Client client;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "workout_id")
	private Workout workout;
	
	@Column(columnDefinition = "datetime default CURRENT_TIMESTAMP") 
	private java.sql.Date registration_date;

	public Registration() {
	}

	public Registration(Client client, Workout workout, Date registration_date) {
		this.client = client;
		this.workout = workout;
		this.registration_date = registration_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.sql.Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(java.sql.Date registration_date) {
		this.registration_date = registration_date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
}
