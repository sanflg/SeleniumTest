package org.seleniumtest.api.pokeapi;

import io.qameta.allure.Step;
import org.seleniumtest.api.BaseApi;
import org.seleniumtest.api.pokeapi.models.Ability;
import static io.restassured.RestAssured.*;

public class PokeApi implements BaseApi<Ability> {
    public static final String ENDPOINT = "https://pokeapi.co/api/v2/ability";

    @Step ("Get ability with id: {0}")
    public static void getAbility (int id){
        given().when().get(ENDPOINT + "/" + id).then().log()
                .all();
    }
}
