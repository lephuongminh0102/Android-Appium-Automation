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

public class CheckoutTest {
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
        Login("user3", "Pass@123");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void TC01_CheckoutNoProductSelected() throws Exception {
        GoToCart();
        ClearCart();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_checkout")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng chọn ít nhất một sản phẩm để thanh toán!");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 2)
    public void TC02_CheckoutEmptyName() throws Exception {
        GoToCart();
        Thread.sleep(500);
        if(!driver.findElements(AppiumBy.id("cntt.cuoiky:id/tv_empty_cart")).isEmpty()) {
            driver.navigate().back();
            AddProduct("Vinamilk ADM");
        }
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_checkout")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edt_ten")
                )
        ).clear();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_xacnhan")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng điền đầy đủ thông tin");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 3)
    public void TC03_CheckoutEmptyPhone() throws Exception {
        GoToCart();
        if(!driver.findElements(AppiumBy.id("cntt.cuoiky:id/tv_empty_cart")).isEmpty()) {
            driver.navigate().back();
            AddProduct("Vinamilk ADM");
        }
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_checkout")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edt_sdt")
                )
        ).clear();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_xacnhan")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng điền đầy đủ thông tin");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 4)
    public void TC04_CheckoutEmptyAddress() throws Exception {
        GoToCart();
        if(!driver.findElements(AppiumBy.id("cntt.cuoiky:id/tv_empty_cart")).isEmpty()) {
            driver.navigate().back();
            AddProduct("Vinamilk ADM");
        }
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_checkout")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edt_diachi")
                )
        ).clear();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_xacnhan")
                )
        ).click();
        Assert.assertEquals(getToast(), "Vui lòng điền đầy đủ thông tin");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 5)
    public void TC05_CheckoutPhoneNotStartWithZero() throws Exception {
        GoToCart();
        if(!driver.findElements(AppiumBy.id("cntt.cuoiky:id/tv_empty_cart")).isEmpty()) {
            driver.navigate().back();
            AddProduct("Vinamilk ADM");
        }
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_checkout")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edt_sdt")
                )
        ).sendKeys("1234567890");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_xacnhan")
                )
        ).click();
        Assert.assertEquals(getToast(), "Số điện thoại không hợp lệ");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 6)
    public void TC06_PhoneTooShort() throws Exception {
        GoToCart();
        if(!driver.findElements(AppiumBy.id("cntt.cuoiky:id/tv_empty_cart")).isEmpty()) {
            driver.navigate().back();
            AddProduct("Vinamilk ADM");
        }
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_checkout")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edt_sdt")
                )
        ).sendKeys("012345678");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_xacnhan")
                )
        ).click();
        Assert.assertEquals(getToast(), "Số điện thoại không hợp lệ");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 7)
    public void TC07_CheckoutPhoneTooLong() throws Exception {
        GoToCart();
        if(!driver.findElements(AppiumBy.id("cntt.cuoiky:id/tv_empty_cart")).isEmpty()) {
            driver.navigate().back();
            AddProduct("Vinamilk ADM");
        }
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_checkout")
                )
        ).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("cntt.cuoiky:id/edt_sdt")
                )
        ).sendKeys("01234567890");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_xacnhan")
                )
        ).click();
        Assert.assertEquals(getToast(), "Số điện thoại không hợp lệ");
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 8)
    public void TC08_CheckoutSuccess() throws Exception {
        GoToCart();
        if(!driver.findElements(AppiumBy.id("cntt.cuoiky:id/tv_empty_cart")).isEmpty()) {
            driver.navigate().back();
            AddProduct("Vinamilk ADM");
        }
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_checkout")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btn_xacnhan")
                )
        ).click();
        Assert.assertEquals(getToast(), "Đặt hàng thành công!");
        Thread.sleep(2000);
        driver.navigate().back();
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
                        AppiumBy.id("cntt.cuoiky:id/navCart")
                )
        ).click();
    }

    public void AddProduct(String productname) {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().text(\"" + productname + "\")")
                )
        ).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("cntt.cuoiky:id/btnBuyNow")
                )
        ).click();
    }

    public void GoToCart() throws Exception {
        if(driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().text(\"GIỎ HÀNG CỦA BẠN\")")).isEmpty()) {
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.id("cntt.cuoiky:id/navCart")
                    )
            ).click();
        }
        Thread.sleep(1000);
    }

    public void ClearCart() {
        if (driver.findElements(AppiumBy.id("cntt.cuoiky:id/tv_empty_cart")).isEmpty()) {
            while (driver.findElements(AppiumBy.id("cntt.cuoiky:id/btn_delete")).size() > 0) {
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                AppiumBy.id("cntt.cuoiky:id/btn_delete")
                        )
                ).click();
            }
        }
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
