import Pages.*;
import baseClass.InitializeBrowser;
import org.testng.annotations.*;
import utilityClass.RegtestUtil;
import utilityClass.testUtil;

import java.util.ArrayList;
import java.util.Properties;

public class TestCases extends InitializeBrowser {
    public static Properties prop;
    registration registration;
    SignInPage signIn;
    SelectProduct selectProduct;


    public TestCases() {
        super();
    }

    @BeforeMethod
    public void setupClass() {
        initializationOfBrowser();
    }

    /****************************************Test Case no 1***************************************************/
    @Test(dataProvider = "getDatafromExcelforReg", dataProviderClass = RegtestUtil.class)
    public void submitRegistrationForm(String strFirstName, String strLastName, String strPassword, String strDobDate, String strDobMonth, String strDobYear,
                                       String strCompany, String strAddress, String strCity, String strState, String strPostcode, String strCountry,
                                       String strAdditionalInfo, String strMobile, String strAddressAlias) throws Exception {

        String email_id = "amanraj123@gmail.com";
        registration = new registration();
        registration.registration(email_id, strFirstName, strLastName, strPassword, strDobDate, strDobMonth, strDobYear,
                strCompany, strAddress, strCity, strState, strPostcode, strCountry, strAdditionalInfo, strMobile, strAddressAlias);
        driver.close();
    }

    /****************************************Test Case no 2***************************************************/
    @Test(priority = 1)
    public void SignIn() throws Exception {
        signIn = new SignInPage();
        signIn.SignInToWebsite("aman123@gmail.com", "12345");
        driver.close();
    }

    /****************************************Test Case no 3***************************************************/
    @Test(priority = 2)
    public void verifyErrorMessage() throws Exception {
        registration = new registration();
        registration.verifyErrorMessage("aman@gmail.com", "You must register at least one phone number.," +
                "lastname is required.,firstname is required.,passwd is required.,address1 is required.," +
                "city is required.,The Zip/Postal code you've entered is invalid.,It must follow this format: 00000,This country requires you to choose a State.");
        driver.close();
    }

    /****************************************Test Case no 4***************************************************/
    @Test(priority = 3, dataProvider = "getDatafromExcelforReg", dataProviderClass = RegtestUtil.class)
    public void VerifyErrorForIncorrectValue(String strFirstName, String strLastName, String strPassword, String strDobDate, String strDobMonth, String strDobYear,
                                             String strCompany, String strAddress, String strCity, String strState, String strPostcode, String strCountry,
                                             String strAdditionalInfo, String strMobile, String strAddressAlias) throws Exception {

        String email_id = "amanKum123@gmail.com";
        registration = new registration();
        registration.registration(email_id, "12345", "354289", strPassword, strDobDate, strDobMonth, strDobYear,
                strCompany, strAddress, strCity, strState, "14s255", strCountry, strAdditionalInfo, "aman12334g", strAddressAlias);
        registration.verifyErrorForInput("lastname is invalid.,firstname is invalid.,phone_mobile is invalid.,The Zip/Postal code you've entered is invalid.,It must follow this format: 00000");
        driver.close();
    }

    /****************************************Test Case no 5***************************************************/
    @Test(priority = 4)
    public void VerifySearchedProduct() {
        selectProduct = new SelectProduct();
        selectProduct.selectProductFormWebsite();
        driver.close();
    }

    /****************************************Test Case no 6***************************************************/
    @Test(priority = 5)
    public void OrderProduct() throws Exception {
        selectProduct = new SelectProduct();
        selectProduct.selectProductAndProceed("L", "Orange", "aman123@gmail.com", "12345");
        driver.close();
    }

    /****************************************Test Case no 7***************************************************/
    @Test(priority = 6)
    public void addProductToWishlist() throws Exception {
        selectProduct = new SelectProduct();
        selectProduct.addToWishList();
        driver.close();
    }

    /****************************************Test Case no 8***************************************************/
    @Test(priority = 7)
    public void Login_addProductToWishlist() throws Exception {
        signIn = new SignInPage();
        signIn.SignInToWebsite("aman123@gmail.com", "12345");
        selectProduct = new SelectProduct();
        selectProduct.addToWishListWithLogin();
        driver.close();
    }

    /****************************************Test Case no 9***************************************************/
    @Test(priority = 8)
    public void OrderAndChangeProduct() throws Exception {
        selectProduct = new SelectProduct();
        selectProduct.selectProductAndChange("M", "Orange");
        driver.close();
    }
}
