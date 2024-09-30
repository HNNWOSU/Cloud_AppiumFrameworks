package org.henrynFrameworks.pageObject.iOS;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.henrynFrameworks.utils.iOSActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertViewsPage extends iOSActions {

    private WebDriverWait wait;
    IOSDriver driver;
    public AlertViewsPage(IOSDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    //driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Text Entry\"`]")).click();
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Text Entry\"`]")
    private WebElement textEntryMenu;

    //driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField")).sendKeys("Henry Hill");
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
    private WebElement textEntryField;

    //driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"OK\"`]")).click();
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == \"OK\"`]")
    private WebElement okButton;

    //driver.findElement(AppiumBy.iOSNsPredicateString("name == \"Confirm / Cancel\"")).click();
    @iOSXCUITFindBy(iOSNsPredicate = "name == \"Confirm / Cancel\"")
    private WebElement confirmButton;

    //       String text =driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'A message'")).getText();
   @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"A message should be a short, complete sentence.\"`]")
    private WebElement messageText;

    //driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Confirm'")).click();
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == \"Confirm\"`]")
    private WebElement confirmButton2;


    public void fillTextEntry(String name)
    {
        textEntryMenu.click();
        textEntryField.sendKeys(name);
        okButton.click();



    }

    public String getConfirmMessage()
    {
        confirmButton.click();
        return messageText.getText();


    }

}




