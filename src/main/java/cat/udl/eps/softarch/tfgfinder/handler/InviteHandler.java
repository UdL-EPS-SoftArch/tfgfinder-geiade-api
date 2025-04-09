package cat.udl.eps.softarch.tfgfinder.handler;

import cat.udl.eps.softarch.tfgfinder.domain.*;
import cat.udl.eps.softarch.tfgfinder.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class InviteHandler {
    final Logger logger = LoggerFactory.getLogger(User.class);

    final UserRepository userRepository;

    final InviteRepository inviteRepository;

    final StudentRepository studentRepository;

    final ProfessorRepository professorRepository;

    final ExternalRepository externalRepository;

    public InviteHandler(UserRepository userRepository, InviteRepository inviteRepository, StudentRepository studentRepository, ProfessorRepository professorRepository, ExternalRepository externalRepository) {
        this.userRepository = userRepository;
        this.inviteRepository = inviteRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.externalRepository = externalRepository;
    }

    @HandleBeforeCreate
    public void handleInvitePreCreate(Invite invite) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(owner.getAuthorities().toString().equals(invite.getWho().getAuthorities().toString())) {
            logger.error("A user cannot invite another user with the same role.");
            throw new IllegalArgumentException("A user cannot invite another user with the same role.");
        }
        logger.info("Before creating: {}", invite.toString());
    }

}
