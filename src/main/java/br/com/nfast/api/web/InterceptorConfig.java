package br.com.nfast.api.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class InterceptorConfig {

    @Bean
    public MappedInterceptor mappedInterceptor() {
        return new MappedInterceptor(new String[]{"/**"}, new RequestInterceptor());
    }

}
