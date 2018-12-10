package shopping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


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
			.paths(PathSelectors.regex("/api.*"))      
            .build();
                                                              
    }
    


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    

}