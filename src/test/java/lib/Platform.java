package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;
    private Platform(){}
    public static Platform getInstance()
    { if (instance == null) {
        instance = new Platform();
    }
    return  instance;
    }


    public RemoteWebDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL,this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
        return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        } else if (this.isMw()) {
            return new ChromeDriver(this.getMWChromeOptions());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value:" + this.getPlatformVar());
        }
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMw()
    {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/admin/Desktop/JavaAppiumAutomation/java-appium-automation-lesson1/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "/Users/admin/Desktop/JavaAppiumAutomation/java-appium-automation-lesson1/apks/Wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions()
    {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone 5/SE");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        return chromeOptions;
    }

    private boolean isPlatform(String myPlatform)
    {
        String platform = this.getPlatformVar();
        return myPlatform.equals(platform);
    }

    public  String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }
}
