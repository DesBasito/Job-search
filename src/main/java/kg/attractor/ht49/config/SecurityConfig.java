package kg.attractor.ht49.config;

import kg.attractor.ht49.services.Components.AuthUserDetailsService;
import kg.attractor.ht49.services.Components.RedirectIfAuthenticatedFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DataSource dataSource;
    private final PasswordEncoder encoder;
    private final AuthUserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()).addFilterBefore(new RedirectIfAuthenticatedFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/","api/vacancies/paging").permitAll()
                        .requestMatchers(HttpMethod.GET, "resume/create", "resume/update").hasAuthority("employee")
                        .requestMatchers(HttpMethod.GET, "employer/**", "vacancies/create", "vacancies/update").hasAuthority("employer")
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
                        .requestMatchers( "/api/users/**","/vacancies/filter").permitAll()
                        .requestMatchers(HttpMethod.POST, "employer/*", "/employer/**").hasAuthority("employer")
                        .requestMatchers(HttpMethod.POST, "employee/*", "/employee/**").hasAuthority("employee")
                        .requestMatchers(HttpMethod.POST, "chat/**","/api/message").authenticated()
                        .requestMatchers(HttpMethod.GET, "chat/**","/api/message/**").authenticated()
                        .anyRequest().permitAll())
                .rememberMe( remember ->
                        remember.tokenValiditySeconds(2*24*60*60)
                                .userDetailsService(userDetailsService)
                                .tokenRepository(persistentTokenRepository())
                );
        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
