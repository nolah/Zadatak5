package ninja.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ninja.backend.model.*;


public interface FlightRepository extends JpaRepository<Flight, Long>, FlightRepositoryCustom {

}
