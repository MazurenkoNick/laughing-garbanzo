package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder routeBuilder) {
		return routeBuilder
				.routes()
				.route(p -> p.path("/api/departments/**")
							 .uri("lb://DEPARTMENT-SERVICE"))
				.route(p -> p.path("/api//employee/**")
							 .uri("lb://EMPLOYEE-SERVICE"))
				.build();
	}

}
