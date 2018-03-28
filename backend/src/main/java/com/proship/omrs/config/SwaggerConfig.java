package com.proship.omrs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.security.oauth2.provider.token.AccessTokenConverter.CLIENT_ID;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.proship.omrs"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo()).useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder().code(500).message("500 message")
                                .responseModel(new ModelRef("Error")).build(), new ResponseMessageBuilder()
                                .code(403).message("Forbidden!!!!!").build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("OMRS REST API", "Descriptions of API.",
                "OMRS-VUE 1.0",
                "Terms of service",
                new Contact("Yiwei Ge", null, "yge@proship.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());

    }

//    @Bean
//    public SecurityConfiguration security() {
//
//
//        return SecurityConfigurationBuilder.builder()
//                .clientId("test")
//                .clientSecret("test-secret")
//                .scopeSeparator(" ")
//                .useBasicAuthenticationWithAccessCodeGrant(true)
//                .build();
//    }
}

