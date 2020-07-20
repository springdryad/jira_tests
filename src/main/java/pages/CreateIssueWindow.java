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
  private By createIssueButton = By.id("create-issue-submit");


  public CreateIssueWindow(WebDriver driver) {
    this.driver = driver;
  }

  public boolean isProjectFieldDisplayed() {
    WebDriverWait wait = new WebDriverWait(driver, 3);
    return wait.until(ExpectedConditions.elementToBeClickable(projectField)).isDisplayed();
  }

  public void clearProjectField() {
    driver.findElement(projectField).clear();
  }

  public void enterProjectField(String text) {
    driver.findElement(projectField).sendKeys(text);
  }

  public void pressTabAfterProjectField() {
    driver.findElement(projectField).sendKeys(Keys.TAB);
  }

  public boolean isIssueTypeFieldDisplayed() {
    WebDriverWait wait = new WebDriverWait(driver, 3);
    return wait.until(ExpectedConditions.elementToBeClickable(issueTypeField)).isDisplayed();
  }

  public void clearIssueTypeField() {
    driver.findElement(issueTypeField).clear();
  }

  public void enterIssueTypeField(String text) {
    driver.findElement(issueTypeField).sendKeys(text);
  }

  public void pressTabAfterIssueTypeField() {
    driver.findElement(issueTypeField).sendKeys(Keys.TAB);
  }

  public boolean isSummaryFieldDisplayed() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    return wait.until(ExpectedConditions.elementToBeClickable(summaryField)).isDisplayed();
  }

  public void enterSummary(String text) {
    driver.findElement(summaryField).sendKeys(text);
  }

  public void clearReporterField() {
    driver.findElement(reporterField).clear();
  }

  public void enterReporterField(String text) {
    driver.findElement(reporterField).sendKeys(text);
  }

  public void selectDescriptionMode() {
    driver.findElement(summaryMode).click();
  }

  public void enterDescription(String text) {
    driver.findElement(issueDescription).sendKeys(text);
  }

  public void pressCreateIssueButton() {
    driver.findElement(createIssueButton).click();
  }
}
