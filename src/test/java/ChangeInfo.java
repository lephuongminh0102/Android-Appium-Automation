import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class ChangeInfo {
    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("cntt.cuoiky");
        options.setAppActivity("cntt.cuoiky.Activity.LoginActivity");
        options.setNoReset(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.terminateApp("cntt.cuoiky");
        driver.activateApp("cntt.cuoiky");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void TC01_ChangeInfo() throws Exception {
        Login("user3", "Pass@123");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/navProfile")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnEditProfile")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etEditName")
                )
        ).sendKeys("Lê Phương Minh");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etEditAddress")
                )
        ).sendKeys("54 Triều Khúc, Thanh Xuân, Hà Nội");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        String error = wait.until(
                driver -> driver.findElement(
                        AppiumBy.xpath("//android.widget.Toast")
                ).getText()
        );
        Assert.assertEquals(error, "Cập nhật thành công");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 2)
    public void TC02_ChangePassword() throws Exception {
        Login("user4", "Pass@abc");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/navProfile")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().text(\"\uD83D\uDD11 Đổi mật khẩu\")")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etOldPassword")
                )
        ).sendKeys("Pass@abc");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etNewPassword")
                )
        ).sendKeys("Pass@123");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etConfirmNewPassword")
                )
        ).sendKeys("Pass@123");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        String error = wait.until(
                driver -> driver.findElement(
                        AppiumBy.xpath("//android.widget.Toast")
                ).getText()
        );
        Assert.assertEquals(error, "Đổi mật khẩu thành công");
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void TC03_DeleteAll() throws Exception {
        Login("admin", "admin");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/navAdminUser")
                )
        ).click();
        List<WebElement> roles = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        AppiumBy.className("android.widget.TextView")
                )
        );
        for (WebElement role : roles) {
            String text = role.getText();
            if (text.equalsIgnoreCase("Vai trò: user")) {

            }
        }
    }

    public void Login(String username, String password) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtUserName")
                )
        ).sendKeys(username);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtPassword")
                )
        ).sendKeys(password);
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnLogin")
                )
        ).click();
    }
}
