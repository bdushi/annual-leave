package de.dlh.lhind.annualleave.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    Docket api()  {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                 .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .apis(RequestHandlerSelectors.basePackage("de.dlh.lhind.annualleave.controller"))
                .paths(PathSelectors.ant("/*"))
                .build()
                // .pathMapping("/")
                .directModelSubstitute(LocalDateTime.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                /*.securitySchemes(listOf(oauth()))
                .securityContexts(listOf(securityContext()))*/
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo()  {
        return new ApiInfoBuilder()
                .title("Courier Logistic App")
                .description("Swagger Configuration")
                .termsOfServiceUrl("http://springfox.io")
                .contact(new Contact("Bruno Dushi", "https://github.com/bdushi", "brddev@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("1.0.0")
                .build();
    }
}
