package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssueWindow {
  private WebDriver driver = null;

  private By projectField = By.id("project-field");
  private By issueTypeField = By.id("issuetype-field");
  private By summaryField = By.id("summary");
  private By reporterField = By.id("reporter-field");
  private By summaryMode = By.xpath("//ul/li[@data-mode='source']");
  private By issueDescription = By.id("description");
  private By createIssueSubmit = By.id("create-issue-submit");
  private By cancelButton = By.xpath("//a[@class='cancel']");
  private By createIssueWindow = By.id("create-issue-dialog");

  public CreateIssueWindow(WebDriver driver) {
    this.driver = driver;
  }

  public void enterProjectField(String text) {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(projectField)).clear();
    driver.findElement(projectField).sendKeys(text, Keys.TAB);
  }

  public void enterIssueTypeField(String text) {
    WebDriverWait wait = new WebDriverWait(driver, 3);
    wait.until(ExpectedConditions.elementToBeClickable(issueTypeField)).clear();
    driver.findElement(issueTypeField).sendKeys(text,Keys.TAB);
  }

  public void enterSummary(String text) {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(summaryField)).sendKeys(text,Keys.TAB);
  }

  public void enterReporterField(String text) {
    driver.findElement(reporterField).clear();
    driver.findElement(reporterField).sendKeys(text);
  }

  public void selectDescriptionMode() {
    driver.findElement(summaryMode).click();
  }

  public void enterDescription(String text) {
    driver.findElement(issueDescription).sendKeys(text);
  }

  public void pressCreateIssueButton() {
    driver.findElement(createIssueSubmit).click();
  }

  public void clickCancelButton(){
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
  }

  public void acceptAlert(){
    driver.switchTo().alert().accept();
  }

  public void dismissAlert(){
    driver.switchTo().alert().dismiss();
  }

  public boolean isCreateIssueWindowDisappear(){
    WebDriverWait wait = new WebDriverWait(driver, 3);
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(createIssueWindow));
  }

  public boolean isCreateIssueWindowDoesNotDisappear(){
    WebDriverWait wait = new WebDriverWait(driver, 3);
    return wait.until(ExpectedConditions.presenceOfElementLocated(createIssueWindow)).isDisplayed();
  }
}
