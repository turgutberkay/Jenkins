package stepDefinations;

import pages.ProductPage;
import util.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class ProductPageSteps {

    WebDriver driver = DriverFactory.getDriver();
    public ProductPage productPage = new ProductPage(driver);

    @When("Clear basket for new scenarios")
    public void clearBasketForNextScenarios() {
        productPage.clearBasket();
    }

    @When("Check homepage")
    public void checkHomepage() {
        productPage.checkHomePage("Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com");
    }

    @When("Search {string} product")
    public void searchProduct(String product) {
        productPage.typeIntoTheSearchBoxAndEnter(product);
        productPage.searchIsVerified();
    }

    @When("Sort products by {string}")
    public void sortProductsBy(String select) {
        productPage.sortBySelection(select);
    }

    @When("Click on any product and go to the product")
    public void clickAndCheckProduct() throws InterruptedException {
        productPage.clickOnProduct();
        productPage.checkProductPage();
    }

    @When("{int} different sellers products are added to the cart")
    public void differentSellersProductsAreAddedToTheCart(int numberOfSellers) {
        productPage.differentSellersProductsAreAddedToTheCart(numberOfSellers);
    }

    @Then("Go to the cart and check the products")
    public void goToTheCartAndCheckTheProducts() {
        productPage.goToBasket();
        productPage.checkBasketProducts();
    }


}
