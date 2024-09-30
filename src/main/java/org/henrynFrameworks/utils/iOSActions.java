package org.henrynFrameworks.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.Map;

public class iOSActions extends AppiumUtils {

    IOSDriver driver;

    public iOSActions(IOSDriver driver)
    {

        this.driver = driver;
    }

    public void iOSlongPressAction(WebElement ele)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) ele).getId());
        params.put("duration", 5.0);
        ((JavascriptExecutor) driver).executeScript("mobile: touchAndHold", params);
    }

    public void scrollToWebElement(WebElement scroll){

        Map <String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) scroll).getId());
        params.put("direction", "down");
        ((JavascriptExecutor) driver).executeScript("mobile: scroll", params);

    }

    public void swipeAction(WebElement ele, String direction) {
        Map<String, Object> params1 = new HashMap<String, Object>();
        //params1.put("elementId", ((RemoteWebElement) allPhotos.get(1)).getId());
        params1.put("direction", "left");
        ((JavascriptExecutor) driver).executeScript("mobile: swipe", params1);
    }

    }

