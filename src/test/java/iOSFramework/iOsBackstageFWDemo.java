package iOSFramework;


import BaseTestUtils.iOSBaseFWorkUtil;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class iOsBackstageFWDemo extends iOSBaseFWorkUtil
{
    @Test
  public void iOSSliderTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement productclick = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"Sauce Labs Bike Light\"`]")));
        productclick.click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"counter plus button\"`]")).click();
        driver.findElement(AppiumBy.iOSNsPredicateString("name == \"Add To Cart button\"")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"tab bar option catalog\"`]")).click();


        WebElement scroll = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"Test.allTheThings() T-Shirt\"`]"));
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"Test.allTheThings() T-Shirt\"`]")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"counter plus button\"`]")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == \"Add To Cart button\"`]")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"tab bar option cart\"`]")).click();
        Thread.sleep(5000);
        driver.findElement(AppiumBy.iOSNsPredicateString("name == \"Proceed To Checkout button\"")).click();




  }
}

