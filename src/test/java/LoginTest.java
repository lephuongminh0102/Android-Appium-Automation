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

public class LoginTest {
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
    public void TC01_LoginEmptyBoth() throws Exception {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnLogin")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng nhập tài khoản và mật khẩu");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void TC02_LoginEmptyPassword() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtUserName")
                )
        ).sendKeys("user2");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnLogin")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng nhập tài khoản và mật khẩu");
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void TC03_LoginEmptyUsername() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtUserName")
                )
        ).clear();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Show password")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtPassword")
                )
        ).sendKeys("Pass@123");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnLogin")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng nhập tài khoản và mật khẩu");
        Thread.sleep(2000);
    }
    @Test(priority = 4)
    public void TC04_LoginWrongBoth() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtUserName")
                )
        ).sendKeys("wrong_name");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtPassword")
                )
        ).sendKeys("wrong_pass");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnLogin")
                )
        ).click();
        Assert.assertEquals(getToast(), "Tài khoản hoặc mật khẩu không đúng");
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void TC05_LoginWrongUsername() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtPassword")
                )
        ).sendKeys("Pass@123");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnLogin")
                )
        ).click();
        Assert.assertEquals(getToast(), "Tài khoản hoặc mật khẩu không đúng");
        Thread.sleep(2000);
    }
    @Test(priority = 6)
    public void TC06_LoginWrongPassword() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtUserName")
                )
        ).sendKeys("user2");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtPassword")
                )
        ).sendKeys("wrong_pass");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnLogin")
                )
        ).click();
        Assert.assertEquals(getToast(), "Tài khoản hoặc mật khẩu không đúng");
        Thread.sleep(2000);
    }
    @Test(priority = 7)
    public void TC07_LoginSuccess() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtPassword")
        )).sendKeys("Pass@123");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnLogin")
                )
        ).click();
        WebElement home = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/navHome")
                )
        );
        Assert.assertTrue(home.isDisplayed());
    }

    public String getToast() {
        for (int i = 0; i < 5; i++) {
            try {
                WebElement toast = driver.findElement(
                        AppiumBy.xpath("//android.widget.Toast"));
                return toast.getText();
            } catch (Exception e) {

            }
        }
        return "";
    }
}
