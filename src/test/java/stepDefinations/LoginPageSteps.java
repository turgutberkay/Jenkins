package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import util.DriverFactory;

public class LoginPageSteps {

    WebDriver driver = DriverFactory.getDriver();
    public LoginPage loginPage = new LoginPage(driver);

    @Given("Go to {string}")
    public void goToUrl(String url) {
        loginPage.goToUrl(url);
    }

    @When("Login with {string} email and {string} password")
    public void loginWithEmailAndPassword(String email, String password) {
        loginPage.clickToLoginButton();
        loginPage.enterTheEmailAndEnter(email);
        loginPage.enterThePasswordAndEnter(password);

    }

    @When("^Login with (.*) email and enter$")
    public void loginWithEmailEmailAndEnter(String email) {
        loginPage.clickToLoginButton();
        loginPage.enterTheEmailAndEnter(email);
    }

    @When("^Login with (.*?) password and enter$")
    public void loginWithPasswordAndEnter(String password) {
        loginPage.enterThePasswordAndEnter(password);
    }

    @Then("^Should see false email message (.*)$")
    public void shouldSeeFalseEmailMessageFalseMailMessage(String email) {
        loginPage.shouldSeeFalseEmailMessageFalseMailMessage(email);
    }

    @Then("^Should see empty email message (.*)$")
    public void shouldSeeEmptyEmailMessage(String email) {
        loginPage.shouldSeeEmptyEmailMessage(email);
    }

    @Then("^Should see empty password message (.*)$")
    public void shouldSeeEmptyPasswordMessageEmptyPasswordMessage(String password) {
        loginPage.shouldSeeEmptyPasswordMessage(password);
    }
}
