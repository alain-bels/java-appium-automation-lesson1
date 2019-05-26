package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE_TMP = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
        SAVED_ARTICLE_CELL = "xpath://XCUIElementTypeCell";

    }
    public iOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

}
