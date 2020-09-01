package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverFactory;

public class HomePage {
  private WebDriver driver = null;

  private By createIssueButton = By.id("create_link");
  private By tempWindowIssueCreated = By.xpath("//*[contains(@class,'aui-will-close')]");
  private By createIssueDialog = By.id("create-issue-dialog");
  private By searchField = By.id("quickSearchInput");
  private By userIcon = By.id("header-details-user-fullname");
  private By browseLink = By.id("browse_link");

  public HomePage(WebDriver driver) {
    this.driver = driver;
  }

  public void navigateToHomePage() {
    driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
  }

  public void clickCreateIssue() {
    WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);
    wait.until(ExpectedConditions.elementToBeClickable(browseLink));
    wait.until(ExpectedConditions.elementToBeClickable(createIssueButton)).click();
  }

//  public void clickCreateIssue() {
//    clickOnElementWithRetry(createIssueButton, createIssueDialog, 3, 3);
//  }
//
//  private void clickOnElementWithRetry(By elementToBeClicked, By successCriteriaElement, int attempts, int timeOutInSeconds) {
//    WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
//    for (int i = 0; i < attempts; i++) {
//      try {
//        wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
//        driver.findElement(elementToBeClicked).click();
//        break;
//      } catch (TimeoutException e) {
//      }
//    }
//    wait.until(ExpectedConditions.visibilityOfElementLocated(successCriteriaElement)).isDisplayed();
//  }

  public boolean isCreateIssueButtonPresent() {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    return wait.until(ExpectedConditions.elementToBeClickable(createIssueButton)).isDisplayed();
  }

  public boolean isIssueCreated(String text) {
    WebDriverWait wait = new WebDriverWait(driver, 3);
    return wait.until(ExpectedConditions.visibilityOfElementLocated(tempWindowIssueCreated)).isDisplayed()
        && driver.findElement(tempWindowIssueCreated).getText().contains(text);
  }

  public boolean findUserIcon() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    return wait.until(ExpectedConditions.visibilityOfElementLocated(userIcon)).isDisplayed();
  }

  public void searchJiraTicket() {
    new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(searchField)).sendKeys("WEBINAR-12202");
  }

  public void enterSearchJiraTicket() {
    driver.findElement(searchField).sendKeys(Keys.ENTER);
  }
}