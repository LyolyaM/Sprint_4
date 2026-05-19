import Page.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;


public class BestUiTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    @Before
    public void startBrowser() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        mainPage  = new MainPage(driver);
    }

    protected void acceptCookies() {
        try {
            WebElement cookieButton = driver.findElement(By.id("rcc-confirm-button"));
            if (cookieButton.isDisplayed()) {
                cookieButton.click();
            }
        } catch (Exception e) {
            System.out.println("Куки баннер не найден");
        }
    }

    @After
    public void tearDown() {
            driver.quit();
        }
    }


