package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";
        CLOSE_POPUP = "id:Sort";
    }
    public iOSNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
