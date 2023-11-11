package k24.testjava.config;

import k24.testjava.service.CustomMemberDetailsService;
import k24.testjava.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class MemberSecurityConfig {
    @Bean
    public UserDetailsService memberDetailsService() {
        return new CustomMemberDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder2() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider2() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(memberDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder2());

        return authProvider;
    }
    @Bean
    protected SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider2());
        http.authorizeRequests().antMatchers("/").permitAll();

        http.antMatcher("/member/**")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/member/login")
                .usernameParameter("username")
                .loginProcessingUrl("/member/login")
                .defaultSuccessUrl("/member/home")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/");

        return http.build();
    }
}
