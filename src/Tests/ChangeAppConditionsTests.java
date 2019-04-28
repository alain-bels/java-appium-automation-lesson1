package Tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionsTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String titleBeforRotation = articlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String titleAfterRotation = articlePageObject.getArticleTitle();
        assertEquals(
                "Article title have been changed after screen rotation",
                titleBeforRotation,
                titleAfterRotation
        );

        this.rotateScreenPortrate();

        String titleAfterSecondRotation = articlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                titleBeforRotation,
                titleAfterSecondRotation
        );
    }


    @Test
    public void testCheckSearchArticleInBackground() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }


}
