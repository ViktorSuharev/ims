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
	private static final String CLIENT_ID = "client";
	private static final String SECRET = "secret";
	private static final String READ_SCOPE = "read";
	private static final String WRITE_SCOPE = "write";
	private static final String PASSWORD_GRANT_TYPE = "password";
	private static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";
	private static final String[] ALL_AUTHORITIES = {"USER", "ADMIN"};

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
				.withClient(CLIENT_ID)
				.secret(SECRET)
				.accessTokenValiditySeconds(OAUTH_TOKEN_EXPIRATION)
				.scopes(READ_SCOPE, WRITE_SCOPE)
				.authorizedGrantTypes(PASSWORD_GRANT_TYPE, REFRESH_TOKEN_GRANT_TYPE)
				.authorities(ALL_AUTHORITIES);
	}
}
