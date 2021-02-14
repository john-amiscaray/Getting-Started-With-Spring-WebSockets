package me.john.amiscaray.springwebsocketdemo.config;

import me.john.amiscaray.springwebsocketdemo.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Set up simplified security settings requiring all requests to be authenticated
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .fullyAuthenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Tell Spring to ignore securing the handshake endpoint. This allows the handshake to take place unauthenticated
        web.ignoring().antMatchers("/stomp/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // Create an AuthenticationManager bean to Authenticate users in the ChannelInterceptor
    @Bean
    public AuthenticationManager authManager() throws Exception {

        return this.authenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder(10);

    }

}
