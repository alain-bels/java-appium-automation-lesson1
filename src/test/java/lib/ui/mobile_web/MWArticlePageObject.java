package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:page-actions-watch";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";
        ARTICLE_HEADER_CONTAINER = "id:org.wikipedia:id/view_page_header_container";
    }
    public  MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}

