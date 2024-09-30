package org.henrynFrameworks.pageObject.iOS;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.henrynFrameworks.utils.iOSActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class iOSHomePage extends iOSActions {

    private WebDriverWait wait;
    IOSDriver driver;
    public iOSHomePage(IOSDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    //driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
    @iOSXCUITFindBy(accessibility = "Alert Views")
    private WebElement alertViewsButton;

    public AlertViewsPage clickAlertViewsButton()
    {
        alertViewsButton.click();
        return new AlertViewsPage(driver);

    }


}




