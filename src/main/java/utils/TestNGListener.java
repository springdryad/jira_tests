package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;

public class TestNGListener implements ITestListener {

  @Override
  public void onTestStart(ITestResult result) {
//    String browserName = result.getTestContext().getCurrentXmlTest().getParameter("browserName");
//    WebDriverFactory.createInstance(browserName);


  }

  @Override
  public void onTestSuccess(ITestResult result) {

  }

  @Override
  public void onTestFailure(ITestResult result) {
    File screenshotsFolder = new File(System.getProperty("user.dir") + "/screenshots");

    if (!screenshotsFolder.exists()) {
      screenshotsFolder.mkdir();
    }

    File screenshot = captureScreenshot();
    Path pathToScreenShot = Paths.get(screenshot.getPath());
    try {
      String screenshotName = screenshotsFolder + "/" + "Screenshot_" + java.time.LocalTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss")) + ".png";
      Files.copy(pathToScreenShot, Paths.get(screenshotName), StandardCopyOption.COPY_ATTRIBUTES);
    } catch (IOException e) {
      e.printStackTrace();
    }
    WebDriverFactory.getDriver().quit();

  }



  @Override
  public void onTestSkipped(ITestResult result) {

  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

  }

  @Override
  public void onStart(ITestContext context) {
//    String browserName = context.getCurrentXmlTest().getParameter("browserName");
//    WebDriverFactory.createInstance(browserName);
  }

  @Override
  public void onFinish(ITestContext context) {
    //WebDriverFactory.getDriver().quit();
  }

  private File captureScreenshot() {
    return ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
  }
}
