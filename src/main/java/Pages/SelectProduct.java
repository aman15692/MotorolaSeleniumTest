package Pages;

import baseClass.InitializeBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class SelectProduct extends InitializeBrowser {


    @FindBy(linkText = "WOMEN")
    WebElement WomenElement;

    @FindBy(linkText = "T-shirts")
    WebElement Tshirts;

    @FindBy(xpath = "//a[@class='product-name'][@itemprop='url']")
    WebElement firstItemName;

    @FindBy(id = "search_query_top")
    WebElement searchInputBox;

    @FindBy(className = "product_img_link")
    WebElement productImgLink;

    @FindBy(linkText = "More")
    WebElement MoreButton;

    @FindBy(xpath = "//a[contains(@class, 'product_quantity_up')]")
    WebElement IncreaseQuantity;

    @FindBy(id = "group_1")
    WebElement selectSize;

    @FindBy(xpath = "//button[normalize-space(.)='Add to cart']")
    WebElement AddtoCart;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    WebElement proceedToCheckout;

    @FindBy(xpath = "//p//a[@title='Proceed to checkout']")
    WebElement proceedToCheckout2;

    @FindBy(xpath = "//button[normalize-space(.)='Proceed to checkout']")
    WebElement proceedToCheckout3;

    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement checkBox;

    @FindBy(className = "bankwire")
    WebElement bankWire;

    @FindBy(xpath = "//button[normalize-space(.)='I confirm my order']")
    WebElement ConfirmOrder;

    @FindBy(id = "email")
    WebElement emailIdInputBox;

    @FindBy(id = "passwd")
    WebElement password;

    @FindBy(id = "SubmitLogin")
    WebElement SignInButton;

    @FindBy(xpath = "//a[contains(@class, 'addToWishlist')]")
    WebElement addToWishlist;

    @FindBy(xpath = "//a[@title='Close']")
    WebElement closeButton;

    @FindBy(xpath = "//a[@title='View my customer account']")
    WebElement myAccount;

    @FindBy(xpath = "//a[normalize-space(.)='My wishlists']")
    WebElement wishlists;

    @FindBy(xpath = "//a[@title='Add']")
    WebElement AddQuantity;

    @FindBy(id = "total_price_container")
    WebElement totalPrice;

    public SelectProduct() {
        PageFactory.initElements(driver, this);
        driver.get(prop.getProperty("loginAutomationPractice"));
    }

    public void selectProductFormWebsite() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        actions.moveToElement(WomenElement).build().perform();
        Tshirts.click();
        String firstItemText = firstItemName.getText().trim();
        System.out.println("first Item name is : " + firstItemText);
        searchInputBox.sendKeys(firstItemText);
        String firstItemSearchedText = firstItemName.getText().trim();
        Assert.assertEquals(firstItemText, firstItemSearchedText);
    }

    public void selectProductAndProceed(String Size, String Color, String email, String passWd) throws Exception {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        actions.moveToElement(WomenElement).build().perform();
        Tshirts.click();
        actions.moveToElement(productImgLink).build().perform();
        MoreButton.click();
        IncreaseQuantity.click();
        Select select = new Select(selectSize);
        select.selectByVisibleText(Size);
        driver.findElement(By.xpath("//a[@title='" + Color + "']")).click();
        AddtoCart.click();
        proceedToCheckout.click();
        proceedToCheckout2.click();
        emailIdInputBox.sendKeys(email);
        password.sendKeys(passWd);
        SignInButton.click();
        Thread.sleep(3000L);
        if (driver.findElements(By.id("SubmitLogin")).size()!=0){
            SignInButton.click();
        }
        proceedToCheckout3.click();
        checkBox.click();
        proceedToCheckout3.click();
        bankWire.click();
        ConfirmOrder.click();
    }

    public void addToWishList() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        actions.moveToElement(WomenElement).build().perform();
        Tshirts.click();
        actions.moveToElement(productImgLink).build().perform();
        addToWishlist.click();
        if (driver.findElements(By.xpath("//p[normalize-space(.)='You must be logged in to manage your wishlist.']")).size() != 0) {
            System.out.println("Wishlist not able to add without login");
        }else if (driver.findElements(By.xpath("//p[normalize-space(.)='Added to your wishlist.']")).size() != 0) {
            System.out.println("Wishlist added");
        }
        closeButton.click();
    }

    public void addToWishListWithLogin() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        actions.moveToElement(WomenElement).build().perform();
        Tshirts.click();
        actions.moveToElement(productImgLink).build().perform();
        addToWishlist.click();
        if (driver.findElements(By.xpath("//p[normalize-space(.)='You must be logged in to manage your wishlist.']")).size() != 0) {
            System.out.println("Wishlist not able to add without login");
        }else if (driver.findElements(By.xpath("//p[normalize-space(.)='Added to your wishlist.']")).size() != 0) {
            System.out.println("Wishlist added");
        }
        closeButton.click();
        myAccount.click();
        wishlists.click();
        if (driver.findElements(By.xpath("//h1[normalize-space(.)='My wishlists']")).size()!=0){
            System.out.println("my wishlist is getting displayed");
        }
    }

    public void selectProductAndChange(String Size, String Color) throws Exception {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        actions.moveToElement(WomenElement).build().perform();
        Tshirts.click();
        actions.moveToElement(productImgLink).build().perform();
        MoreButton.click();
        Select select = new Select(selectSize);
        select.selectByVisibleText(Size);
        driver.findElement(By.xpath("//a[@title='" + Color + "']")).click();
        AddtoCart.click();
        proceedToCheckout.click();
        String price = totalPrice.getText();
        AddQuantity.click();
        Thread.sleep(2000L);
        String Secondprice = totalPrice.getText();
        Assert.assertNotEquals(price, Secondprice);
    }
}
