package org.henrynFrameworks.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public abstract class AppiumUtils {

    public AppiumDriverLocalService service;

public AppiumDriverLocalService startAppiumServer(String ipAddress, int port)

{
    AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder()
            .withAppiumJS(new File("/opt/homebrew/lib/node_modules/appium/build/lib/main.js"))
            .withIPAddress(ipAddress)
            .usingPort(port);
    service = AppiumDriverLocalService.buildService(appiumServiceBuilder);
    service.start();
    return service;
}

public void waitForElement(WebElement element,AppiumDriver driver)

{
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    wait.until(ExpectedConditions.visibilityOf(element));
}

    public Double getFormattedAmount(String amountString)
    {
        return Double.parseDouble(amountString.substring(1));
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {

        //convert json file content to json string
        String jsonsContent =
                FileUtils.readFileToString(new File(jsonFilePath), "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonsContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }

    public String getScreenShotPath(String testCaseName, AppiumDriver driver) throws IOException {

       File source = driver.getScreenshotAs(OutputType.FILE);
       String destinationFile = System.getProperty("user.dir")+"/src/main/Reports/"+testCaseName+".png";
       FileUtils.copyFile(source,new File(destinationFile));
       return destinationFile;

    }

}
