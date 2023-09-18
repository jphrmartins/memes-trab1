package com.br.ages.orientacaobucalbackend.steps;

import com.br.ages.orientacaobucalbackend.Controllers.ContentController;
import com.br.ages.orientacaobucalbackend.DataAcess.Repository.ContentRepository;
import com.br.ages.orientacaobucalbackend.Entity.Category;
import com.br.ages.orientacaobucalbackend.Entity.Content;
import com.br.ages.orientacaobucalbackend.Services.CategoryService;
import com.br.ages.orientacaobucalbackend.Services.ContentService;
import com.br.ages.orientacaobucalbackend.Services.RecommendedSourceService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.stream.Collectors;

public class ContentsStep extends CucumberStep {

    @Autowired
    WebTestClient client;
    private String endpoint;
    private List<Content> response;

    @MockBean
    private ContentService contentService  = Mockito.mock(ContentService.class);


    @Given("I am at the categories page")
    public void setCategoryService() {
        this.endpoint = "api/content";

    }

    @When("I click on the \"todos\" button")
    public void clickOnTodosButton() {
        Category category = new Category();
        category.setName("gengiva");
        Content content = new Content();
        content.setCategories(List.of(category));

        Mockito.when(this.contentService.getAllContents()).thenReturn(List.of(new Content(), new Content(), content));
        this.response = this.client
                .get()
                .uri(this.endpoint)
                .exchange()
                .returnResult(new ParameterizedTypeReference<List<Content>>() {
                }).getResponseBody().blockFirst();

    }

    @Then("I should see all Contents")
    public void seeAllContents() {
        assert !this.response.isEmpty();
    }

    @And("I use the search bar to filter by \"gengiva\"")
    public void filterByGengiva() {
        Category category = new Category();
        category.setName("gengiva");

        this.response = this.response.stream()
                .filter(x -> x.getCategories().contains(category))
                .collect(Collectors.toList());
    }

    @Then("I should see filtered contents by \"gengiva\" category")
    public void seeGengivaContent() {
        assert this.response.size() == 1;
    }
}
