package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "css:a[data-event-name='watchlist']";
        OPEN_NAVIGATION = "id:mw-mf-main-menu-button";
        CLOSE_POPUP = "id:Sort";
    }
    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}

