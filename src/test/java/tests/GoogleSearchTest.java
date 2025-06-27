package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GoogleSearchPage;
import testdata.SearchData;
import utils.JsonDataReader;

public class GoogleSearchTest {

    WebDriver driver;
    GoogleSearchPage googlePage;
    SearchData searchData;
    @BeforeClass
    public void loadData() {
        searchData = JsonDataReader.loadSearchData();
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        googlePage = new GoogleSearchPage(driver);
        driver.manage().window().maximize();
    }
    @Test
    public void verifySuggestionsContainSearchText() {
        googlePage.openGoogle();
        googlePage.typeSearchText(searchData.getFlightSearchText());
        Assert.assertTrue(googlePage.doAllSuggestionsContainText(searchData.getFlightSearchText()),
                "Suggestions do not match search text!");
    }
    @Test
    public void testFlightSearchOutput(){
        googlePage.openGoogle();
        googlePage.typeSearchText(searchData.getFlightSearchText());
        googlePage.pressEnterKey();
        Assert.assertTrue(googlePage.isFlightTabDisplayed(), "Flights tab is not visible on the page");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
