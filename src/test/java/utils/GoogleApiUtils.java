package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GoogleApiUtils {

    public static Response getSearchSuggestions(String query) {
        return RestAssured.given()
                .queryParam("q", query)
                .queryParam("client", "firefox")
                .get("https://www.google.com/complete/search");
    }
}
