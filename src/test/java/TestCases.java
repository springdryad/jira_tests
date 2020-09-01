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
    WebDriverFactory.createInstance("chrome");
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
        {"RuslanaChumachenko", "wrong_password", "Извините, имя пользователя или пароль неверны - пожалуйста, попробуйте еще раз."},
        {"wrong_username", "RuslanaChumachenko", "Извините, имя пользователя или пароль неверны - пожалуйста, попробуйте еще раз."},
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

    createIssueWindow.enterProjectField("Webinar");
    createIssueWindow.enterIssueTypeField("Task");
    createIssueWindow.enterSummary("Ruslana's task #3");
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

  @Test
  public void cancelCreateIssueAcceptAlert(){
    //login to home page
    homePage.navigateToHomePage();
    loginPage.enterUserName("RuslanaChumachenko");
    loginPage.enterPassword("RuslanaChumachenko");
    loginPage.clickLoginButton();

    //click Create Issue button and enter summary
    homePage.isCreateIssueButtonPresent();
    homePage.clickCreateIssue();
    createIssueWindow.enterSummary("Ruslana's task #3");

    //click Cancel button and accept alert
    createIssueWindow.clickCancelButton();
    createIssueWindow.acceptAlert();

    // make sure that Create Issue window disappeared
    Assert.assertTrue(createIssueWindow.isCreateIssueWindowDisappear());
  }

  @Test
  public void cancelCreateIssueDismissAlert(){
    //login to home page
    homePage.navigateToHomePage();
    loginPage.enterUserName("RuslanaChumachenko");
    loginPage.enterPassword("RuslanaChumachenko");
    loginPage.clickLoginButton();

    //click Create Issue button and enter summary
    homePage.isCreateIssueButtonPresent();
    homePage.clickCreateIssue();
    createIssueWindow.enterSummary("Ruslana's task #3");

    //click Cancel button and dismiss alert
    createIssueWindow.clickCancelButton();
    createIssueWindow.dismissAlert();

    // make sure that Create Issue window did not disappear
    Assert.assertTrue(createIssueWindow.isCreateIssueWindowDoesNotDisappear());
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}
