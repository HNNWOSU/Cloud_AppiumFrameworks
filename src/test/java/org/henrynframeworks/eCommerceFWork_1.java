package org.henrynframeworks;

import BaseTestUtils.AndroidFWBaseUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.henrynFrameworks.pageObject.android.CartPage1;
import org.henrynFrameworks.pageObject.android.ProductCataloguePage1;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class eCommerceFWork_1 extends AndroidFWBaseUtil {

    @Test(dataProvider = "getData")
    public void eCommerce(String name, String gender, String country) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        formPage.setNameField(name);
        formPage.setGender(gender);
        formPage.setCountry(country);
        ProductCataloguePage1 productCataloguePage = formPage.submitForm();
        productCataloguePage.addItemToCartByIndex(0);
        productCataloguePage.addItemToCartByIndex(0);
       CartPage1 cartPage = productCataloguePage.goToCartPage();

        wait.until(ExpectedConditions.attributeContains(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/toolbar_title\")"), "text", "Cart"));
    double totalSum = cartPage.getProductSum();
    double displayFormattedSum = cartPage.getProductSum();
    Assert.assertEquals(totalSum, displayFormattedSum);
    //cartPage.acceptTermsConditions();
    String titleText = cartPage.acceptTermsConditions();
    Assert.assertEquals(titleText, "Terms Of Conditions");
    cartPage.submitOrder();

        // Switch to WebView context
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName);
        }

        driver.context("WEBVIEW_com.androidsample.generalstore");
Thread.sleep(3000);
        try {
            WebElement readMore = wait.until(ExpectedConditions.elementToBeClickable(By.id("KByQx")));
            // Scroll the read more button into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", readMore);

            // Click the read more button using JavaScript to avoid multiple clicks
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", readMore);

            // Now click on the reject cookies button
            WebElement rejectCookie = wait.until(ExpectedConditions.elementToBeClickable(By.id("W0wltc")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", rejectCookie);

            // Alternatively, accept cookies:
            // WebElement acceptCookie = wait.until(ExpectedConditions.elementToBeClickable(By.id("L2AGLb")));
            // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", acceptCookie);
        } catch (TimeoutException e) {
            System.out.println("Cookie consent elements were not found, proceeding with the test.");
        }

        // Perform search operation
  driver.findElement(By.name("q")).sendKeys("Kamala Harris");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(5000);

        // Go back to the native app context
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }

    @BeforeMethod
    public void preSetUp()
    {
        formPage.setActivity();

    }

    @DataProvider
    public Object[][] getData()
    {
        return new Object[] []  { {"Henry Appium Test Data", "male", "Canada"},{"Henry CreateFuture", "male",
                "Aruba"} };

        }
}








