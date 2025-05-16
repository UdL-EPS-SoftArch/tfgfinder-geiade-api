package cat.udl.eps.softarch.tfgfinder.steps;

import cat.udl.eps.softarch.tfgfinder.domain.*;
import cat.udl.eps.softarch.tfgfinder.repository.*;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InviteUserStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ExternalRepository externalRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private InviteRepository inviteRepository;

    @Given("{string} is a Student with name {string}, surname {string}, DNI {string}, address {string}, municipality {string}, postalCode {string}, phoneNumber {string} and degree {string} and email {string} and password {string}")
    public void isAStudentWithNameSurnameDNIAddressMunicipalityPostalCodePhoneNumberAndDegree(String username, String name, String surname, String dni, String address, String municipality, String postalCode, String phoneNumber, String degree, String email, String password) {
        if (studentRepository.findByNameContaining(name).isEmpty()) {
            Student student = new Student();
            student.setName(name);
            student.setUsername(username);
            student.setSurname(surname);
            student.setDni(dni);
            student.setAddress(address);
            student.setMunicipality(municipality);
            student.setPostalCode(postalCode);
            student.setPhoneNumber(phoneNumber);
            student.setDegree(degree);
            student.setEmail(email);
            student.setPassword(password);
            student.encodePassword();
            studentRepository.save(student);
        }
    }

    @Given("{string} is a Professor with name {string} and surname {string} of faculty {string} and department {string} and email {string} and password {string}")
    public void isAProfessorWithNameAndSurnameOfFacultyAndDepartment(String username, String name, String surname, String faculty, String department, String email, String password) {
        if (professorRepository.findByDepartmentContaining(department).isEmpty()) {
            Professor professor = new Professor();
            professor.setUsername(username);
            professor.setName(name);
            professor.setSurname(surname);
            professor.setFaculty(faculty);
            professor.setDepartment(department);
            professor.setEmail(email);
            professor.setPassword(password);
            professor.encodePassword();
            professorRepository.save(professor);
        }
    }

    @Given("{string} is an External with name {string}, surname {string}, position {string}, organization {string}, address {string}, municipality {string}, postalCode {string}, phoneNumber {string} and email {string} and password {string}")
    public void isAnExternalWithNameSurnamePositionOrganizationAddressMunicipalityPostalCodePhoneNumber(String username, String name, String surname, String position, String organization, String address, String municipality, String postalCode, String phoneNumber, String email, String password) {
        if (externalRepository.findByOrganizationContaining(organization).isEmpty()) {
            External external = new External();
            external.setUsername(username);
            external.setName(name);
            external.setSurname(surname);
            external.setPosition(position);
            external.setOrganization(organization);
            external.setAddress(address);
            external.setMunicipality(municipality);
            external.setPostalCode(postalCode);
            external.setPhoneNumber(phoneNumber);
            external.setEmail(email);
            external.setPassword(password);
            external.encodePassword();
            externalRepository.save(external);
        }
    }

    @And("There is a proposal by {string} titled {string} with description {string} and timing {string} and specialty {string} and kind {string}")
    public void thereIsAProposalTitledWithDescriptionAndTimingAndSpecialtyAndKind(String username, String title, String description, String timing, String specialty, String kind) {
        if (proposalRepository.findByTitle(title).isEmpty()) {
            Proposal proposal = new Proposal();
            proposal.setTitle(title);
            proposal.setDescription(description);
            proposal.setTiming(timing);
            proposal.setSpeciality(specialty);
            proposal.setKind(kind);
            proposal.setUser(userRepository.findUserById(username));
            proposalRepository.save(proposal);
        }
    }

    @When("{string} creates an invite to user {string} for proposal {string} with status {string} and date {string}")
    public void createsAnInviteToUserForProposalWithStatusAndDate(String ownUsername, String whoUsername, String whatTitle, String status, String dateString) throws Exception {
        Proposal proposal = proposalRepository.findByTitle(whatTitle).stream().findFirst().orElse(null);
        ZonedDateTime date = ZonedDateTime.parse(dateString);
        User who = userRepository.findUserById(whoUsername);

        Invite invite = new Invite();
        invite.setWho(who);
        invite.setWhat(proposal);
        invite.setStatus(status);
        invite.setInviteDate(date);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/invites")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(invite))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Then("The invite to user {string} of proposal {string} is created with status {string}")
    public void theInviteToUserOfProposalShouldBeCreatedWithStatus(String whoUsername, String whatTitle, String status) throws Exception {
        User who = userRepository.findUserById(whoUsername);
        Proposal what = proposalRepository.findByTitle(whatTitle).stream().findFirst().orElse(null);
        Invite invite = inviteRepository.findByWhoAndWhat(who, what);

        assertEquals(status, invite.getStatus());

        stepDefs.result = stepDefs.mockMvc.perform(
                get("/invites/{id}", invite.getId())
                        .param("whoUsername", whoUsername)
                        .param("proposalTitle", whatTitle)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        )
                .andDo(print())
                .andExpect(jsonPath("$.status").value(invite.getStatus()));
    }

    @Then("The invite to {string} for {string} will not be created")
    public void theInviteToForWillNotBeCreated(String whoUsername, String whatTitle) {
        User who = userRepository.findUserById(whoUsername);
        Proposal what = proposalRepository.findByTitle(whatTitle).stream().findFirst().orElse(null);
        Invite invite = inviteRepository.findByWhoAndWhat(who, what);

        Assertions.assertNull(invite);
    }

    @When("{string} creates an invite for proposal {string} with status {string} and date {string}")
    public void createsAnInviteForProposalWithStatusAndDate(String ownUsername, String whatTitle, String status, String dateString) throws Exception {
        Proposal proposal = proposalRepository.findByTitle(whatTitle).stream().findFirst().orElse(null);
        ZonedDateTime date = ZonedDateTime.parse(dateString);

        Invite invite = new Invite();
        invite.setWhat(proposal);
        invite.setStatus(status);
        invite.setInviteDate(date);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/invites")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(invite))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("{string}creates an invite to user {string} with status {string} and date {string}")
    public void createsAnInviteToUserWithStatusAndDate(String ownUsername, String whoUsername, String status, String dateString) throws Exception {
        ZonedDateTime date = ZonedDateTime.parse(dateString);
        User who = userRepository.findUserById(whoUsername);

        Invite invite = new Invite();
        invite.setWho(who);
        invite.setStatus(status);
        invite.setInviteDate(date);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/invites")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(invite))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("{string} creates an invite to user {string} for proposal {string} with date {string}")
    public void createsAnInviteToUserForProposalWithDate(String ownUsername, String whoUsername, String whatTitle, String dateString) throws Exception {
        Proposal proposal = proposalRepository.findByTitle(whatTitle).stream().findFirst().orElse(null);
        ZonedDateTime date = ZonedDateTime.parse(dateString);
        User who = userRepository.findUserById(whoUsername);

        Invite invite = new Invite();
        invite.setWho(who);
        invite.setWhat(proposal);
        invite.setInviteDate(date);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/invites")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(invite))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("{string} creates an invite to user {string} for proposal {string} with status {string}")
    public void createsAnInviteToUserForProposalWithStatus(String ownUsername, String whoUsername, String whatTitle, String status) throws Exception {
        Proposal proposal = proposalRepository.findByTitle(whatTitle).stream().findFirst().orElse(null);
        User who = userRepository.findUserById(whoUsername);

        Invite invite = new Invite();
        invite.setWho(who);
        invite.setWhat(proposal);
        invite.setStatus(status);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/invites")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(invite))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("{string} creates an invite without specifying who, what, status or date")
    public void createsAnInviteWithoutSpecifyingWhoWhatStatusOrDate(String ownUsername) throws Exception {
        Invite invite = new Invite();
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/invites")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(invite))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
