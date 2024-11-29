//package binhle.project.storetech.configuration;
//
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@EnableWebSecurity
//public class SecurityConfig {
//    private final String[] PUBLIC_ENDPOINT={"/auth/login",
//                            "/auth/logout",
//            "/auth",
//                            "/auth/checklogin",
//                            "/auth/register",
//                            "/home",
//                            "home/signup",
//                            "home/signin",
//                            "/users",
//                            "/products",
//                            "/",
//            "/**",
//            "assets/css/**", "assets/js/**", "assets/images/**", "assets/webjars/**", "assets/fonts/**"};
////    private final String[] PRIVATE_ENDPOINT={};
//@Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(request ->
//                request.requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINT).permitAll()
//                        .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT).permitAll()
//                        .anyRequest().authenticated());
////                request.requestMatchers("/*").permitAll()
////                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
////
////                        .loginPage("/login").loginProcessingUrl("/login")
////                        .usernameParameter("username")
////                        .passwordParameter("password")
////                        .defaultSuccessUrl("/*"));
////        httpSecurity.csrf(httpSecurityCsrfConfigurer ->
////                httpSecurityCsrfConfigurer.disable());
////        httpSecurity.oauth2ResourceServer(oauth2->
////                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));
//        return httpSecurity.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(10);
//        }
//}
