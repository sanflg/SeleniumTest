package org.seleniumtest.cucumber.api.pokeapi;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.seleniumtest.api.pokeapi.PokeApi;
import org.seleniumtest.api.pokeapi.models.Ability;

public class PokeApiSD {
    private Ability ability;

    public PokeApiSD(){

    }

    @When("search ability by id: {}")
    public void searchAbilityById(int id) {
        PokeApi.getAbility(id);
    }

    @Then("status code is {}")
    public void statusCodeIs(String statusCode) {

    }

    @Then("ability name is {}")
    public void abilityNameIs(String abilityName) {

    }
}
