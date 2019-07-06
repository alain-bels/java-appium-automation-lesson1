package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TMP = "xpath://*[@text = '{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TMP = "xpath://*[@text = '{TITLE}']";
        SAVED_FOLDER_SEARCH_AREA = "id:org.wikipedia:id/reading_list_header_image_2";
        SAVED_ARTICLE_CELL = "id:org.wikipedia:id/page_list_item_container";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
