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

public class RegisterTest {
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
    public void TC01_RegisterEmpty() throws Exception {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/tvRegisterAccount")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnDoRegister")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng điền đầy đủ các trường bắt buộc (*)");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void TC02_RegisterShort() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegUser")
                )
        ).sendKeys("user1");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Show password")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPass")
                )
        ).sendKeys("Pass@12");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPhone")
                )
        ).sendKeys("0123456789");
        driver.hideKeyboard();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnDoRegister")
                )
        ).click();
        Assert.assertEquals(getToast(), "Mật khẩu ít nhất 8 ký tự, gồm chữ hoa, thường và ký tự đặc biệt");
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void TC03_RegisterNoUppercase() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPass")
                )
        ).sendKeys("pass@123");
        driver.hideKeyboard();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnDoRegister")
                )
        ).click();
        Assert.assertEquals(getToast(), "Mật khẩu ít nhất 8 ký tự, gồm chữ hoa, thường và ký tự đặc biệt");
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void TC04_RegisterNoSpecialChar() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPass")
                )
        ).sendKeys("pass0123");
        driver.hideKeyboard();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnDoRegister")
                )
        ).click();
        Assert.assertEquals(getToast(), "Mật khẩu ít nhất 8 ký tự, gồm chữ hoa, thường và ký tự đặc biệt");
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void TC05_RegisterPhoneNotStartWithZero() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPass")
                )
        ).sendKeys("Pass@123");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPhone")
                )
        ).sendKeys("1234567890");
        driver.hideKeyboard();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnDoRegister")
                )
        ).click();
        Assert.assertEquals(getToast(), "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số");
        Thread.sleep(2000);
    }

    @Test(priority = 6)
    public void TC06_RegisterPhoneTooShort() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPhone")
                )
        ).sendKeys("012345678");
        driver.hideKeyboard();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnDoRegister")
                )
        ).click();
        Assert.assertEquals(getToast(), "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số");
        Thread.sleep(2000);
    }

    @Test(priority = 7)
    public void TC07_RegisterPhoneTooLong() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPhone")
                )
        ).sendKeys("01234567890");
        driver.hideKeyboard();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnDoRegister")
                )
        ).click();
        Assert.assertEquals(getToast(), "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số");
        Thread.sleep(2000);
    }

    @Test(priority = 8)
    public void TC08_RegisterSuccessEmptyAddress() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPhone")
                )
        ).sendKeys("0123456789");
        driver.hideKeyboard();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnDoRegister")
                )
        ).click();
        Assert.assertEquals(getToast(), "Đăng ký thành công!");
        Thread.sleep(2000);
    }

    @Test(priority = 9)
    public void TC09_RegisterDuplicateUsername() throws Exception {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/tvRegisterAccount")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegUser")
                )
        ).sendKeys("user1");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Show password")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPass")
                )
        ).sendKeys("Pass@123");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegPhone")
                )
        ).sendKeys("0123456789");
        driver.hideKeyboard();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnDoRegister")
                )
        ).click();
        Assert.assertEquals(getToast(), "Tên đăng nhập đã tồn tại");
        Thread.sleep(2000);
    }

    @Test(priority = 10)
    public void TC010_RegisterSuccess() throws Exception {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegUser")
                )
        ).sendKeys("user");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etRegAddress")
                )
        ).sendKeys("Hà Nội");
        driver.hideKeyboard();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnDoRegister")
                )
        ).click();
        Assert.assertEquals(getToast(), "Đăng ký thành công!");
        Thread.sleep(2000);
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
