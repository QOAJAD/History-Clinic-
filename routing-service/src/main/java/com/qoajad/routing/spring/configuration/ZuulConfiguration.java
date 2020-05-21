package com.qoajad.routing.spring.configuration;

import com.qoajad.routing.spring.filter.AuthenticationHeaderValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class ZuulConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationHeaderValidatorFilter authenticationHeaderValidatorFilter;

    @Autowired
    public ZuulConfiguration(@Qualifier("defaultAuthenticationHeaderValidator") final AuthenticationHeaderValidatorFilter authenticationHeaderValidatorFilter) {
        this.authenticationHeaderValidatorFilter = authenticationHeaderValidatorFilter;
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // Activate cors configuration
        http.cors().and();
        http
                // Disable csrf.
                .csrf().disable()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // Add the filter that is in charge of validating the cookie.
                .addFilterAfter(authenticationHeaderValidatorFilter, UsernamePasswordAuthenticationFilter.class)
                // Handle the authentication settings.
                .authorizeRequests()
                // dont ask for authentication this particular request
                .antMatchers("/authentication/**").permitAll()
                // all other requests need to be authenticated
                .antMatchers("/**").authenticated();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
