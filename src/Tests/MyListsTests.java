package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyListsTests extends CoreTestCase {

    private static final String nameOfFolder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() throws Exception {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.createNewMyListAndAddArticle(nameOfFolder);

        } else {
            articlePageObject.addArticlesToMySaved();

        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();
        navigationUI.closePopup();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        }
        Thread.sleep(5000);

        myListsPageObject.swipeByArticleToDelete(articleTitle);
    }


    @Test
    public void testSaveTwoArticlesToOneList() throws Exception {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.createNewMyListAndAddArticle(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }
        Thread.sleep(3000);
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        if (Platform.getInstance().isAndroid()) {
            searchPageObject.typeSearchLine("Java");
        }
        searchPageObject.clickByArticleWithSubstring("Island of Indonesia");

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        ArticlePageObject secondArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            secondArticlePageObject.addArticleToExistingMyList(nameOfFolder);
            myListsPageObject.openFolderByName(nameOfFolder);
        } else {
            secondArticlePageObject.addArticlesToMySaved();
        }
        Thread.sleep(3000);
        secondArticlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            Thread.sleep(3000);
            myListsPageObject.openFolderByName(nameOfFolder);
        } else {
            navigationUI.closePopup();
        }

        myListsPageObject.swipeByArticleToDelete(articleTitle);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openSearchInFolder();
            searchPageObject.typeSearchLineInSaved("Java");
        } else {
            searchPageObject.typeSearchLineInSaved("Island of Indonesia");
        }
        myListsPageObject.assertSavedListNotEmpty();
    }
}