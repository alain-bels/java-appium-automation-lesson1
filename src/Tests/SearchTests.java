package Tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }


    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testPresentSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                10
        );
        WebElement searchField = searchPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        String searchText = searchField.getAttribute("text");

        assertEquals(
                "Cannot find \"Search…\" text",
                "Search…",
                searchText
        );
    }

    @Test
    public void testCancelSearchAndCheckResult() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                10
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Word",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']" +
                        "//*[@index = 1]"),
                "Cannot find second item",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Search result item is still present on the page",
                5
        );
    }

    @Test
    public void testCheckWordsInResultsOfSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                10
        );

        searchPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Word",
                "Cannot find search input",
                5
        );

        searchPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Cannot find search field",
                5
        );

        List<WebElement> headers = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        headers.forEach(webElement ->
                assertTrue("Cannot find \"Word\" in title",
                        webElement.getAttribute("text")
                                .toLowerCase()
                                .contains("Word".toLowerCase())
                )
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        String searchLine = "Linkin Park Discography";
        searchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found too few results!",
                amountOfSearchResults > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        String searchLine = "zxcgsgsu";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();


    }

}
