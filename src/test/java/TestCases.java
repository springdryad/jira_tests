import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreateIssueWindow;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverFactory;

public class TestCases {

  private WebDriver driver;
  private LoginPage loginPage;
  private HomePage homePage;
  private CreateIssueWindow createIssueWindow;


  @BeforeMethod
  public void setUp() {
    WebDriverFactory.createInstance("Chrome");
    driver = WebDriverFactory.getDriver();
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
    createIssueWindow = new CreateIssueWindow(driver);
  }

  @Test
  public void createIssue() {
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

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }


}
