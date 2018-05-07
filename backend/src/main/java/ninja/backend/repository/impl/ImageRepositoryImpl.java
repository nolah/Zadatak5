package ninja.backend.repository.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.repository.ImageRepositoryCustom;

import com.querydsl.jpa.JPQLQueryFactory;


public class ImageRepositoryImpl implements ImageRepositoryCustom {

    private final Logger log = LoggerFactory.getLogger(ImageRepositoryImpl.class);

    @Inject
    private JPQLQueryFactory factory;

    @Override
    public List<Image> findByFile(String filePath) {
        log.trace(".findByFile(filePath: {})", filePath);
        final QImage image = QImage.image;
        return factory.select(image).from(image).where(image.file.path.eq(filePath)).fetch();
    }

    @Override
    public List<Image> findByAircraft(Long aircraftId) {
        log.trace(".findByAircraft(aircraftId: {})", aircraftId);
        final QImage image = QImage.image;
        return factory.select(image).from(image).where(image.aircraft.id.eq(aircraftId)).fetch();
    }

}
