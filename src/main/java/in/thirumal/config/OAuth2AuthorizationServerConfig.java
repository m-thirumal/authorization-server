/**
 * 
 */
package in.thirumal.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.keys.KeyManager;
import org.springframework.security.crypto.keys.StaticKeyGeneratingKeyManager;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


import static org.springframework.security.config.Customizer.withDefaults;
/**
 * @author Thirumal
 *
 */
@EnableWebSecurity
//@Order(201)
@Configuration(proxyBeanMethods = false)
@Import(OAuth2AuthorizationServerConfiguration.class)
public class OAuth2AuthorizationServerConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		RegisteredClient pilotClient = RegisteredClient.withId(UUID.randomUUID().toString()).clientId("thirumal")
				.clientSecret("thirumal").clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS).scope("aircraft.fly")
				.scope("aircraft.board").build();
		RegisteredClient passengerClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId("passenger-client").clientSecret("secret")
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS).scope("aircraft.board").build();
		RegisteredClient sailorClient = RegisteredClient.withId(UUID.randomUUID().toString()).clientId("sailor-client")
				.clientSecret("secret").clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS).scope("boat.sail")
				.scope("boat.board").build();
		return new InMemoryRegisteredClientRepository(pilotClient, passengerClient, sailorClient);
	}

	@Bean
	public KeyManager keyManager() {
		return new StaticKeyGeneratingKeyManager();
	}
//	@Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(authorizeRequests ->
//          authorizeRequests.anyRequest().authenticated()
//        )
//          .formLogin(withDefaults());
//        return http.build();
//    }
	
//  // fix issue option 2:
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//    OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
//            new OAuth2AuthorizationServerConfigurer<>();
//    RequestMatcher[] endpointMatchers = authorizationServerConfigurer
//            .getEndpointMatchers().toArray(new RequestMatcher[0]);
//
//    httpSecurity
//            .requestMatcher(new OrRequestMatcher(endpointMatchers))
//            .authorizeRequests(authorizeRequests ->
//                    authorizeRequests.anyRequest().authenticated()
//            )
//            .formLogin(withDefaults())
//            .csrf(csrf -> csrf.ignoringRequestMatchers(endpointMatchers))
//            .apply(authorizationServerConfigurer);
//
//    return httpSecurity.build();
//  }

    @Bean
    UserDetailsService users() {
        UserDetails user = User.withDefaultPasswordEncoder()
          .username("thirumal")
          .password("thirumal")
          .roles("USER")
          .build();
        return new InMemoryUserDetailsManager(user);
    }

}
