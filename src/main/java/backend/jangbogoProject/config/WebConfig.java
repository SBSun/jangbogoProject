package backend.jangbogoProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig {
    @Bean(name="jsonView")
    public MappingJackson2JsonView jsonView() {
        return new MappingJackson2JsonView();
    }
}
