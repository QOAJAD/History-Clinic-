package com.qoajad.backend.spring.configuration;

import com.qoajad.backend.constants.AuthenticationConstants;
import com.qoajad.backend.spring.filter.AuthenticationHeaderValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Configuration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationHeaderValidatorFilter authenticationHeaderValidatorFilter;

    @Autowired
    public Configuration(@Qualifier("defaultUserDetailsService") final UserDetailsService userDetailsService, @Qualifier("defaultAuthenticationHeaderValidator") final AuthenticationHeaderValidatorFilter authenticationHeaderValidatorFilter) {
        this.userDetailsService = userDetailsService;
        this.authenticationHeaderValidatorFilter = authenticationHeaderValidatorFilter;
    }

    public void configure(HttpSecurity http) throws Exception {
        http
            // Disable csrf.
            .csrf().disable()
            // make sure we use stateless session to avoid storing the user state.
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // don't authenticate request to the following url.
            .authorizeRequests().antMatchers("/" + AuthenticationConstants.AUTHENTICATION_ROOT_URI +"**").permitAll().and()
            // Filter before the authentication stage to set the authenticated user.
            .addFilterAfter(authenticationHeaderValidatorFilter, UsernamePasswordAuthenticationFilter.class)
            // Apply the authentication settings.
            .authorizeRequests()
            // All other requests that aren't part of the antMatchers need to be authenticated.
            .antMatchers("/**").authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure password encoding for the user details.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * This bean is required to be injected into the AuthorizationServerConfiguration.
     * By default uses the bcrypt encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * This bean is required to be injected into the AuthorizationServerConfiguration.
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
