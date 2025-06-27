package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchPage {

    WebDriver driver;
    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By searchBox = By.name("q");
    private By weatherWidget = By.id("wob_wc");
    private By locationText = By.id("wob_loc");
    private By flightSection = By.xpath("//*[contains(text(), 'Flights')]");
    private By suggestionListItems = By.cssSelector("ul[role='listbox'] li");
    private By flightsTab = By.xpath("//span[@jsname='AznF2e' and contains(., 'Flights')]");


    // Actions
    public void openGoogle() {
        driver.get("https://www.google.com");
    }

    // Just types the search term
    public void typeSearchText(String text) {
        driver.findElement(searchBox).sendKeys(text);
    }

    // Presses Enter key
    public void pressEnterKey() {
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }

    public boolean doAllSuggestionsContainText(String expectedText) {
        List<WebElement> suggestions = driver.findElements(suggestionListItems);
        if (suggestions.isEmpty()) {
            System.out.println("No suggestions found.");
            return false;
        }

        for (WebElement suggestion : suggestions) {
            String suggestionText = suggestion.getText();
            System.out.println("Suggestion: " + suggestionText); // Optional: print for debug

            if (!suggestionText.toLowerCase().contains(expectedText.toLowerCase())) {
                System.out.println("Mismatch found: " + suggestionText);
                return false;
            }
        }
        return true;
    }
    public boolean isFlightTabDisplayed() {
        return driver.findElements(flightsTab).size() > 0 && driver.findElement(flightsTab).isDisplayed();
    }




}
