package training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthWebSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeHttpRequests((auth)->auth
                    .requestMatchers("/index.html").permitAll()
                    .requestMatchers("/error").permitAll()
                    .requestMatchers("/user/admin.html").hasRole("ADMIN")
                    .requestMatchers("/user/dev.html").hasRole("DEV")
                    .requestMatchers("/user/user.html").hasAnyRole("USER", "DEV", "ADMIN")
                    .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling()
                    .accessDeniedPage("/error");

//                .logout()
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/")
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID");

        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER", "USER1")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails dev = User
                .withUsername("dev")
                .password(passwordEncoder().encode("dev"))
                .roles("DEV")
                .build();

        return new InMemoryUserDetailsManager(user, admin, dev);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}