// Package declaration for the ProductCataloguePage class
package org.henrynFrameworks.pageObject.android;

// Import necessary libraries for Appium and Selenium

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.henrynFrameworks.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class CartPage1 extends AndroidActions {

    // Declare a private WebDriverWait object to handle waiting for elements to be available
    private WebDriverWait wait;

    // Declare an AndroidDriver object to interact with the Android device
    AndroidDriver driver;


    public CartPage1(AndroidDriver driver) {
        // Call the superclass constructor to initialize the AndroidActions class
        super(driver);

        // Assign the AndroidDriver object to the driver field
        this.driver = driver;

        // Initialize the PageFactory to bind the elements on the page to the corresponding fields in this class
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

        // Initialize the WebDriverWait object with a timeout of 20 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    public List<WebElement> productList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    public WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    public WebElement terms;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
    public WebElement titleText;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button1\")")
    public WebElement acceptButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    public WebElement proceed;

    @AndroidFindBy(className = "android.widget.CheckBox")
    public WebElement CheckBox;

    public List<WebElement> getProductList()
    {
return productList;

    }

    public double getProductSum()
    {
        int count = productList.size();
        double totalSum = 0;
        for (int i = 0; i < count; i++) {
            String amountString = productList.get(i).getText();
            double price = getFormattedAmount(amountString);
            totalSum = totalSum + price;
//            System.out.println(totalSum);
        }
        return totalSum;
    }

    public Double getTotalAmountDisplayed()
    {
        return getFormattedAmount(totalAmount.getText());
    }

    public String acceptTermsConditions()
    {
        longPressAction(terms);
        wait.until(ExpectedConditions.visibilityOf(titleText));
        String title =titleText.getText();
        acceptButton.click();
        return title;
    }

    public Double getFormattedAmount(String amount)
    {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public void submitOrder()
    {
        CheckBox.click();
        proceed.click();
    }

}


