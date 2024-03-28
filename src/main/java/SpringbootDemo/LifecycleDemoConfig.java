package SpringbootDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifecycleDemoConfig {
    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public LifecycleDemoBean lifecycleDemoBean() {
        return new LifecycleDemoBean();
    }
}
