package Tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.By;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                articleTitle
        );
    }


    @Test
    public void testSwipeArticle() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
       articlePageObject.swipeToFooter();

    }

    @Test
    public void testAssertTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java version history");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForArticleHeaderContainerPresent();

        articlePageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title"
        );
    }
}
