package cat.udl.eps.softarch.tfgfinder.steps;

import java.util.Optional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.repository.ProposalRepository;
import cat.udl.eps.softarch.tfgfinder.domain.Interest;
import cat.udl.eps.softarch.tfgfinder.repository.InterestRepository;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.junit.Assert;


public class CreateInterestStepDefs {

    
    public static String currentUsername;

    
    @Autowired
    private StepDefs stepDefs;

    
    @Autowired
    private ProposalRepository proposalRepository;


    @Autowired 
    private InterestRepository interestRepository;


    @Autowired
    private UserRepository userRepository;


    @Given("^There is a proposal with id \"([^\"]*)\"$")
    public void thereIsAProposalWithId(long id) 
    {
        if (!proposalRepository.existsById(id)) 
        {
            Proposal proposal = new Proposal();

            proposal.setTitle("title of the project");
            proposal.setDescription("description: This is a test description, only for testing.");
            proposal.setTiming("timing");
            proposal.setSpeciality("speciality");
            proposal.setKind("kind of the project");

            proposalRepository.save(proposal);
        }
    }


    @And("^There is a user with user \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void thereIsAUserWithUserAndPassword(String username, String password)
    {
        if (!userRepository.existsById(username)) 
        {
            User user = new User();

            user.setId(username);
            user.setEmail(username + "@sample.app");
            user.setPassword(password);
            user.encodePassword();

            userRepository.save(user);

            this.currentUsername = username;
        }
    }


    @Given("^Don't exist Interest with user \"([^\"]*)\" and id \"([^\"]*)\"$")
    public void DontExistInterestWithUserAndId(String user, long id)
    {
        Optional<Interest> optionalInterest = interestRepository.findById(id);

        if (optionalInterest.isPresent()) 
        {

            Interest interest = optionalInterest.get();

            if (interest.getUser().getUsername().equals(user)) 
            {
                interestRepository.delete(interest);
            }
        }
    }

    @And("^There is ([^\"]*) Interest created with user \"([^\"]*)\" and proposal id \"([^\"]*)\"$")
    public void ThereIs1InterestCreatedWithUserAndProposalId(int num, String user, long proposalId)
    {
        if (num == 0)
        {
            Optional<Proposal> optionalProposal = proposalRepository.findById(proposalId);

            if (optionalProposal.isPresent())
            {
                Assert.assertTrue(false);
            }

            List<Interest> interests = interestRepository.findByProposal(optionalProposal.get());

            Assert.assertEquals(0, interests.stream()
                .anyMatch(interest -> interest.getUser().getUsername().equals(user)));
        }

        if (num > 0)
        {
            Optional<Proposal> optionalProposal = proposalRepository.findById(proposalId);

            if (!optionalProposal.isPresent())
            {
                Assert.assertTrue(false);
            }

            List<Interest> interests = interestRepository.findByProposal(optionalProposal.get());

            Assert.assertEquals(num, interests.stream()
                .anyMatch(interest -> interest.getUser().getUsername().equals(user)));
        }
    }

    @Then("^I show interest to proposal id \"([^\"]*)\"$")
    public void IShowInterestToProposalId(long id) throws Exception
    {
        Interest interest = new Interest();

        interest.setId(id);

        Optional<User> optionalUser = userRepository.findById(currentUsername);
        if (optionalUser.isPresent()) 
        {
            interest.setUser(optionalUser.get());
        }

        String body = "{ \"proposalId\": " + id + " }";

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/interests")
                        .session(stepDefs.session)
                        .with(AuthenticationStepDefs.authenticate())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        ).andDo(print());
    }

}