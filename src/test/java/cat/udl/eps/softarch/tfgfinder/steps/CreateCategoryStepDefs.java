package cat.udl.eps.softarch.tfgfinder.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cat.udl.eps.softarch.tfgfinder.domain.Category;
import cat.udl.eps.softarch.tfgfinder.repository.CategoryRepository;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CreateCategoryStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private CategoryRepository categoryRepository;


    private Category category;


    @Given("a category with name {string} and description {string}")
    public void a_category_with_name_and_description(String name, String description) {
        category = new Category();
        category.setName(name);
        category.setDescription(description);
        categoryRepository.save(category);
    }

    @When("the user creates a category with name {string}")
    public void the_user_creates_a_category_with_name(String categoryName) {
        category = new Category();
        category.setName(categoryName);
    }

    @And("description {string}")
    public void description(String description) {
        category.setDescription(description);
    }

    @Then("the category should be created successfully")
    public void the_category_should_be_created() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(category))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isCreated());


        Optional<Category> obtainedCategory = categoryRepository.findByName(category.getName());
        assertTrue("Category should exist", obtainedCategory.isPresent());
    }

    @Then("the category should not be created")
    public void the_category_should_not_be_created() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(category))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isBadRequest());

        Optional<Category> obtainedCategory = categoryRepository.findByName(category.getName());
        assertFalse("Category should not exist", obtainedCategory.isPresent());
    }


}