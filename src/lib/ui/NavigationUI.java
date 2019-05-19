package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            CLOSE_POPUP;


    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() throws Exception {
        Thread.sleep(5000);
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My List ",
                20
        );
    }

    public void closePopup() throws Exception {
        Thread.sleep(5000);
        this.waitForElementAndClick(
                CLOSE_POPUP,
                "Cannot find x for close popup",
                20
        );
    }

}
