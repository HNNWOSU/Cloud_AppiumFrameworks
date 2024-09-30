package org.henrynFrameworks.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends AppiumUtils{

    AndroidDriver driver;

    public AndroidActions(AndroidDriver driver) {

        this.driver = driver;
    }

    public void longPressAction(WebElement ele) {
        // Execute a JavaScript function called "mobile: longClickGesture" on the driver object
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                // Pass a map as an argument to the JavaScript function
                // The map contains two key-value pairs: "elementId" and "duration"
                // "elementId" is the ID of the element "ele" and "duration" is the duration of the long press gesture in milliseconds
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000.0));

    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.of("left", 100, "top", 100, "width", 200, "height",
                            200, "direction", "down", "percent", 10.0
                    ));
        } while (canScrollMore);
    }

    public void swipeAction(WebElement ele, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of
                ("elementId", ((RemoteWebElement) ele).getId(),
                        "direction", direction, "percent", 0.75));


    }

    public Double getFormattedAmount(String amountString) {
        return Double.parseDouble(amountString.substring(1));
    }

    public void scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator
                ("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"" + text + "\"));"));

    }
}
