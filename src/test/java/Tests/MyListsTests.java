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

import javax.swing.plaf.metal.MetalBorders;
import java.util.List;

public class MyListsTests extends CoreTestCase {

    private static final String nameOfFolder = "Learning programming";
    private static final String
            login = "alainbels",
            password = "TrT135TrT#$";

    @Test
    public void testSaveFirstArticleToMyList() throws Exception {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.createNewMyListAndAddArticle(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();

            articlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login",
                    articleTitle,
                    articlePageObject.getArticleTitle());

            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        Thread.sleep(1500);
        navigationUI.clickMyLists();
//        navigationUI.closePopup();

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
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");


        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.createNewMyListAndAddArticle(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();

            articlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login",
                    articleTitle,
                    articlePageObject.getArticleTitle());

            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();


        searchPageObject.initSearchInput();
        if (!Platform.getInstance().isIOS()) {
            searchPageObject.typeSearchLine("Java");
        }
        searchPageObject.clickByArticleWithSubstring("sland of Indonesia");

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        ArticlePageObject secondArticlePageObject = ArticlePageObjectFactory.get(driver);
        secondArticlePageObject.waitForTitleElement();
        String secondArticleTitle = secondArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            secondArticlePageObject.addArticleToExistingMyList(nameOfFolder);
            myListsPageObject.openFolderByName(nameOfFolder);
        } else {
            secondArticlePageObject.addArticlesToMySaved();
        }
        Thread.sleep(3000);
        secondArticlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        Thread.sleep(1500);
        navigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            Thread.sleep(3000);
            myListsPageObject.openFolderByName(nameOfFolder);
        } else if (Platform.getInstance().isIOS()){
            navigationUI.closePopup();
        }
        Thread.sleep(3000);

        myListsPageObject.swipeByArticleToDelete(articleTitle);
        Thread.sleep(3000);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openSearchInFolder();
            searchPageObject.typeSearchLineInSaved("Java");
        } else if (Platform.getInstance().isIOS()){
            searchPageObject.typeSearchLineInSaved("Island of Indonesia");
        }

        if (!Platform.getInstance().isMw()) {
            myListsPageObject.assertSavedListNotEmpty();
        } else {
            myListsPageObject.waitForArticleToAppearByTitle(secondArticleTitle);
        }
    }
}