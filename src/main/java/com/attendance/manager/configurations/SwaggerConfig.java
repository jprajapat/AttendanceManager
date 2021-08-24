package com.attendance.manager.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	@Bean
	public Docket swaggerApiConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.attendance.manager"))
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Employee Service")
				.description("Sample Documentation Generateed Using SWAGGER2 for our Employee Manager Rest API")
				.build();
	}

}
