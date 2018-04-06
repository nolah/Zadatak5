package ninja.backend;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


public class WebInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        String profile = System.getProperty("spring.profiles.active");
        return application.profiles(profile == null ? "dev" : profile).sources(BackendApplication.class);
    }

}
