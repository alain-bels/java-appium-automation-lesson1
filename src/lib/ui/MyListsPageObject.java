package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TMP,
            ARTICLE_BY_TITLE_TMP,
            SUBTITLE,
            SAVED_FOLDER_SEARCH_AREA,
            SAVED_ARTICLE_CELL;


    public static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TMP.replace("{FOLDER_NAME}", nameOfFolder);
    }

    public static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TMP.replace("{TITLE}", articleTitle);
    }


    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(
                folderNameXpath,
                "Cannot find folder by name " + nameOfFolder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        String articleXpath = getArticleXpathByPlatform(articleTitle);
        this.waitForElementPresent(articleXpath, "Cannot find saved article by title" + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleXpath = getArticleXpathByPlatform(articleTitle);
        this.waitForElementNotPresent(articleXpath, "Saved article still present with title" + articleTitle, 15);
    }

    private String getArticleXpathByPlatform(String articleTitle) {
        String articleXpath;
        if (Platform.getInstance().isAndroid()) {
            articleXpath = getFolderXpathByName(articleTitle);
        } else {
            articleXpath = getSavedArticleXpathByTitle(articleTitle);
        }
        return articleXpath;
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(SUBTITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }

    }

    public void waitForArticleAndClick(String articleTitle) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementAndClick(articleXpath, "Cannot find article title", 10);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);

        this.swipeElementToLeft(
                articleXpath,
                "Cannot find saved article"
        );
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void assertSavedListNotEmpty() {
        assertElementPresent(SAVED_ARTICLE_CELL, "Cannot find any saved article");
    }

    public void openSearchInFolder() {
        this.waitForElementAndClick(SAVED_FOLDER_SEARCH_AREA, "Cannot find search area", 10);
    }

}
