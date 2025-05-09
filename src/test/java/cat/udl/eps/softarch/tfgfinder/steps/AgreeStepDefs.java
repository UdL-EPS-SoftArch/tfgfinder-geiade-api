package cat.udl.eps.softarch.tfgfinder.steps;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;


import cat.udl.eps.softarch.tfgfinder.domain.Agree;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.Student;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.domain.Agree.Status;
import cat.udl.eps.softarch.tfgfinder.repository.AgreeRepository;
import cat.udl.eps.softarch.tfgfinder.repository.ProposalRepository;
import cat.udl.eps.softarch.tfgfinder.repository.StudentRepository;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;

public class AgreeStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private AgreeRepository agreeRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private UserRepository userRepository;

    public static String newResourceUri;

    @When("There already is an agree with the following details:$")
    public void There_already_is_and_agre_with_the_following_details(Map<String, String> agreeDetails) throws Throwable {
        Agree agree = new Agree();
        Proposal proposal = proposalRepository.findByTitle(agreeDetails.get("proposalTitle")).get(0);
        agree.setProposal(proposal);
            if(agreeDetails.get("date") != null){
            ZonedDateTime date = ZonedDateTime.parse(agreeDetails.get("date"));
            agree.setAgreeDate(date);
        }
        String statusString = agreeDetails.get("status");
        Status status = Status.ACCEPTED;
        if(statusString == "accepted"){
            status = Status.ACCEPTED;
        }else if(statusString == "pending"){
            status = Status.PENDING;
        }else if(statusString == "rejected"){
            status = Status.REJECTED;
        }
        agree.setStatus(status);
        if(agreeDetails.get("username") != null){
            List<User> users = userRepository.findByIdContaining(agreeDetails.get("username"));
            agree.setUser(users.get(0));
        }
        agreeRepository.save(agree);
    }

    @When("I agree to an agree with the following details:$")
    public void createAgree(Map<String, String> agreeDetails) throws Exception{
        Proposal proposal = proposalRepository.findByTitle(agreeDetails.get("proposalTitle"))
                .stream().findFirst().orElse(null);
        if (proposal != null) {
            List<Agree> existingAgrees = agreeRepository.findAgreeByProposal(proposal);
            if(!existingAgrees.isEmpty()) {
                Agree existingAgree = existingAgrees.get(0);
                assertThat(existingAgree).isNotNull();
            } else {
                Agree agree = new Agree();
                    agree.setStatus(Status.ACCEPTED);
                    List<User> users = userRepository.findByIdContaining(agreeDetails.get("username"));
                    agree.setUser(users.get(0));
                    agree.setProposal(proposal);
                    agree.setAgreeDate(ZonedDateTime.parse(agreeDetails.get("date")));

                    stepDefs.result = stepDefs.mockMvc.perform(
                                    post("/agrees")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(stepDefs.mapper.writeValueAsString(agree))
                                            .characterEncoding(StandardCharsets.UTF_8)
                                            .accept(MediaType.APPLICATION_JSON)
                                            .with(AuthenticationStepDefs.authenticate()))
                            .andDo(print());
            }
        } else {
            Agree agree = new Agree();
                    agree.setStatus(Status.ACCEPTED);
                    List<User> users = userRepository.findByIdContaining(agreeDetails.get("username"));
                    agree.setUser(users.get(0));
                    agree.setProposal(proposal);
                    agree.setAgreeDate(ZonedDateTime.parse(agreeDetails.get("date")));

                    stepDefs.result = stepDefs.mockMvc.perform(
                                    post("/agrees")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(stepDefs.mapper.writeValueAsString(agree))
                                            .characterEncoding(StandardCharsets.UTF_8)
                                            .accept(MediaType.APPLICATION_JSON)
                                            .with(AuthenticationStepDefs.authenticate()))
                            .andDo(print());
        }
    }

    @And("There is only (\\d+) agree with the details:$")
    public void thereIsOnlyAgreeWithTheDeatils(int agreeCreatedNum, Map<String, String> agreeDetails) {
        assertEquals(agreeCreatedNum, agreeRepository.count());
        if(agreeCreatedNum == 1){
            Agree agree = agreeRepository.findAll().iterator().next();
            assertEquals(Status.ACCEPTED, agree.getStatus());
            String proposalTitle = agreeDetails.get("proposalTitle");
            Assert.assertEquals(agree.getProposal().getTitle(), proposalTitle);
            assertEquals(agree.getUser().getUsername(), agreeDetails.get("username"));
        }
    }

    @And("There already is a proposal by {string}")
    public void There_already_is_a_proposal(String username) {
        Proposal proposal = new Proposal();
        proposal.setTitle("Gestión Propuestas");
        proposal.setDescription("Desarrollo de una aplicación web para gestionar propuestas académicas usando Spring Boot y Angular.");
        proposal.setTiming("aaaaa");
        proposal.setSpeciality("aaaaa");
        proposal.setKind("aaaaa");
        proposal.setUser(userRepository.findUserById(username));
        proposalRepository.save(proposal);
    }
}
