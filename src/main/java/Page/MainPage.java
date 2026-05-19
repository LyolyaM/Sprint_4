package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;


    private final By cookieButton = By.id("rcc-confirm-button");
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g']");
    private final By yandexLogo = By.xpath("//a[contains(@href, 'yandex')]//img");
    private final By[] questionLocators = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    };

    private final By[] answerLocators = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public MainPage open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }

    public MainPage acceptCookies() {
        try {
            WebElement button = driver.findElement(cookieButton);
            if (button.isDisplayed()) {
                button.click();
                Thread.sleep(500); // небольшая пауза после клика по куки
            }
        } catch (Exception e) {
            System.out.println("Куки баннер не найден");
        }
        return this;
    }

    public MainPage scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        try {
            Thread.sleep(1000); // ждём подгрузки всех элементов
        } catch (InterruptedException e) {
            System.out.println("Ошибка при паузе: " + e.getMessage());
        }
        return this;
    }

    public String getQuestionText(int index) {
        return driver.findElement(questionLocators[index]).getText();
    }

    public MainPage clickQuestion(int index) {
        WebElement question = driver.findElement(questionLocators[index]);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        question.click();
        return this;
    }

    public String getAnswerText(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocators[index]));
        return driver.findElement(answerLocators[index]).getText();
    }

    public boolean isAnswerDisplayed(int index) {
        try {
            // Ждём появления ответа
            wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocators[index]));
            return driver.findElement(answerLocators[index]).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getQuestionsCount() {
        return questionLocators.length;
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public MainPage clickYandexLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(yandexLogo)).click();
        return this;
    }


    public void switchToNewWindow() {

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }


    public boolean isCurrentUrlContains(String expectedUrl) {
        return driver.getCurrentUrl().contains(expectedUrl);
    }


    public void closeCurrentWindowAndSwitchToMain() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        String mainTab = tabs.get(0);  // первое окно - главное
        driver.close();  // закрываем текущее окно
        driver.switchTo().window(mainTab);  // возвращаемся на главное
    }
}