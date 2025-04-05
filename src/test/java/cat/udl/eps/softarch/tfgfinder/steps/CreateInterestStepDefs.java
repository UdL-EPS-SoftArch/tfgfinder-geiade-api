package cat.udl.eps.softarch.tfgfinder.steps;

import java.util.Optional;

import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.repository.ProposalRepository;
import cat.udl.eps.softarch.tfgfinder.domain.Interest;
import cat.udl.eps.softarch.tfgfinder.repository.InterestRepository;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;


public class CreateInterestStepDefs {

    @Autowired
    private ProposalRepository proposalRepository;


    @Autowired 
    InterestRepository interestRepository;


    @Given("^There is a proposal with id \"([^\"]*)\"$")
    public void thereIsAProposalWithId(long id) 
    {
        if (!proposalRepository.existsById(id)) 
        {
            Proposal proposal = new Proposal();
            proposal.setId(id);
            proposalRepository.save(proposal);
        }
    }


    @Given("^Don't exist Interest with user \"([^\"]*)\" and id \"([^\"]*)\"$")
    public void DontExistInterestWithUserAndId(String user, long id)
    {
        Optional<Interest> optionalInterest = interestRepository.findById(id);

        if (optionalInterest.isPresent()) {
            
            Interest interest = optionalInterest.get();

            if (interest.getUser().getUsername().equals(user)) {
                interestRepository.delete(interest);
            }
        }
    }

}