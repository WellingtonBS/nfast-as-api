package br.com.nfast.api.web;

import br.com.nfast.api.config.Config;
import br.com.nfast.api.utils.Dates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.nfast.api.services"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("NFast API")
                .description("NFast API via REST")
                .version(Config.API_VERSION)
                .license("Instance ID [ " + Config.INSTANCE_ID + " ] started in " + Dates.format(Config.START_TIME))
                .build();
    }

}
