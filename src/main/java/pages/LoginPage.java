package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

  private WebDriver driver = null;
  private By userNameInput = By.id("login-form-username");
  private By passwordInput = By.id("login-form-password");
  private By loginButton = By.id("login");
  //private By loginErrorMessage = By.xpath("//*[contains(text(),\"" + message + "\")]");


  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }


  public void enterUserName(String username) {
    new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(userNameInput)).clear();
    driver.findElement(userNameInput).sendKeys(username);
  }


  public void enterPassword(String password) {
    driver.findElement(passwordInput).sendKeys(password);
  }


  public void clickLoginButton() {
    driver.findElement(loginButton).click();
  }


//  public boolean errorMessageIsPresent(String message) {
//    return driver.findElement(loginErrorMessage).isDisplayed();
//  }


  public boolean errorMessageIsPresent(String message) {
    new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), \"" + message + "\")]")));
    return driver.findElement(By.xpath("//*[contains(text(), \"" + message + "\")]")).isDisplayed();
  }


}
