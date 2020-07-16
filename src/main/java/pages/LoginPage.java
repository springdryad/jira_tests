package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

  private String username = "RuslanaChumachenko";
  private String password = "RuslanaChumachenko";

  private WebDriver driver = null;
  private By userNameInput = By.id("login-form-username");
  private By passwordInput = By.id("login-form-password");
  private By loginButton = By.id("login");

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  public void enterUserName() {
    driver.findElement(userNameInput).clear();
    driver.findElement(userNameInput).sendKeys(username);
  }

  public void enterPassword() {
    driver.findElement(passwordInput).sendKeys(password);
  }

  public void clickLoginButton() {
    driver.findElement(loginButton).click();
  }



}
