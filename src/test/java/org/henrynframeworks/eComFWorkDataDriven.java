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

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class eComFWorkDataDriven extends AndroidFWBaseUtil {



    @Test(dataProvider = "getData")
    public void eCommerce(HashMap<String, String> input) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountry(input.get("country"));
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
    }

    @BeforeMethod
    public void preSetUp()
    {
        formPage.setActivity();

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/testData" +
                "/eCommerce.json");
        return new Object[] []  { {data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)} };
        }
}








