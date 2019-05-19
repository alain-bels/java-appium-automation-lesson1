package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";
        CLOSE_POPUP = "id:Sort";
    }
    public iOSNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}
