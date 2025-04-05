package cat.udl.eps.softarch.tfgfinder.steps;

import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.repository.ProposalRepository;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;


public class CreateInterestStepDefs {

    @Autowired
    private ProposalRepository proposalRepository;

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

}