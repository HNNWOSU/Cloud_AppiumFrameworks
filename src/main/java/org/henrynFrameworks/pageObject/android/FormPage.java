package org.henrynFrameworks.pageObject.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.henrynFrameworks.utils.AndroidActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPage extends AndroidActions {

    private WebDriverWait wait;
    AndroidDriver driver;
    public FormPage(AndroidDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

//            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/nameField")));
//    WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/nameField")));
//        nameField.sendKeys("Henry Appium Test Data");
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    private WebElement setNameField() {
        // Wait until the element is visible
        wait.until(ExpectedConditions.visibilityOf(nameField));
        return nameField;
    }

    //    WebElement maleRadio = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator
//            ("new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioMale\")")));
//        maleRadio.click();
    @AndroidFindBy(uiAutomator= "new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioMale\")")
    private WebElement maleRadio;

    @AndroidFindBy(uiAutomator= "new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioFemale\")")
    private WebElement femaleRadio;

//    WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator
//            ("new UiSelector().resourceId(\"android:id/text1\")")));
//        countryDropdown.click();
//
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())" +
//                ".scrollIntoView(text(\"Aruba\"));"));
//    WebElement countryOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator
//            ("new UiSelector().text(\"Aruba\")")));
//        countryOption.click();

    @AndroidFindBy(uiAutomator= "new UiSelector().resourceId(\"android:id/text1\")")
    private WebElement countryDropdown;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopNowButton;

    public void setNameField(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }


    public void setActivity() {
        // Check if already on the home screen
        if (!isHomeScreen()) {
            Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
            ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", activity.getAppPackage() + "/" + activity.getAppActivity()));
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/nameField")));
    }
    private boolean isHomeScreen() {
        // Implement logic to check if on home screen, e.g., checking for the presence of a unique element
        return driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/nameField")).size() > 0;
    }

    public void setGender(String gender) {
        if (gender.contains("female"))
            femaleRadio.click();
       else
            maleRadio.click();
        }

    public void setCountry(String countryName) {
        countryDropdown.click();
        scrollToText(countryName);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
    }

    public ProductCataloguePage1 submitForm() {
        shopNowButton.click();
        return new ProductCataloguePage1(driver);
    }

}



