package Pages;

import baseClass.InitializeBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class SignInPage extends InitializeBrowser {


    @FindBy(id = "email")
    WebElement emailIdInputBox;

    @FindBy(id = "passwd")
    WebElement password;

    @FindBy(id = "SubmitLogin")
    WebElement SignInButton;

    @FindBy(xpath = "//li[normalize-space(.)='Authentication failed.']")
    WebElement authenMsg;

    public SignInPage() {
        PageFactory.initElements(driver, this);
        driver.get(prop.getProperty("loginAutomationPractice"));
    }

    public void SignInToWebsite(String email, String pass) throws Exception {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        emailIdInputBox.sendKeys(email);
        password.sendKeys(pass);
        SignInButton.click();
        Thread.sleep(3000L);
        if (driver.findElements(By.id("SubmitLogin")).size()!=0){
            SignInButton.click();
        }
        if (driver.findElements(By.xpath("//li[normalize-space(.)='Authentication failed.']")).size() != 0) {
            System.out.println("email id and password is not valid");
        } else {
            System.out.println("Successfully Login to website");
        }
    }
}
