package Tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";
        articlePageObject.addArticleToMyList(nameOfFolder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(nameOfFolder);
        myListsPageObject.swipeByArticleToDelete(articleTitle);


    }

    @Test
    public void testSaveTwoArticlesToOneList() {

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                10
        );

        String searchLine = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']" +
                        "//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                10
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options ",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String nameOfFolder = "Learning programming";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                nameOfFolder,
                "Cannot put text into articles folder input ",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot press OK button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot close article, cannot find X link ",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                10

        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']" +
                        "//*[@text = 'Island of Indonesia']"),
                "Cannot find 'Set of several computer software products and specifications' topic searching by 'Java'",
                10
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options ",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title']"),
                "Cannot find folder title by " + nameOfFolder,
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot close article, cannot find X link ",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My list ",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'Learning programming']"),
                "Cannot find created folder ",
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot find saved article"
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']" +
                        "//*[@text = 'island of Indonesia']"),
                "Cannot find article title",
                15
        );

        String titleSecondArticleinList = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/page_list_item_title"),
                "text",
                "Cannot find title of article in list",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']" +
                        "//*[@text = 'island of Indonesia']"),
                "Cannot find 'set of several computer software products and specifications' topic searching by 'Java'",
                10
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String titleSecondArticleOnViewPage = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article on view page",
                15
        );

        assertEquals(
                "Article titles are not equals",
                titleSecondArticleinList,
                titleSecondArticleOnViewPage
        );
    }
}
