package pages;

import util.DriverFactory;
import util.ElementUtil;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.*;

public class ProductPage {

    private final By myAccountTab = By.id("myAccount");
    private final By searchBox = By.cssSelector(".desktopOldAutosuggestTheme-container input");
    private final By searchResultTxt = By.cssSelector(".searchResultSummaryBar-mainText h1");
    private final By productList = By.cssSelector(".productListContent-item");
    private final By productName = By.id("product-name");
    private final By sortingBox = By.id("SortingBox");
    private final By sortSelects = By.cssSelector(".horizontalSortingBar-content a");
    private final By toastMsgGoToBasket = By.cssSelector(".hb-toast-link");
    private final By goToBasketBtt = By.id("shoppingCart");
    private final By prodoctOnBasketHeaderBtt = By.cssSelector(".checkoutui-ProductOnBasketHeader-m4tLG");
    private final By deleteBasket = By.cssSelector(".delete_all_2uTds");
    private final By emptyBasketMsg = By.cssSelector(".content_Z9h8v");
    private final By deletePopupApprove = By.cssSelector(".red_3m-Kl");
    private final By allSellerTab = By.id("merchantTabTrigger");
    private final By allSellerTabSellerList = By.cssSelector(".merchant-list-item.merchant-sort-item");
    private final By checkoutModalCloseBtt = By.cssSelector(".checkoutui-Modal-2iZXl");
    private final By productNameListOnBasket = By.cssSelector(".product_name_3Lh3t");


    private WebDriver driver;
    private ElementUtil elementUtil;
    private WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
    public String expectedProductText;
    public String productNameText;
    public int numberOfProductsOnBasket;


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    public void checkHomePage(String title) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountTab));
        Assert.assertEquals(title, driver.getTitle());
    }

    public void typeIntoTheSearchBoxAndEnter(String product) {
        elementUtil.sendKeyAndEnter(searchBox, product, Keys.ENTER);
        expectedProductText = product;
    }

    public void searchIsVerified() {
        Assert.assertEquals(expectedProductText, elementUtil.elementGetText(searchResultTxt));
    }

    public void sortBySelection(String select) {
        elementUtil.click(sortingBox);
        elementUtil.clickElementEqualText(sortSelects, select);
    }

    public void clickOnProduct() throws InterruptedException {
        //Normalde thread sleep kullanmayi tercih etmiyorum fakat siralama da cok satanlari sectikten sonra dom ve frontend 2-3 saniye sonra yenileniyor.
        //Ve bu durum angularwait, jswait gibi bircok wait ile cozume kavusmuyor
        Thread.sleep(5000);
        int productNumber = (int) (Math.random() * 10);
        int sayac = 0;
        boolean check = false;
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productList));
        for (WebElement element : elements) {
            if (sayac == productNumber - 1) {
                productNameText = element.findElement(By.xpath("./div//h3")).getText();
                elementUtil.click(element);
                check = true;
                break;
            }
            sayac++;
        }
        Assert.assertTrue(check);
    }

    public void checkProductPage() {
        elementUtil.windowSwitchTo(1);
        Assert.assertEquals(productNameText, elementUtil.elementGetText(productName));
    }

    public void differentSellersProductsAreAddedToTheCart(int numberOfSellers) {
        numberOfProductsOnBasket = numberOfSellers;
        elementUtil.click(allSellerTab);
        int sayac = 0;
        boolean check = false;
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allSellerTabSellerList));
        for (WebElement element : elements) {
            List<WebElement> childelements = element.findElements(By.xpath("./td"));
            for (WebElement childelement : childelements) {
                if (childelement.getAttribute("class").equals("add-Cart")) {
                    elementUtil.click(childelement);
                    sayac++;
                    if (sayac == 1) {
                        elementUtil.click(checkoutModalCloseBtt);
                    }
                    break;
                }
            }
            if (sayac == numberOfSellers) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    public void goToBasket() {
        if (elementUtil.checkElementExist(prodoctOnBasketHeaderBtt)) {
            elementUtil.click(checkoutModalCloseBtt);
            elementUtil.click(goToBasketBtt);

        } else if (elementUtil.checkElementExist(toastMsgGoToBasket)) {
            elementUtil.click(toastMsgGoToBasket);
        } else {
            elementUtil.click(goToBasketBtt);
        }
    }

    public void checkBasketProducts() {
        boolean check = false;
        int sayac = 0;
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productNameListOnBasket));
        for (WebElement element : elements) {
            if (element.findElement(By.xpath("./a")).getText().equals(productNameText)) {
                sayac++;
            }
            if (sayac == numberOfProductsOnBasket) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    public void clearBasket() {
        driver.get("https://checkout.hepsiburada.com/sepetim");
        if (elementUtil.checkElementExist(deleteBasket)) {
            elementUtil.click(deleteBasket);
            elementUtil.click(deletePopupApprove);
        } else {
            Assert.assertEquals("Sepetin şu an boş", elementUtil.childElementGetText(emptyBasketMsg, By.xpath("./h1")));
        }
        driver.get("https://www.hepsiburada.com/");
    }

}


