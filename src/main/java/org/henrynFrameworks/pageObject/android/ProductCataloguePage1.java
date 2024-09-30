// Package declaration for the ProductCataloguePage class
package org.henrynFrameworks.pageObject.android;

// Import necessary libraries for Appium and Selenium
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.henrynFrameworks.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Represents the Product Catalogue Page in the application.
 * This class extends the AndroidActions class, which provides common actions for Android devices.
 */
public class ProductCataloguePage1 extends AndroidActions {

    // Declare a private WebDriverWait object to handle waiting for elements to be available
    private WebDriverWait wait;

    // Declare an AndroidDriver object to interact with the Android device
    AndroidDriver driver;

    /**
     * Constructor for the ProductCataloguePage class.
     * Initializes the AndroidDriver and WebDriverWait objects.
     * @param driver the AndroidDriver object to use for interacting with the device
     */
    public ProductCataloguePage1(AndroidDriver driver) {
        // Call the superclass constructor to initialize the AndroidActions class
        super(driver);

        // Assign the AndroidDriver object to the driver field
        this.driver = driver;

        // Initialize the PageFactory to bind the elements on the page to the corresponding fields in this class
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

        // Initialize the WebDriverWait object with a timeout of 20 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Represents the list of "Add to Cart" buttons on the page.
     * The @AndroidFindBy annotation is used to specify the XPath locator for the elements.
     */
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCart;

    /**
     * Represents the "Cart" button on the page.
     * The @AndroidFindBy annotation is used to specify the ID locator for the element.
     */
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;

    /**
     * Adds an item to the cart by clicking on the "Add to Cart" button at the specified index.
     * @param index the index of the "Add to Cart" button to click
     */
    public void addItemToCartByIndex(int index) {
        // Get the "Add to Cart" button at the specified index and click on it
        addToCart.get(index).click();
    }

    /**
     * Navigates to the Cart page by clicking on the "Cart" button.
     * @throws InterruptedException if the thread is interrupted while waiting for the page to load
     */
    public CartPage1 goToCartPage() throws InterruptedException {
        // Click on the "Cart" button to navigate to the Cart page
        cartButton.click();

        // Wait for 2 seconds to allow the page to load
        Thread.sleep(2000);
        return new CartPage1(driver);
    }
}


