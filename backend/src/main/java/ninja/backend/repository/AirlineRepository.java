package ninja.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ninja.backend.model.*;


public interface AirlineRepository extends JpaRepository<Airline, Long>, AirlineRepositoryCustom {

}
