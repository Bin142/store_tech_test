//package binhle.project.storetech.configuration;
//
//import binhle.project.storetech.entity.impo.User;
//import binhle.project.storetech.enums.Role;
//import binhle.project.storetech.repository.UserRepository;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.HashSet;
//
//@Configuration
////@RequiredArgsConstructor
////@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class ApplicationInitConfig {
//    private static final Logger log = LoggerFactory.getLogger(ApplicationInitConfig.class);
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
////    @Bean
////    ApplicationRunner applicationRunne(UserRepository userRepository) {
////        return arg -> {
////            if (userRepository.findByUsername("admin").isEmpty()) {
////                var roles = new HashSet<String>();
////                roles.add(Role.ADMIN.name());
////                User user = new User();
////                user.setUsername("admin");
////                user.setPassword(passwordEncoder.encode("admin"));
////                user.setRoles(roles);
////                userRepository.save(user);
////                log.warn("admin user has been created with dèault password: admin, please change it");
////            }
////        };
////    }
//}
