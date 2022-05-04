package neu.edu.info6350.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

/**
 * @author arronshentu
 */
@Configuration
public class MyBatisConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customJackson() {
    return jacksonObjectMapperBuilder -> {
      jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
      jacksonObjectMapperBuilder.failOnUnknownProperties(false);
      jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    };
  }

}
