package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class JiraTicketPage {

  private WebDriver driver = null;
  private String comment = "Comment 1 from Ruslana";
  private By commentButton = By.id("footer-comment-button");
  private By commentField = By.id("comment");
  private By addCommentButton = By.id("issue-comment-add-submit");
  private By addedComment = By.xpath("//p[contains(text(),'" + comment + "')]");
  private By deleteDialogButton = By.id("comment-delete-submit");
  private By deleteCommentButton = By.xpath("//p[contains(text(),'" + comment + "')]//parent::div//parent::div//a[contains(@id, 'delete')]");


  public JiraTicketPage(WebDriver driver) {
    this.driver = driver;
  }


  public void clickCommentButton() {
    new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(commentButton)).click();
  }


  public void enterTextToCommentField() {
    driver.findElement(commentField).sendKeys(comment);
  }


  public void clickAddCommentButton() {
    driver.findElement(addCommentButton).click();
  }


  public boolean isCommentAdded() {
    new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(addedComment));
    return driver.findElement(addedComment).getText().contains(comment);
  }


  public void clickOnDeleteComment() {
    driver.findElement(deleteCommentButton).click();
  }


  public void clickDeleteDialogButton() {
    new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(deleteDialogButton)).click();
  }


  public boolean isCommentDeleted() {
    return new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(addedComment));
  }
}