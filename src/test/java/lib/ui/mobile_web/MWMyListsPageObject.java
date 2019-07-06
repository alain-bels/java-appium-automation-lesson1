package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TMP = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(@class,'mw-ui-icon-mf-watched')]";
        SAVED_FOLDER_SEARCH_AREA = "css:button#searchIcon";
        SAVED_ARTICLE_CELL = "xpath://ul[contains(@class,'watchlist')]//li[contains(@class,'page-summary with-watchstar')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}



