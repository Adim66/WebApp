package tp4.enit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Allow CORS for all paths under /api/
                .allowedOrigins("http://localhost:4200") // Allow only from localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","LOGIN") // Allow these HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow cookies or authentication data to be shared
    }
}