package org.SpringConfigClient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class ConfigClientConfig extends WebSecurityConfigurerAdapter {
	@Value("#{'${service.username:admin}'}") // property with default value
	String userName;

	@Value("#{'${service.password:admin}'}") // property with default value
	String user_password;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password(user_password).roles("USER").and().withUser(userName)
				.password(user_password).roles("USER", "ACTUATOR");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/encrypt/**").authenticated()
        .antMatchers("/decrypt/**").authenticated();
	}
}
