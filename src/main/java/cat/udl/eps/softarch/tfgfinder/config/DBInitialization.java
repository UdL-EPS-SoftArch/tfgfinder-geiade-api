package cat.udl.eps.softarch.tfgfinder.config;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import cat.udl.eps.softarch.tfgfinder.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;


@Configuration
public class DBInitialization {
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;


    public DBInitialization(UserRepository userRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        // Default user
        if (!userRepository.existsById("demo")) {
            User user = new User();
            user.setEmail("demo@sample.app");
            user.setId("demo");
            user.setPassword(defaultPassword);
            user.encodePassword();
            userRepository.save(user);
        }
        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!userRepository.existsById("test")) {
                User user = new User();
                user.setEmail("test@sample.app");
                user.setId("test");
                user.setPassword(defaultPassword);
                user.encodePassword();
                userRepository.save(user);
            }
        }
        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!studentRepository.existsById("student1")) {
                User student1 = new User();
                student1.setEmail("student1@sample.app");
                student1.setId("student1");
                student1.setPassword(defaultPassword);
                student1.encodePassword();
                studentRepository.save(student1);
            }
        }
        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!userRepository.existsById("student2")) {
                User student2 = new User();
                student2.setEmail("student2@sample.app");
                student2.setId("student2");
                student2.setPassword(defaultPassword);
                student2.encodePassword();
                userRepository.save(student2);
            }
        }
        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!userRepository.existsById("professor1")) {
                User professor1 = new User();
                professor1.setEmail("professor1@sample.app");
                professor1.setId("professor1");
                professor1.setPassword(defaultPassword);
                professor1.encodePassword();
                userRepository.save(professor1);
            }
        }
        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!userRepository.existsById("external1")) {
                User external1 = new User();
                external1.setEmail("external1@sample.app");
                external1.setId("external1");
                external1.setPassword(defaultPassword);
                external1.encodePassword();
                userRepository.save(external1);
            }
        }
    }
}
