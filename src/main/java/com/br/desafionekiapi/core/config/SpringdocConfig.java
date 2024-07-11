package com.br.desafionekiapi.core.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import io.swagger.v3.oas.models.tags.Tag;

@Configurable
@SecurityScheme(
		  name = "Bearer Authentication",
		  type = SecuritySchemeType.HTTP,
		  bearerFormat = "JWT",
		  scheme = "bearer"
		)
public class SpringdocConfig {

	private final String apiTitle;
	private final String apiVersion;
	private final String apiDescription;
	private final String apiLicense;
	private final String apiTag;
	private final String apiUrl;
	private final String apiExternalDescription;
	private final String apiExternalUrl;


	public SpringdocConfig(@Value("API giveto") String apiTitle, @Value("0.0.1") String apiVersion,
			@Value("API para projeto voluntario da Give To") String apiDescription,
			@Value("Apache 4.0") String apiLicense, @Value("Recursos") String apiTag,
			@Value("http://localhost/") String apiUrl,
			@Value("API para projeto de doações GiveTo") String apiExternalDescription,
			@Value("http://localhost/") String apiExternalUrl) {
		this.apiTitle = apiTitle;
		this.apiVersion = apiVersion;
		this.apiDescription = apiDescription;
		this.apiLicense = apiLicense;
		this.apiTag = apiTag;
		this.apiUrl = apiUrl;
		this.apiExternalDescription = apiExternalDescription;
		this.apiExternalUrl = apiExternalUrl;

	}

	@Bean
	public OpenAPI customOpenAPI() {
		final String securitySchemeName = "bearerAuth";
		return new OpenAPI()
				.info(new Info().title(apiTitle).version(apiVersion).description(apiDescription)
						.license(new License().name(apiLicense).url(apiUrl)))
				.externalDocs(new ExternalDocumentation().description(apiExternalDescription).url(apiExternalUrl))
				.addTagsItem(new Tag().name(apiTag));
	}
}
