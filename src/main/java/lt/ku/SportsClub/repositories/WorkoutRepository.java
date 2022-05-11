package lt.ku.SportsClub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.SportsClub.entities.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Integer>{

}
