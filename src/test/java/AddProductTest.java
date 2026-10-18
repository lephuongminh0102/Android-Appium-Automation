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

public class AddProductTest {
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
        login("admin", "admin");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void TC01_AddProductEmptyAll() throws Exception {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnAddProduct")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Lỗi nhập liệu");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 2)
    public void TC02_AddProductEmptyImage() throws Exception {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnAddProduct")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtName")
                )
        ).sendKeys("Vallac");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtPrice")
                )
        ).sendKeys("60000");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Lỗi nhập liệu");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 3)
    public void TC03_AddProductEmptyName() throws Exception {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnAddProduct")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnChonAnh")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(15)")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtPrice")
                )
        ).sendKeys("60000");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Lỗi nhập liệu");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 4)
    public void TC04_AddProductEmptyPrice() throws Exception {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnAddProduct")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnChonAnh")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(15)")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtName")
                )
        ).sendKeys("Vallac");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Lỗi nhập liệu");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 5)
    public void TC05_AddProductSuccess() throws Exception {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnAddProduct")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnChonAnh")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(15)")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtName")
                )
        ).sendKeys("Vallac");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edtPrice")
                )
        ).sendKeys("60000");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
        Assert.assertEquals(getToast(), "Cập nhật thành công");
        Thread.sleep(2000);
    }

    public void login(String username, String password) {
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
