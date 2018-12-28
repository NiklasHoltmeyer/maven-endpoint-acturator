package de.hsos.bachelorarbeit.nh.endpoint.acturator.framework.spring.Configuration;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ActuatorSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .requestMatcher(EndpointRequest.toAnyEndpoint())
                .anonymous()
                //.authorizeRequests()
                //.anyRequest().authenticated()
                .and()
                .httpBasic();
        // @formatter:on
    }

}
