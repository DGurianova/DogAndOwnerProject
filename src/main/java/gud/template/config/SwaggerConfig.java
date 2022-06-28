package gud.template.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getAllApis() {
        final String docTitle = "ABC Title string";
        final String docDescription = "Description of ABC string";
        return buildD(Docket.DEFAULT_GROUP_NAME, PathSelectors.any(), docTitle, docDescription);
    }

    @Bean
    public Docket getTemplateApis() {
        final String docTitle = "Template api Title string";
        final String docDescription = "This is template api";
        final String pathRegexp = "(/template).+";
        return buildD("template", PathSelectors.regex(pathRegexp), docTitle, docDescription);
    }

    private Docket buildD(String groupName, Predicate<String> pathSelectors, String title, String description) {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Collections.singleton("application/json"))
                .groupName(groupName)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(pathSelectors)
                .build()
                .apiInfo(buildInfo(title, description));
    }

    private ApiInfo buildInfo(String title, String description) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version("X.Y.Z")
                .contact(new Contact("ABC1", "https://abcde.com", "abcde@xyz.com"))
                .build();

    }
}
