package kg.attractor.ht49.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
//                .csrf(AbstractHttpConfigurer::disable)
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
                        .anyRequest().permitAll());
        return http.build();
    }
}
