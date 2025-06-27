package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.GoogleApiUtils;

import java.io.IOException;
import java.util.List;

public class GoogleApiTest {

    @Test
    public void testSearchSuggestionsContainExpectedText() throws IOException {
        String query = "flights from cairo to marsa alam";

        Response response = GoogleApiUtils.getSearchSuggestions(query);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        String rawBody = response.getBody().asString();
        ObjectMapper mapper = new ObjectMapper();
        List<Object> rootList = mapper.readValue(rawBody, List.class);

        List<String> suggestions = (List<String>) rootList.get(1);

        boolean allMatch = suggestions.stream()
                .map(String::toLowerCase)
                .allMatch(s -> s.contains("cairo") && s.contains("marsa"));

        Assert.assertTrue(allMatch, "Not all suggestions contain 'cairo' and 'marsa'");

        suggestions.forEach(s -> System.out.println("Suggestion: " + s));
    }
}
