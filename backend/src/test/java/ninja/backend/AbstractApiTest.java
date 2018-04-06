package ninja.backend;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.querydsl.core.types.Order;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ninja.backend.api.dto.*;
import ninja.backend.model.enumeration.*;
import java.time.ZonedDateTime;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.inject.Inject;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


public abstract class AbstractApiTest extends AbstractDatabaseTest {

    protected MockMvc mockMvc;

    @Inject
    public ObjectMapper objectMapper;

    @Inject
    private WebApplicationContext context;

    @Before
    public void apiTestSetup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

    }

    public RestResponse<SignInResponse> refreshToken(RefreshTokenRequest request) throws Exception {

        final MvcResult result = mockMvc.perform(post("/api" + "/refresh-token").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(request)).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        return RestResponse.fromMvcResult(result, objectMapper, SignInResponse.class);
    }

    public RestResponse<Void> signUp(SignUpRequest request) throws Exception {

        final MvcResult result = mockMvc.perform(post("/api" + "/sign-up").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(request)).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        return RestResponse.fromMvcResult(result, objectMapper, Void.class);
    }

    public RestResponse<SignInResponse> signIn(SignInRequest request) throws Exception {

        final MvcResult result = mockMvc.perform(post("/api" + "/sign-in").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(request)).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        return RestResponse.fromMvcResult(result, objectMapper, SignInResponse.class);
    }

    public RestResponse<Void> changePassword(ChangePasswordRequest request, String accessToken) throws Exception {

        final MvcResult result = mockMvc.perform(post("/api" + "/change-password").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + accessToken)
                .content(convertObjectToJsonBytes(request)).accept(MediaType.APPLICATION_JSON)).andReturn();

        return RestResponse.fromMvcResult(result, objectMapper, Void.class);
    }

    public RestResponse<List<AircraftsResponse>> aircrafts(String accessToken) throws Exception {

        final MvcResult result = mockMvc.perform(get("/api" + "/aircrafts").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        return RestResponse.fromMvcResult(result, objectMapper, List.class, AircraftsResponse.class);
    }

    public RestResponse<ReadAircraftResponse> readAircraft(Long id, String accessToken) throws Exception {

        final MvcResult result = mockMvc.perform(
                get("/api" + "/read-aircraft").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + accessToken).param("id", id.toString()).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        return RestResponse.fromMvcResult(result, objectMapper, ReadAircraftResponse.class);
    }

    public RestResponse<Void> createAircraft(CreateAircraftRequest request, String accessToken) throws Exception {

        final MvcResult result = mockMvc.perform(post("/api" + "/create-aircraft").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + accessToken)
                .content(convertObjectToJsonBytes(request)).accept(MediaType.APPLICATION_JSON)).andReturn();

        return RestResponse.fromMvcResult(result, objectMapper, Void.class);
    }

    public RestResponse<Void> updateAircraft(UpdateAircraftRequest request, String accessToken) throws Exception {

        final MvcResult result = mockMvc.perform(put("/api" + "/update-aircraft").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + accessToken)
                .content(convertObjectToJsonBytes(request)).accept(MediaType.APPLICATION_JSON)).andReturn();

        return RestResponse.fromMvcResult(result, objectMapper, Void.class);
    }

    public RestResponse<Void> findFile(String key, String fileName) throws Exception {

        final MvcResult result = mockMvc.perform(get("/api" + "/file/" + key + "/" + fileName + "").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

        return RestResponse.fromMvcResult(result, objectMapper, Void.class);
    }

    protected byte[] convertObjectToJsonBytes(Object object) throws IOException {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.writeValueAsBytes(object);
    }
}