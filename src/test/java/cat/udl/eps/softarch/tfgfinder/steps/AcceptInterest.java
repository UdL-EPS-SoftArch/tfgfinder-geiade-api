package cat.udl.eps.softarch.tfgfinder.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import cat.udl.eps.softarch.tfgfinder.domain.Interest;
import cat.udl.eps.softarch.tfgfinder.domain.Interest.Status;
import cat.udl.eps.softarch.tfgfinder.repository.InterestRepository;
import io.cucumber.java.en.And;

public class AcceptInterest {
    
    @Autowired
    private InterestRepository interestRepository;
    
    @Autowired
    private StepDefs stepDefs;

    @And("^I try to accept an interest with title \"([^\"]*)\"$")
    public void acceptInterest(String interestTitle) throws Exception{
        Interest interest;
        try {
            interest = interestRepository.findByTitle(interestTitle).iterator().next();
        } catch (NoSuchElementException e) {
            interest = null;
        }
        
        if (interest == null) {
            stepDefs.result = stepDefs.mockMvc.perform(
                        patch("/interests/{id}", (interest != null) ? interest.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(interest))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        } else{
            interest.setStatus(Status.ACCEPTED);
            stepDefs.result = stepDefs.mockMvc.perform(
                        patch("/interests/{id}", (interest != null) ? interest.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(interest))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

        }
    }
}