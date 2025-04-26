package com.example.Artalia.Config;

import com.example.Artalia.Data.RoleEntity;
import com.example.Artalia.Model.ApplicationRole;
import com.example.Artalia.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Artalia.EntryPoint.AuthEntryPointJwt;
import com.example.Artalia.Filter.AuthTokenFilter;
import com.example.Artalia.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private AuthEntryPointJwt unAuthorizedHandler;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain (HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((request) -> 
            request
                .requestMatchers("/user/accounts", "/user/account", "/auth/user", "/register/user", "/song/items", "/song/item")
                    .permitAll()
                .requestMatchers("/*/delete", "/song", "/user", "/songlist")
                    .hasRole("USER")
                    .anyRequest()
                    .authenticated()
        );
        http.headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions.sameOrigin())
        );
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unAuthorizedHandler));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository) {
        return args -> {
            RoleEntity adminRole = roleRepository.findByRoleName(ApplicationRole.ROLE_ADMIN)
                    .orElseGet(() -> {
                        RoleEntity createAdminRole = new RoleEntity(ApplicationRole.ROLE_ADMIN);
                        return roleRepository.save(createAdminRole);
                    });

            RoleEntity userRole = roleRepository.findByRoleName(ApplicationRole.ROLE_USER)
                    .orElseGet(() -> {
                        RoleEntity createUserRole = new RoleEntity(ApplicationRole.ROLE_USER);
                        return roleRepository.save(createUserRole);
                    });
        };
    }
}
