package Pages;

import baseClass.InitializeBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class registration extends InitializeBrowser {


    @FindBy(xpath = "//a[normalize-space(.)='Sign in']")
    WebElement SignIn;

    @FindBy(xpath = "//a[normalize-space(.)='Contact us']")
    WebElement ContactUs;

    @FindBy(xpath = "//input[@id='email_create']")
    WebElement emailIdInputBox;

    @FindBy(css = "#SubmitCreate")
    WebElement CreateAnAccount;

    @FindBy(css = "#id_gender1")
    WebElement MrRadioButton;

    @FindBy(css = "#customer_firstname")
    WebElement firstName;

    @FindBy(css = "#customer_lastname")
    WebElement lastName;

    @FindBy(id = "passwd")
    WebElement password;

    @FindBy(css = "#days")
    WebElement DOB_days;

    @FindBy(css = "#months")
    WebElement DOB_months;

    @FindBy(css = "#years")
    WebElement DOB_years;

    @FindBy(id = "firstname")
    WebElement Add_firstName;

    @FindBy(id = "lastname")
    WebElement Add_lastName;

    @FindBy(id = "company")
    WebElement company;

    @FindBy(id = "address1")
    WebElement address1;

    @FindBy(id = "city")
    WebElement City;

    @FindBy(id = "id_state")
    WebElement State;

    @FindBy(id = "postcode")
    WebElement Postcode;

    @FindBy(id = "id_country")
    WebElement Country;

    @FindBy(id = "other")
    WebElement AdditionalInfo;

    @FindBy(id = "phone_mobile")
    WebElement MobPhone;

    @FindBy(id = "alias")
    WebElement AddressAlias;

    @FindBy(xpath = "//button[normalize-space(.)='Register']")
    WebElement Register;

    @FindBy(xpath = "//h1[normalize-space(.)='My account']")
    WebElement myAccount;

    @FindBy(xpath = "//p[normalize-space(.)='There are 8 errors']/..//li")
    WebElement listOfErrorMsg;

    @FindBy(xpath = "//p[normalize-space(.)='There are 4 errors']/..//li")
    WebElement listOfInvalidError;

    public registration() {
        PageFactory.initElements(driver, this);
        driver.get(prop.getProperty("loginAutomationPractice"));
    }

    public void registration(String email, String fName, String lname, String pass, String day, String Month, String year,
                             String Company, String Address, String city, String state, String postalCode, String country,
                             String AddInfo, String Mobile, String Alias) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        emailIdInputBox.sendKeys(email);
        CreateAnAccount.click();
        MrRadioButton.click();
        firstName.sendKeys(fName);
        lastName.sendKeys(lname);
        password.click();
        password.sendKeys(pass);
        Select selectDay = new Select(DOB_days);
        Select selectMon = new Select(DOB_months);
        Select selectYear = new Select(DOB_years);
        selectDay.selectByValue(day);
        selectMon.selectByValue(Month);
        selectYear.selectByValue(year);
        Add_firstName.clear();
        Add_firstName.sendKeys(fName);
        Add_lastName.clear();
        Add_lastName.sendKeys(lname);
        company.sendKeys(Company);
        address1.sendKeys(Address);
        City.sendKeys(city);
        Select selectState = new Select(State);
        selectState.selectByVisibleText(state);
        Postcode.sendKeys(postalCode);
        Select selectCountry = new Select(Country);
        selectCountry.selectByVisibleText(country);
        AdditionalInfo.sendKeys(AddInfo);
        MobPhone.sendKeys(Mobile);
        AddressAlias.clear();
        AddressAlias.sendKeys(Alias);
        Register.click();
        if (driver.findElements(By.xpath("//h1[normalize-space(.)='My account']")).size() != 0) {
            System.out.println("Registration is completed SuccessFully");
        }
    }

    public void verifyErrorMessage(String email, String errors) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String[] listOfError = errors.split(",");
        emailIdInputBox.sendKeys(email);
        CreateAnAccount.click();
        Register.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(listOfErrorMsg));
        List<String> errorList = new ArrayList<>(Arrays.asList(listOfError));
        for (int i = 0; i < errorList.size(); i++) {
            errorList.get(i).equalsIgnoreCase(listOfError[i]);
            System.out.println(listOfError[i] + " is getting displayed");
        }
    }

    public void verifyErrorForInput(String errors) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(listOfInvalidError));
        String[] listOfError = errors.split(",");
        List<String> errorList = new ArrayList<>(Arrays.asList(listOfError));
        for (int i = 0; i < errorList.size(); i++) {
            errorList.get(i).equalsIgnoreCase(listOfError[i]);
            System.out.println(listOfError[i] + " is getting displayed");
        }
    }

}
