import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Factory
{
    public DriverFactory() {
    }

    public static WebDriver getInst() {
        String platform = ConfigReader.getProperty("platform");
        System.out.println(platform);
        switch (platform) {
            case "android":
                driver = initAndroid();
                break;
            case "ios":
                driver = initIOs();
                break;
            case "google":
                driver = initGoogle();
        }
        return new Wrapper(driver);
    }

    private static AndroidDriver initAndroid() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("avd", "Nexus_5_API_23_2");
        capabilities.setCapability(AndroidMobileCapabilityType.APPLICATION_NAME, "Chrome");

        File app = new File("apk/com.android.chrome_67.0.3396.87-339608711_minAPI21(x86)(nodpi)_apkmirror.com.apk");
        capabilities.setCapability("app", app.getAbsolutePath());

        AndroidDriver driver = null;
        try {
            driver = new AndroidDriver(capabilities);
        } catch (NullPointerException e) {
            e.fillInStackTrace();
        }

        return driver;
    }

    private static IOSDriver initIOs() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("deviceName", "iPhone 6");
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("xcodeOrgId", "7RDDYA983Z");
        capabilities.setCapability("udid", "cb413b5e7299e8030f9fef465973b5703f29fdb1");
        capabilities.setCapability("xcodeSigningId", "iPhone Developer");

        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("newCommandTimeout", "180");
        capabilities.setCapability("autoAcceptAlerts", "true");
        capabilities.setCapability(IOSMobileCapabilityType.APPLICATION_NAME, "Safari");
        return new IOSDriver(capabilities);
    }

    private static WebDriver initGoogle() {
//        System.setProperty("webdriver.chrome.driver", "demogoogle/src/main/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", new Devices().getDevice());
        System.out.println(":> device "+new Devices().getDevice());
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        options.addArguments("disable-infobars");
        options.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    public static void quit(){
        driver.close();
        driver.quit();
    }
}

}
