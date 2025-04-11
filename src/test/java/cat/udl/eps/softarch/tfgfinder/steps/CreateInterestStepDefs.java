package cat.udl.eps.softarch.tfgfinder.steps;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import org.springframework.test.web.servlet.request.RequestPostProcessor;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;

import cat.udl.eps.softarch.tfgfinder.domain.Interest;
import cat.udl.eps.softarch.tfgfinder.domain.Interest.Status;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.Student;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.InterestRepository;
import cat.udl.eps.softarch.tfgfinder.repository.ProposalRepository;
import cat.udl.eps.softarch.tfgfinder.repository.StudentRepository;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;


public class CreateInterestStepDefs {

    
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private UserRepository userRepository;

    @Given("^There is a proposal created$")
    public void createdProposal(){
        Proposal proposal = new Proposal();
        proposal.setTitle("proposalTitle");
        proposal.setDescription("Desarrollo de una aplicación web para gestionar propuestas académicas usando Spring Boot y Angular.");
        proposal.setTiming("aaaaa");
        proposal.setSpeciality("aaaaa");
        proposal.setKind("aaaaa");
        proposalRepository.save(proposal);
    }

    @When("There already is an interest with the following details:$")
    public void There_already_is_an_interest_with_the_following_details(Map<String, String> interestDetails) {
        Interest interest = new Interest();
        Proposal proposal = proposalRepository.findByTitle(interestDetails.get("proposalTitle")).orElse(null);
        interest.setProposal(proposal);
            if(interestDetails.get("date") != null){
            ZonedDateTime date = ZonedDateTime.parse(interestDetails.get("date"));
            interest.setCreatedDate(date);;
        }
        String statusString = interestDetails.get("status");
        Status status = null;
        if(statusString.equals("accepted")){
            status = Status.ACCEPTED;
        }else if(statusString.equals("pending")){
            status = Status.PENDING;
        }else if(statusString.equals("rejected")){
            status = Status.REJECTED;
        }
        interest.setStatus(status);
        if(interestDetails.get("username") != null){
            List<User> users = userRepository.findByIdContaining(interestDetails.get("username"));
            interest.setUser(users.get(0));
        }
        interestRepository.save(interest);
    }

    @When("I try to create an interest with the following details:$")
    public void I_try_to_create_an_interest_with_the_following_details(Map<String, String> interestDetails) throws Exception {
        Proposal proposal = proposalRepository.findByTitle(interestDetails.get("proposalTitle")).orElse(null);
        if (proposal != null) {
            List<Interest> existingInterests = interestRepository.findByProposal(proposal);
            if(existingInterests.size()>0){
                Interest existingInterest = existingInterests.get(0);
                assertThat(existingInterest).isNotNull();
            }else{
                Interest interest = new Interest();
                String statusString = interestDetails.get("status");
                Status status = null;
                if(statusString.equals("accepted")){
                    status = Status.ACCEPTED;
                }else if(statusString.equals("pending")){
                    status = Status.PENDING;
                }else if(statusString.equals("rejected")){
                    status = Status.REJECTED;
                }
                interest.setStatus(status);
                List<User> users = userRepository.findByIdContaining(interestDetails.get("username"));
                interest.setUser(users.get(0));
                interest.setProposal(proposal);
                ZonedDateTime date = ZonedDateTime.parse(interestDetails.get("date"));
                interest.setCreatedDate(date);
                System.out.println(interest.getUser().toString());
                stepDefs.result = stepDefs.mockMvc.perform(
                                post("/interests")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(stepDefs.mapper.writeValueAsString(interest))
                                        .characterEncoding(StandardCharsets.UTF_8)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .with(AuthenticationStepDefs.authenticate()))
                        .andDo(print());
            }
        }
    }

    @And("There is only (\\d+) interest with the details:$")
    public void There_is_only_interest_with_the_details(int interestCreatedNum, Map<String, String> interestDetails) {
        assertEquals(interestCreatedNum, interestRepository.count());
        if(interestCreatedNum == 1){
            Interest interest = interestRepository.findAll().iterator().next();
            String statusString = interestDetails.get("status");
                Status status = null;
                if(statusString.equals("accepted")){
                    status = Status.ACCEPTED;
                }else if(statusString.equals("pending")){
                    status = Status.PENDING;
                }else if(statusString.equals("rejected")){
                    status = Status.REJECTED;
                }
            assertEquals(interest.getStatus(), status);
            String title = interestDetails.get("proposalTitle");
            Assert.assertEquals(interest.getProposal().getTitle(), title);
            assertEquals(interest.getUser().getUsername(), interestDetails.get("username"));
        }
    }
}