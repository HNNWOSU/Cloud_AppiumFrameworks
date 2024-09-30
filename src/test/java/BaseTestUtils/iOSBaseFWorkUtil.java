package BaseTestUtils;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.henrynFrameworks.pageObject.iOS.iOSHomePage;
import org.henrynFrameworks.utils.AppiumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class iOSBaseFWorkUtil extends AppiumUtils {

    public IOSDriver driver;
    public AppiumDriverLocalService service;
    public iOSHomePage homePage;


    @BeforeClass(alwaysRun = true)
    public void setUpAppium() throws URISyntaxException, IOException
    {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data.properties");

        properties.load(fis);
        String ipAddress = properties.getProperty("ipAddress");
        String port = properties.getProperty("port");
        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        XCUITestOptions options = new XCUITestOptions()
            .setDeviceName (properties.getProperty("iOSDeviceName"))//("iPhone 15 Pro Max")
            .setPlatformName(properties.getProperty("iOSPlatformName"))//("iOS")
            .setPlatformVersion(properties.getProperty("iOSPlatformVersion"))//("17.5")
           .setApp(System.getProperty("user.dir")+"/src/test/resources/UIKitCatalog.app");
 //      .setApp(System.getProperty("user.dir")+"/src/test/resources/TestApp 3.app" );
//        .setApp(System.getProperty("user.dir")+"/src/test/resources/MyRNDemoApp.app" );

        options.setWdaLaunchTimeout(Duration.ofSeconds(20));

        driver = new IOSDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new iOSHomePage(driver);

    }

    @AfterClass(alwaysRun = true)
    public void tearDownAppium() {
        driver.quit();
        service.stop();
    }
}