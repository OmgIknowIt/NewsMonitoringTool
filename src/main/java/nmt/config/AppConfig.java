package nmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
  @Bean
  public InternalResourceViewResolver internalResourceViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("templates/");
    resolver.setSuffix(".html");
    return resolver;
  }
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry){
    registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
    registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
    registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
    registry.addResourceHandler("/scss/**").addResourceLocations("classpath:/static/scss/");
    registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
  }

}
