package cat.udl.eps.softarch.tfgfinder.steps;

import cat.udl.eps.softarch.tfgfinder.domain.*;
import cat.udl.eps.softarch.tfgfinder.repository.*;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class InviteAsProfessorDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExternalRepository externalRepository;

    private Professor professor;
    private Proposal proposal;
    private Student student1;
    private External external1;
    private Student student2;
    private External external2;

    @Given("I am logged in as a professor")
    public void i_am_logged_in_as_a_professor() {
        professor = new Professor();
        professor.setFaculty("Faculty");
        professor.setDepartment("Department1");
        professor.setName("Name");
        professor.setSurname("Surname");
        professorRepository.save(professor);
    }

}
