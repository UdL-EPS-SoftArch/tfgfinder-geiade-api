package cat.udl.eps.softarch.tfgfinder.steps;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.springframework.test.web.servlet.request.RequestPostProcessor;

import cat.udl.eps.softarch.tfgfinder.domain.Agree;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.Student;
import cat.udl.eps.softarch.tfgfinder.domain.Agree.Status;
import cat.udl.eps.softarch.tfgfinder.repository.AgreeRepository;
import cat.udl.eps.softarch.tfgfinder.repository.ProposalRepository;
import cat.udl.eps.softarch.tfgfinder.repository.StudentRepository;

public class AgreeStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private AgreeRepository agreeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProposalRepository proposalRepository;
    
    private Student student;
    private Proposal proposal;

    @Given("^There is an authenticated student with id \"([^\"]*)\"$")
    public void thereIsAnAuthenticatedStudent(String studentId) {
        student = studentRepository.findById(studentId).orElse(null);
    }

    @When("he agrees to a proposal with id \"([^\"]*)\"$")
    public void agreeToAProposal(Long proposalId) {
        proposal = proposalRepository.findById(proposalId).orElse(null);
    }

    @Then("the agree with id \"([^\"]*)\" is created with status ACCEPTED$")
    public void createAgree(Long agreeId) {
        Agree agree = new Agree();
        agree.setStatus(Status.ACCEPTED);
        agree.setUser(student);
        agree.setProposal(proposal);
        agreeRepository.save(agree);
    }

    @Then("the student with id \"([^\"]*)\" and the proposal with id \"([^\"]*)\" are linked to the agree with id \"([^\"]*)\"$")
    public void setStudentAndProposalOnAgree(String studentId, Long proposalId, Long agreeId) {
        boolean exists = agreeRepository.existsById(agreeId);
        assertEquals(exists, true);
        Agree agree = agreeRepository.findById(agreeId).orElse(null);
        assertEquals(agree.getProposal().getId(), proposalId);
        assertEquals(agree.getUser().getId(), studentId);
    }

    @Then("the status is set to accepted")
    public void the_status_is_set_to_accepted() {
        // Write code here that turns the phrase above into concrete actions
    }
}
