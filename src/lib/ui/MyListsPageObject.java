package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String
    FOLDER_BY_NAME_TMP = "//*[@text = '{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TMP = "//*[@text = '{TITLE}']";

    public static String getFolderXpathByName(String nameOfFolder)
    {
        return FOLDER_BY_NAME_TMP.replace("{FOLDER_NAME}",nameOfFolder);
    }
    public static String getSavedArticleXpathByTitle(String articleTitle)
    {
        return ARTICLE_BY_TITLE_TMP.replace("{TITLE}",articleTitle);
    }


    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder)
    {
        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementAndClick(
                By.xpath(folderNameXpath),
                "Cannot find folder by name " + nameOfFolder,
                5
        );
    }


    public void waitForArticleToAppearByTitle(String articleTitle)
    {
        String articleXpath = getFolderXpathByName(articleTitle);
        this.waitForElementPresent(By.xpath(articleXpath), "Cannot find saved article by title" + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle)
    {
        String articleXpath = getFolderXpathByName(articleTitle);
        this.waitForElementNotPresent(By.xpath(articleXpath), "Saved article still present with title" + articleTitle, 15);
    }

    public void waitForArticleAndClick(String articleTitle)
    {
        String articleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementAndClick(By.xpath(articleXpath), "Cannot find article title", 10);
    }

    public void swipeByArticleToDelete(String articleTitle)
    {

        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getFolderXpathByName(articleTitle);

        this.swipeElementToLeft(
                By.xpath(articleXpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

}
