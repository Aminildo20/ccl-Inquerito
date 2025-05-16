package ccl.inquerito.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ccl.inquerito.serviceImpl.UsuarioDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
    private UsuarioDetailsServiceImpl usuarioDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/api-ccl/inquerito").permitAll()
            	.requestMatchers("/conta/**").permitAll()
                .requestMatchers(
                		"/admin/login", 
                		"/ccl/inquerito",
                		"/ccl/fomulario-inquerito-acao",
                		"/css/**",
                		"/fonts/**",
                		"/index",
						"/static/**",
						"/templates/**",
						"/images/**",
						"/js/**",
						"/css/**",
						"/process-login"
                		).permitAll()
                .requestMatchers("/admin/relatorio/**").authenticated()
                .requestMatchers("/admin/**").authenticated()
                .anyRequest().authenticated()	
            )
            .formLogin(form -> form
                .loginPage("/admin/login")
                .loginProcessingUrl("/process-login")
                .usernameParameter("telemovel") 
                .passwordParameter("password")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .failureHandler(new CustomAuthenticationFailureHandler())
                .defaultSuccessUrl("/admin/dashboard", true)
                .permitAll()                
            )
            
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/admin/login")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
            	    .authenticationEntryPoint((request, response, authException) -> {
            	        String uri = request.getRequestURI();
            	        String accept = request.getHeader("Accept");
            	        String xhr = request.getHeader("X-Requested-With");

            	        // 1. Requisições AJAX ou JSON recebem 401 Unauthorized
            	        if ("XMLHttpRequest".equalsIgnoreCase(xhr) || "application/json".equalsIgnoreCase(accept)) {
            	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado.");
            	            return;
            	        }
            	        // 2. Se a URI solicitada for /admin/login, deixa ir para lá
            	        if (uri.equals("/admin/login")) {
            	            response.sendRedirect("/admin/login");
            	            return;
            	        }
            	        // 3. Qualquer outro caso redireciona para /ccl/inquerito
            	        response.sendRedirect("/ccl/inquerito");
            	    })
            	)

            .csrf(csrf -> csrf.disable());

        return http.build();
      }
    
    
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(usuarioDetailsService)
            .passwordEncoder(passwordEncoder())
            .and()
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
