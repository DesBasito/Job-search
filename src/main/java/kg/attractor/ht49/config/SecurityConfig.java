package kg.attractor.ht49.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DataSource dataSource;
    private final PasswordEncoder encoder;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String USER_QUERY = "select USERS.EMAIL, USERS.PASSWORD,USERS.ENABLED from USERS where EMAIL = ?";
        String USER_ROLE = """
                SELECT USERS.EMAIL, AUTHORITIES.ROLE
                                              FROM USERS
                                              JOIN USER_AUTHORITY ON USERS.ID = USER_AUTHORITY.USER_ID
                                              JOIN AUTHORITIES ON USER_AUTHORITY.AUTHORITY_ID = AUTHORITIES.ID
                                              WHERE USERS.EMAIL = ?;
                """;
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(USER_ROLE)
                .passwordEncoder(encoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "vacancies").permitAll()
                        .requestMatchers(HttpMethod.GET, "resume/create", "resume/update").hasAuthority("employee")
                        .requestMatchers(HttpMethod.GET, "employer/**", "vacancies/create", "vacancies/update").hasAuthority("employer")
                        .requestMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                        .requestMatchers( "/api/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "employer/*", "/employer/**").hasAuthority("employer")
                        .requestMatchers(HttpMethod.POST, "employee/*", "/employee/**").hasAuthority("employee")
                        .requestMatchers(HttpMethod.POST, "chat/**","/api/message").authenticated()
                        .requestMatchers(HttpMethod.GET, "chat/**","/api/message/**").authenticated()
                        .anyRequest().permitAll());
        return http.build();
    }
}
