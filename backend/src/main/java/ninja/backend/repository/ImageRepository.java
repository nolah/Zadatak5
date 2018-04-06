package ninja.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ninja.backend.model.*;


public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom {

}
