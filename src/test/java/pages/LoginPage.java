package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;
import util.ElementUtil;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    private final By myAccountTab = By.id("myAccount");
    private final By loginBtt = By.id("login");
    private final By emailTxtBox = By.id("txtUserName");
    private final By passwordTxtBox = By.id("txtPassword");
    private final By emailBar = By.cssSelector("._3kuUxLzkHa5tBZFWbKwAfZ");


    private WebDriver driver;
    private ElementUtil elementUtil;
    private WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public void clickToLoginButton() {
        elementUtil.moveToElement(myAccountTab);
        elementUtil.click(loginBtt);
    }

    public void enterTheEmailAndEnter(String email) {
        elementUtil.sendKeyAndEnter(emailTxtBox, email, Keys.ENTER);
    }

    public void enterThePasswordAndEnter(String password) {
        elementUtil.sendKeyAndEnter(passwordTxtBox, password, Keys.ENTER);
    }

    public void shouldSeeFalseEmailMessageFalseMailMessage(String email) {
        Assert.assertEquals(email, elementUtil.childElementGetText(emailTxtBox, By.xpath("../div")));
    }

    public void shouldSeeEmptyEmailMessage(String email) {
        Assert.assertEquals(email, elementUtil.childElementGetText(emailTxtBox, By.xpath("../div")));
    }

    public void shouldSeeEmptyPasswordMessage(String password) {
        elementUtil.click(emailBar);
        boolean check = false;
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(passwordTxtBox, By.xpath("../div")));
        for (WebElement element : elements) {
            if (element.getAttribute("type") == null) {
                Assert.assertEquals(password, element.getText());
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }
}


