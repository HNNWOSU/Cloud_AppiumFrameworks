package BaseTestUtils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.henrynFrameworks.pageObject.android.FormPage;
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
import java.util.Properties;

public class AndroidFWBaseUtil extends AppiumUtils
{

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass (alwaysRun = true)
    public void setUpAppium() throws URISyntaxException, IOException
    {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data.properties");
        String ipAddress = System.getProperty("ipAddress")!=null? System.getProperty("ipAddress"):properties.getProperty("ipAddress");
//        String port = System.getProperty("port")!=null? System.getProperty("port"):properties.getProperty("port");
        properties.load(fis);
//        String ipAddress = properties.getProperty("ipAddress");
        String port = properties.getProperty("port");
        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        UiAutomator2Options options = new UiAutomator2Options()
 //           .setDeviceName("Samsung SM-F926B API 34")
               .setDeviceName(properties.getProperty("AndroidDeviceName"))
 //            .setChromedriverExecutable(System.getProperty("user.dir")+"/src/test/resources/chromedriver")
        .setChromedriverExecutable(System.getProperty("user.dir")+"/src/test/resources/ChromeDriver_113")
           // .setApp(System.getProperty("user.dir")+"/src/test/resources/ApiDemos-debug.apk");
           .setApp(System.getProperty("user.dir")+"/src/test/resources/General-Store.apk");
  //     options.setCapability("noReset",true);

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);
    }


    @AfterClass(alwaysRun = true)
    public void tearDownAppium() {
        driver.quit();
        service.stop();
    }
}