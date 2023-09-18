package com.br.ages.orientacaobucalbackend.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesStep extends CucumberStep {

    private List<String> response;
    private List<String> categories;

    @Given("I have a list of categories")
    public void iHaveAListOfCategories() {
        categories = List.of("gengiva", "labios", "dente", "lingua", "garganta", "todos");
    }

    @When("I list the categories")
    public void listCategories() {
        response = new ArrayList<>(categories);
    }

    @When("I filter the list by type {string}")
    public void iFilterTheListByType(String filterCategory) {
        this.response = this.categories.stream().filter(x -> x.equals(filterCategory))
                .collect(Collectors.toList());
    }

    @Then("I should only see the filtered category")
    public void iShouldSeeTheFollowingCategory() {
        assert this.response.size() == 1;
    }

    @Then("I should see the (.*) categories")
    public void viewCategories(List<String> expectedCategories) {
        for (String expectedCategory : expectedCategories) {
            assert this.response.contains(expectedCategory);
        }
    }

}
