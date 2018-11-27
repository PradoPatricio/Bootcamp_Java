package shopping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        final List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
        responseMessageList.add(new ResponseMessageBuilder()
            .code(500)
            .message("Server Error")
            .responseModel(new ModelRef("Error"))
            .build());
            responseMessageList.add(new ResponseMessageBuilder()
            .code(403)
            .message("Forbidden!")
            .build());
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)       
            .globalResponseMessage(RequestMethod.GET, responseMessageList)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())        
            .build();
            //.securitySchemes(Arrays.asList(securityScheme()))
            //.securityContexts(Arrays.asList(securityContext()));                                                    
    }
    

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Shopping cart API",
                "Allows creating of items and a shopping cart",
                "API TOS",
                "Terms of service",
                new Contact("Patricio Prado", "www.example.com", "myeaddress@company.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    /*
    @Bean
    public SecurityConfiguration security() {
    return SecurityConfigurationBuilder.builder()
        .clientId("1")
        .clientSecret("1")
        .scopeSeparator(" ")
        .useBasicAuthenticationWithAccessCodeGrant(true)
        .build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
            .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
            .tokenRequestEndpoint(
            new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
            .build();
    
        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
            .grantTypes(Arrays.asList(grantType))
            .scopes(Arrays.asList(scopes()))
            .build();
        return oauth;
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = { 
        new AuthorizationScope("read", "for read operations"), 
        new AuthorizationScope("write", "for write operations"), 
        new AuthorizationScope("foo", "Access foo API") };
        return scopes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
        .securityReferences(
            Arrays.asList(new SecurityReference("spring_oauth", scopes())))
        .forPaths(PathSelectors.regex("/foos.*"))
        .build();
    }

*/

}