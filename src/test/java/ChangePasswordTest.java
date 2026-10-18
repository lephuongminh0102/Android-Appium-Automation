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

public class ChangePasswordTest {
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
        Login("user4", "Pass@123");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void TC01_ChangePasswordEmptyOldPassword() throws Exception {
        InputChangePassword();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etOldPassword")
                )
        ).clear();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng điền đầy đủ thông tin");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void TC02_ChangePasswordEmptyNewPassword() throws Exception {
        InputChangePassword();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etNewPassword")
                )
        ).clear();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng điền đầy đủ thông tin");
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void TC03_ChangePasswordEmptyConfirmPassword() throws Exception {
        InputChangePassword();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etConfirmNewPassword")
                )
        ).clear();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng điền đầy đủ thông tin");
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void TC04_ChangePasswordWrongOldPassword() throws Exception {
        InputChangePassword();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etOldPassword")
                )
        ).sendKeys("wrong_pass");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Mật khẩu hiện tại không chính xác");
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void TC05_ChangePasswordConfirmMismatch() throws Exception {
        InputChangePassword();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etConfirmNewPassword")
                )
        ).sendKeys("not_same");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Mật khẩu mới không khớp");
        Thread.sleep(2000);
    }

    @Test(priority = 6)
    public void TC06_ChangePasswordSame() throws Exception {
        InputChangePassword();
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
        Assert.assertEquals(getToast(), "Mật khẩu mới không được trùng mật khẩu cũ");
        Thread.sleep(2000);
    }

    @Test(priority = 7)
    public void TC07_ChangePasswordShort() throws Exception {
        InputChangePassword();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etNewPassword")
                )
        ).sendKeys("Pass@ab");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etConfirmNewPassword")
                )
        ).sendKeys("Pass@ab");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Mật khẩu ít nhất 8 ký tự, gồm chữ hoa, thường và ký tự đặc biệt");
        Thread.sleep(2000);
    }

    @Test(priority = 8)
    public void TC08_ChangePasswordNoUppercase() throws Exception {
        InputChangePassword();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etNewPassword")
                )
        ).sendKeys("pass@abc");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etConfirmNewPassword")
                )
        ).sendKeys("pass@abc");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Mật khẩu ít nhất 8 ký tự, gồm chữ hoa, thường và ký tự đặc biệt");
        Thread.sleep(2000);
    }

    @Test(priority = 9)
    public void TC09_ChangePasswordNoSpecialChar() throws Exception {
        InputChangePassword();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etNewPassword")
                )
        ).sendKeys("Passabcd");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etConfirmNewPassword")
                )
        ).sendKeys("Passabcd");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Mật khẩu ít nhất 8 ký tự, gồm chữ hoa, thường và ký tự đặc biệt");
        Thread.sleep(2000);
    }

    @Test(priority = 10)
    public void TC010_ChangePasswordSuccess() throws Exception {
        InputChangePassword();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Đổi mật khẩu thành công");
        Thread.sleep(2000);
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
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/navProfile")
                )
        ).click();
    }

    public void InputChangePassword() throws Exception{
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().text(\"\uD83D\uDD11 Đổi mật khẩu\")")
                )
        ).click();
        List<WebElement> icons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        AppiumBy.id("cntt.cuoiky:id/text_input_end_icon")
                )
        );
        for (WebElement icon : icons){
            icon.click();
        }
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etOldPassword")
                )
        ).sendKeys("Pass@123");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etNewPassword")
                )
        ).sendKeys("Pass@abc");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/etConfirmNewPassword")
                )
        ).sendKeys("Pass@abc");
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
