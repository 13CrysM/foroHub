package cjmp.desafio.foroHub.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf ->csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeHttpRequest) ->
                        authorizeHttpRequest
                                .requestMatchers(HttpMethod.POST,"/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/cursos").permitAll()
                                .requestMatchers(HttpMethod.POST, "/respuestas").permitAll()
                                .requestMatchers(HttpMethod.GET,"/usuarios").permitAll()
                                .requestMatchers(HttpMethod.GET,"/perfiles").permitAll()
                                .requestMatchers(HttpMethod.GET,"/topicos/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/topicos/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/respuestas/**").permitAll()
                                .requestMatchers("/v3/api-docs/**", "swagger-ui.html", "swagger-ui/**").permitAll()

                                .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
/*
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            // Público
            .requestMatchers(HttpMethod.POST, "/login").permitAll()
            .requestMatchers("/v3/api-docs/**", "swagger-ui.html", "swagger-ui/**").permitAll()

            // Controlador: UsuariosController
            .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("ADMIN", "MODERADOR")
            .requestMatchers(HttpMethod.POST, "/usuarios/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")

            // Controlador: PerfilesController
            .requestMatchers(HttpMethod.GET, "/perfiles/**").hasAnyRole("ADMIN", "MODERADOR")
            .requestMatchers(HttpMethod.POST, "/perfiles/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/perfiles/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/perfiles/**").hasRole("ADMIN")

            // Controlador: TemasController
            .requestMatchers(HttpMethod.GET, "/temas/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/temas/**").hasAnyRole("USER", "MODERADOR", "ADMIN")
            .requestMatchers(HttpMethod.PUT, "/temas/**").hasAnyRole("MODERADOR", "ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/temas/**").hasAnyRole("MODERADOR", "ADMIN")

            // Controlador: ComentariosController
            .requestMatchers(HttpMethod.GET, "/comentarios/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/comentarios/**").hasAnyRole("USER", "MODERADOR", "ADMIN")
            .requestMatchers(HttpMethod.PUT, "/comentarios/**").hasAnyRole("MODERADOR", "ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/comentarios/**").hasAnyRole("MODERADOR", "ADMIN")

            // Todo lo demás requiere autenticación
            .anyRequest().authenticated()
        )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}

//                                .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("ADMIN", "USUARIO","INVITADO")
//                                .requestMatchers(HttpMethod.POST, "/usuarios/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("ADMIN","USUARIO")
//                                .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")
//
//                                .requestMatchers(HttpMethod.GET, "/perfiles/**").hasAnyRole("ADMIN", "USUARIO","INVITADO")
//                                .requestMatchers(HttpMethod.POST, "/perfiles/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.PUT, "/perfiles/**").hasAnyRole("ADMIN","USUARIO")
//                                .requestMatchers(HttpMethod.DELETE, "/perfiles/**").hasRole("ADMIN")
//
//                                .requestMatchers(HttpMethod.GET, "/topicos/**").hasAnyRole("ADMIN", "USUARIO","INVITADO")
//                                .requestMatchers(HttpMethod.POST, "/topicos/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.PUT, "/topicos/**").hasAnyRole("ADMIN","USUARIO")
//                                .requestMatchers(HttpMethod.DELETE, "/topicos/**").hasRole("ADMIN")
//
//                                .requestMatchers(HttpMethod.GET, "/respuestas/**").hasAnyRole("ADMIN", "USUARIO","INVITADO")
//                                .requestMatchers(HttpMethod.POST, "/respuestas/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.PUT, "/respuestas/**").hasAnyRole("ADMIN","USUARIO")
//                                .requestMatchers(HttpMethod.DELETE, "/respuestas/**").hasRole("ADMIN")

*/