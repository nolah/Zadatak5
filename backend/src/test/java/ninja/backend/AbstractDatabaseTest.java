package ninja.backend;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonInclude;
import ninja.backend.config.CustomProperties;
import ninja.backend.repository.*;
import ninja.backend.api.dto.*;
import java.util.List;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import ninja.backend.model.*;
import eu.execom.fabut.Fabut;
import eu.execom.fabut.IFabutRepositoryTest;


@ActiveProfiles(resolver = TestProfileResolver.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@Transactional
public abstract class AbstractDatabaseTest implements IFabutRepositoryTest {

    @Inject
    private CustomProperties customProperties;

    @Inject
    protected UserRepository userRepository;

    @Inject
    protected AircraftRepository aircraftRepository;

    @Inject
    protected ImageRepository imageRepository;

    @Inject
    protected AirlineRepository airlineRepository;

    @Before
    public void setup() {
        Fabut.beforeTest(this);
    }

    @After
    public void after() {
        Fabut.afterTest();
    }

    @Override
    public void fabutBeforeTest() {
        //TODO I am not sure what this method is for, fabut works without implementing it
    }

    @Override
    public void fabutAfterTest() {
        //TODO I am not sure what this method is for, fabut works without implementing it
    }

    @Override
    public List<Class<?>> getComplexTypes() {
        List<Class<?>> complexTypes = new LinkedList<>();
        complexTypes.add(SignInResponse.class);
        complexTypes.add(RefreshTokenRequest.class);
        complexTypes.add(SignUpRequest.class);
        complexTypes.add(SignInRequest.class);
        complexTypes.add(ChangePasswordRequest.class);
        complexTypes.add(AircraftsResponse.class);
        complexTypes.add(ReadAircraftRequest.class);
        complexTypes.add(ReadAircraftResponse.class);
        complexTypes.add(CreateAircraftRequest.class);
        complexTypes.add(UpdateAircraftRequest.class);
        complexTypes.add(AirlinesResponse.class);
        complexTypes.add(ReadAirlineRequest.class);
        complexTypes.add(ReadAirlineResponse.class);
        complexTypes.add(CreateAirlineRequest.class);
        complexTypes.add(UpdateAirlineRequest.class);
        complexTypes.add(FindFileRequest.class);
        complexTypes.add(FileUploadDTO.class);
        complexTypes.add(FileDTO.class);
        return complexTypes;
    }

    @Override
    public List<Class<?>> getIgnoredTypes() {
        return new LinkedList<>();
    }

    @Override
    public void customAssertEquals(Object expected, Object actual) {
        Assert.assertEquals(expected, actual);
    }

    @Override
    public List<?> findAll(Class<?> clazz) {
        if (clazz == User.class) {
            return userRepository.findAll();
        } else if (clazz == Aircraft.class) {
            return aircraftRepository.findAll();
        } else if (clazz == Image.class) {
            return imageRepository.findAll();
        } else if (clazz == Airline.class) {
            return airlineRepository.findAll();
        }

        throw new IllegalStateException("No findAll for class: " + clazz.getName());
    }

    @Override
    public Object findById(final Class<?> entityClass, final Object id) {
        if (entityClass == User.class) {
            return userRepository.findOne((Long) id);
        } else if (entityClass == Aircraft.class) {
            return aircraftRepository.findOne((Long) id);
        } else if (entityClass == Image.class) {
            return imageRepository.findOne((Long) id);
        } else if (entityClass == Airline.class) {
            return airlineRepository.findOne((Long) id);
        }

        throw new IllegalStateException("No findById for class: " + entityClass.getName());
    }

    @Override
    public List<Class<?>> getEntityTypes() {
        List<Class<?>> entityTypes = new LinkedList<>();
        entityTypes.add(User.class);
        entityTypes.add(Aircraft.class);
        entityTypes.add(Image.class);
        entityTypes.add(Airline.class);
        return entityTypes;
    }

}
