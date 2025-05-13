package cat.udl.eps.softarch.tfgfinder.config;
import cat.udl.eps.softarch.tfgfinder.domain.Interest;
import cat.udl.eps.softarch.tfgfinder.domain.Invite;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.InterestRepository;
import cat.udl.eps.softarch.tfgfinder.repository.InviteRepository;
import cat.udl.eps.softarch.tfgfinder.repository.ProposalRepository;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

import java.time.ZonedDateTime;
import java.util.Arrays;

@Configuration
public class DBInitialization {
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    private final UserRepository userRepository;
    private final InviteRepository inviteRepository;
    private final ProposalRepository proposalRepository;

    public DBInitialization(UserRepository userRepository, InviteRepository inviteRepository, ProposalRepository proposalRepository) {
        this.userRepository = userRepository;
        this.inviteRepository = inviteRepository;
        this.proposalRepository = proposalRepository;
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
        // Second user for invite
        if (!userRepository.existsById("demo2")) {
            User user = new User();
            user.setEmail("demo2@sample.app");
            user.setId("demo2");
            user.setPassword(defaultPassword);
            user.encodePassword();
            userRepository.save(user);
        }
        //Default proposal
        if (!proposalRepository.existsByTitle("Proposal Title")) {
            Proposal proposal = new Proposal();
            proposal.setTitle("Proposal Title");
            proposal.setDescription("Proposal Description Proposal Description Proposal Description Proposal");
            proposal.setTiming("timing");
            proposal.setSpeciality("speciality");
            proposal.setKind("kind kind");
            proposal.setUser(userRepository.findUserById("demo"));
            proposalRepository.save(proposal);
        }
        //Default invite
        if (!inviteRepository.existsByWho(userRepository.findUserById("demo2"))) {
            Invite invite = new Invite();
            invite.setInviteDate(ZonedDateTime.now());
            invite.setStatus("PENDING");
            invite.setWho(userRepository.findUserById("demo2"));
            invite.setWhat(proposalRepository.findByTitle("Proposal Title").get(0));
            inviteRepository.save(invite);

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
    }
}
