package com.masglobal.recruitment.javatest.configs;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
  
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.serializationInclusion(Include.NON_NULL);
    builder.serializationInclusion(Include.NON_ABSENT);
    builder.propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
    builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
  }
}
