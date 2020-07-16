import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreateIssueWindow;
import pages.HomePage;
import pages.JiraTicketPage;
import pages.LoginPage;
import utils.WebDriverFactory;

public class TestCases {

  private WebDriver driver;
  private LoginPage loginPage;
  private HomePage homePage;
  private CreateIssueWindow createIssueWindow;
  private JiraTicketPage jiraTicketPage;


  @BeforeMethod
  public void setUp() {
    WebDriverFactory.createInstance("Chrome");
    driver = WebDriverFactory.getDriver();
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
    createIssueWindow = new CreateIssueWindow(driver);
    jiraTicketPage = new JiraTicketPage(driver);
  }

  @Test
  public void createIssue() {
    homePage.navigateToHomePage();
    loginPage.enterUserName();
    loginPage.enterPassword();
    loginPage.clickLoginButton();

    homePage.isCreateIssueButtonPresent();
    homePage.clickCreateIssue();

    createIssueWindow.isProjectFieldDisplayed();
    createIssueWindow.clearProjectField();
    createIssueWindow.enterProjectField("Webinar");
    createIssueWindow.pressTabAfterProjectField();

    createIssueWindow.isIssueTypeFieldDisplayed();
    createIssueWindow.clearIssueTypeField();
    createIssueWindow.enterIssueTypeField("Task");
    createIssueWindow.pressTabAfterIssueTypeField();

    createIssueWindow.isSummaryFieldDisplayed();
    createIssueWindow.enterSummary("Ruslana's task #3");
    createIssueWindow.clearReporterField();
    createIssueWindow.enterReporterField("RuslanaChumachenko");
    createIssueWindow.selectDescriptionMode();
    createIssueWindow.enterDescription("Sample description");

    createIssueWindow.pressCreateIssueButton();
    Assert.assertTrue(homePage.isIssueCreated("WEBINAR"));
  }

  @Test
  public void addCommentForTicket() {
    //login to home page
    homePage.navigateToHomePage();
    loginPage.enterUserName();
    loginPage.enterPassword();
    loginPage.clickLoginButton();

    //it is needed to wait until user becomes logged in, otherwise alert appears
    homePage.findUserIcon();
    //search for the ticket
    homePage.searchJiraTicket();
    homePage.enterSearchJiraTicket();

    //add comment
    jiraTicketPage.clickCommentButton();
    jiraTicketPage.enterTextToCommentField();
    jiraTicketPage.clickAddCommentButton();

    //verify that comment is on the page
    Assert.assertTrue(jiraTicketPage.isCommentAdded());

    //delete the comment
    jiraTicketPage.clickOnDeleteComment();
    jiraTicketPage.clickDeleteDialogButton();

    //verify that comment has been deleted
    Assert.assertTrue(jiraTicketPage.isCommentDeleted());
  }


  @AfterMethod
  public void tearDown() {
    driver.quit();
  }


}
