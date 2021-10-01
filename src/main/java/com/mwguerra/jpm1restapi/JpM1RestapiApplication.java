package com.mwguerra.jpm1restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class JpM1RestapiApplication {
  public static void main(String[] args) {
    SpringApplication.run(JpM1RestapiApplication.class, args);
  }

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.OAS_30)
      .apiInfo(new ApiInfoBuilder()
        .title("Students API")
        .description("A CRUD API to manage students in a hypothetical school")
        .version("0.0.1")
        .license("MIT")
        .licenseUrl("https://opensource.org/licenses/MIT")
        .build())
      .tags(new Tag("Students", "Endpoints for CRUD operations on students"))
      .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
      .build();
  }
}
