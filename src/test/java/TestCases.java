import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
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
    WebDriverFactory.createInstance("Firefox");
    driver = WebDriverFactory.getDriver();
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
    createIssueWindow = new CreateIssueWindow(driver);
    jiraTicketPage = new JiraTicketPage(driver);
  }

  @Test
  public void successfulLoginTest() {
    homePage.navigateToHomePage();
    loginPage.enterUserName("RuslanaChumachenko");
    loginPage.enterPassword("RuslanaChumachenko");
    loginPage.clickLoginButton();

    Assert.assertTrue(homePage.findUserIcon());
  }

  @DataProvider(name = "unsuccessfulLogins")
  public Object[][] createData() {
    return new Object[][]{
        {"RuslanaChumachenko", "wrong_password", "Sorry, your username and password are incorrect - please try again."},
        {"wrong_username", "RuslanaChumachenko", "Sorry, your username and password are incorrect - please try again."},
    };
  }

  @Test(dataProvider = "unsuccessfulLogins")
  public void unsuccessfulLoginTest(String name, String password, String expectedResult) {
    homePage.navigateToHomePage();
    loginPage.enterUserName(name);
    loginPage.enterPassword(password);
    loginPage.clickLoginButton();

    Assert.assertTrue(loginPage.errorMessageIsPresent(expectedResult));
  }

  @Test
  public void createIssueTest() {
    homePage.navigateToHomePage();
    loginPage.enterUserName("RuslanaChumachenko");
    loginPage.enterPassword("RuslanaChumachenko");
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
  public void addCommentForTicketTest() {
    //login to home page
    homePage.navigateToHomePage();
    loginPage.enterUserName("RuslanaChumachenko");
    loginPage.enterPassword("RuslanaChumachenko");
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
