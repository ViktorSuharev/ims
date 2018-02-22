package com.visu.align.ims.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	private static final int OAUTH_TOKEN_EXPIRATION = 600;
	private static final String ADMIN_CLIENT_ID = "admin";
	private static final String USER_CLIENT_ID = "user";
	private static final String SECRET = "secret";

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) {
		configurer.authenticationManager(authenticationManager);
		configurer.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient(USER_CLIENT_ID)
				.secret(SECRET)
				.accessTokenValiditySeconds(OAUTH_TOKEN_EXPIRATION)
				.scopes("read")
				.authorizedGrantTypes("password", "refresh_token")
				.resourceIds("resource")
				.and()
				.withClient(ADMIN_CLIENT_ID)
				.secret(SECRET)
				.accessTokenValiditySeconds(OAUTH_TOKEN_EXPIRATION)
				.scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token")
				.resourceIds("resource");
	}

}
