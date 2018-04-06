package ninja.backend;

import ninja.backend.web.rest.exception.ErrorResponse;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;


public final class RestResponse<ResponseType> {

    private final Optional<ResponseType> dtoResponse;
    private final Optional<ErrorResponse> error;
    private final int status;

    private RestResponse(Optional<ResponseType> dtoResponse, Optional<ErrorResponse> error, int status) {
        this.dtoResponse = dtoResponse;
        this.error = error;
        this.status = status;
    }

    public static <ResponseType> RestResponse<ResponseType> fromMvcResult(MvcResult result, ObjectMapper objectMapper, Class<?>... classes) throws Exception {
        if (classes.length == 0) {
            throw new IllegalArgumentException("Missing arguments - there needs to be at least one class type passed for parameter <Class<?> classes>.");
        }
        final String rawData = result.getResponse().getContentAsString();

        if (result.getResponse().getStatus() >= 200 && result.getResponse().getStatus() < 300) {
            return new RestResponse<>(convertData(rawData, objectMapper, classes), Optional.empty(), result.getResponse().getStatus());
        }
        return new RestResponse<>(Optional.empty(), convertData(rawData, objectMapper, ErrorResponse.class), result.getResponse().getStatus());
    }

    public ResponseType getData() {
        return this.dtoResponse.get();
    }

    public ErrorResponse getError() {
        return this.error.get();
    }

    public int getStatus() {
        return this.status;
    }

    private static <ResponseType> Optional<ResponseType> convertData(String rawData, ObjectMapper objectMapper, Class<?>... classes) throws Exception {
        if (classes[0].equals(Void.class)) {
            return Optional.empty();
        } else if (classes[0] == List.class) {
            final JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, classes[1]);
            return Optional.of(objectMapper.readValue(rawData, type));
        }
        return Optional.of((ResponseType) objectMapper.readValue(rawData, classes[0]));
    }
}