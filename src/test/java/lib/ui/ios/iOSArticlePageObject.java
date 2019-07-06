package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        ARTICLE_HEADER_CONTAINER = "id:org.wikipedia:id/view_page_header_container";
    }
    public  iOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
