package util;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class ElementUtil {

    private WebDriver driver;
    private WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
    private Actions action;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
    }

    public void sendKey(By byElement, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
            JavascriptExecutor j = (JavascriptExecutor) driver;
            j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void sendKeyAndEnter(By by, String string, Keys key) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            JavascriptExecutor j = (JavascriptExecutor) driver;
            j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
            element.sendKeys(string);
            element.sendKeys(key);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void moveToElement(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            action.moveToElement(element).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void click(By byElement) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
            JavascriptExecutor j = (JavascriptExecutor) driver;
            j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void click(WebElement webelement) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webelement));
            JavascriptExecutor j = (JavascriptExecutor) driver;
            j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void clickChildElement(WebElement webelement, By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(webelement, by));
            JavascriptExecutor j = (JavascriptExecutor) driver;
            j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void clickChildElementEqualText(By by1, By by2, String text) {
        boolean check = false;
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(by1, by2));
        System.out.println("11111111111" + elements.size());
        for (WebElement element : elements) {
            System.out.println("22222222222222222" + element.getText());
            System.out.println("333333333333333333" + element.findElement(By.xpath("..")).getText());
            System.out.println("444444444444444444" + element.findElement(By.xpath("..")));
            if (element.getText().equals(text)) {
                System.out.println("55555555555555555555" + element.getText());
                JavascriptExecutor j = (JavascriptExecutor) driver;
                j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
                element.click();
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    public void clickElementEqualText(By by, String text) {
        boolean check = false;
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                JavascriptExecutor j = (JavascriptExecutor) driver;
                j.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
                element.click();
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    public String elementGetText(By by) {
        String text = null;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            text = driver.findElement(by).getText();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
        return text;
    }

    public String childElementGetText(By by1, By by2) {
        String text = null;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(by1, by2));
            text = element.getText();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
        return text;
    }

    public String childElementGetText(WebElement webelement, By by) {
        String text = null;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(webelement, by));
            text = element.getText();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
        return text;
    }

    public boolean checkElementExist(By by) {
        boolean check = false;
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            if (elements.size() != 0) {
                check = true;
            }
        } catch (Exception e) {
            System.out.println("Element not exist. Other elements will be checked");
        }
        return check;
    }

    public void checkAttributeChildElement(WebElement webelement, By by, String attr, String expected) {
        try {
            WebElement finalElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(webelement, by));
            Assert.assertEquals(expected, finalElement.getAttribute(attr));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void windowSwitchTo(int i) {
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(i));
    }

}