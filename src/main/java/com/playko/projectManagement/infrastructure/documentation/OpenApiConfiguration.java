package com.playko.projectManagement.infrastructure.documentation;

import com.playko.projectManagement.shared.constants.SwaggerConstants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI openApiConfig() {
        Schema<?> mapSchema = new Schema<Map<String, String>>()
                .addProperty("message", new StringSchema().example("string"));
        Schema<?> errorSchema = new Schema<Map<String, String>>()
                .addProperty("error", new StringSchema().example("string"));
        return new OpenAPI()
                .info(new Info()
                        .title(SwaggerConstants.SWAGGER_TITLE_MESSAGE)
                        .description(SwaggerConstants.SWAGGER_DESCRIPTION_MESSAGE)
                        .version(SwaggerConstants.SWAGGER_VERSION_MESSAGE)
                        .license(new License().name(SwaggerConstants.SWAGGER_LICENSE_NAME_MESSAGE).url(SwaggerConstants.SWAGGER_LICENSE_URL_MESSAGE))
                        .termsOfService(SwaggerConstants.SWAGGER_TERMS_OF_SERVICE_MESSAGE))
                .components(new Components()
                        .addSchemas("Map", mapSchema)
                        .addSchemas("Error", errorSchema));

    }
}