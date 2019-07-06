package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TMP,
            ARTICLE_BY_TITLE_TMP,
            SUBTITLE,
            SAVED_FOLDER_SEARCH_AREA,
            SAVED_ARTICLE_CELL,
            REMOVE_FROM_SAVED_BUTTON;


    public static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TMP.replace("{FOLDER_NAME}", nameOfFolder);
    }

    public static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TMP.replace("{TITLE}", articleTitle);
    }

    public static String getRemoveButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }


    public MyListsPageObject(RemoteWebDriver driver) {
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

    public void waitForArticleAndClick(String remove_locator, String articleTitle, int i) {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementAndClick(articleXpath, "Cannot find article title", 10);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    articleXpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(articleTitle);
            sleep();
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }


        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleXpath, "Cannot find saved article");
        }
        if (Platform.getInstance().isMw()) {
            sleep();
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void assertSavedListNotEmpty() {
        assertElementPresent(SAVED_ARTICLE_CELL, "Cannot find any saved article");
    }

    public void openSearchInFolder() {
        this.waitForElementAndClick(SAVED_FOLDER_SEARCH_AREA, "Cannot find search area", 10);
    }

}
