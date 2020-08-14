import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverFactory;

public class ParametersTest {

  private WebDriver driver;
  private HomePage homePage;
  private LoginPage loginPage;

  @Parameters("browserName")
  @BeforeMethod
  public void setUp(String browserName) {
    WebDriverFactory.createInstance(browserName);
    driver = WebDriverFactory.getDriver();
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
  }

  @Test
  public void successfulLogin() {
    homePage.navigateToHomePage();
    loginPage.enterUserName("RuslanaChumachenko");
    loginPage.enterPassword("RuslanaChumachenko");
    loginPage.clickLoginButton();

    Assert.assertTrue(homePage.findUserIcon());
  }


  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}
