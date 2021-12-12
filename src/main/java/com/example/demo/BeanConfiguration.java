package com.example.demo;

import com.example.demo.beans.SpringSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;


/**
 * The type Bean configuration.
 */
@Configuration
public class BeanConfiguration {
    /**
     * Session scope bean spring session.
     *
     * @return the spring session
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SpringSession sessionScopeBean () {
        SpringSession s =  new SpringSession();
        return s;
    }
}
