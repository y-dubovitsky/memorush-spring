package ru.dubovitsky.flashcardsspring.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.dubovitsky.flashcardsspring.security.filter.JwtTokenVerifierFilter;
import ru.dubovitsky.flashcardsspring.security.filter.JwtUsernameAndPasswordAuthFilter;

@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager()));
        http.addFilterAfter(new JwtTokenVerifierFilter(), JwtUsernameAndPasswordAuthFilter.class);
    }
}
