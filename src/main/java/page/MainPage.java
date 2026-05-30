package page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;

public class MainPage {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;



    private final By cookieButton = By.id("rcc-confirm-button");
    private final By topOrderButton = By.xpath("//button[@class='Button_Button__ra12g']");
    private final By bottomOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
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

    }


    public MainPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public MainPage acceptCookies() {
        try {
            WebElement button = driver.findElement(cookieButton);
            if (button.isDisplayed()) {
                button.click();

            }
        } catch (Exception e) {
        }
        return this;
    }

    public MainPage scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        return this;
    }

    public void clickOrderButton(String buttonLocation) {
        if (buttonLocation.equals("top")) {
            driver.findElement(topOrderButton).click();
        } else {
            WebElement button = driver.findElement(bottomOrderButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
            button.click();
        }
    }

    public MainPage clickYandexLogo() {
        driver.findElement(yandexLogo).click();
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
        String mainTab = tabs.get(0);
        driver.close();
        driver.switchTo().window(mainTab);
    }

    public void clickQuestionByText(String questionText) {
        int index = getQuestionIndexByText(questionText);
        WebElement question = driver.findElement(questionLocators[index]);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        question.click();
    }


    private int getQuestionIndexByText(String questionText) {
        for (int i = 0; i < questionLocators.length; i++) {
            String currentQuestionText = driver.findElement(questionLocators[i]).getText();
            if (currentQuestionText.equals(questionText)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Вопрос не найден: " + questionText);
    }


    public String getAnswerByQuestionText(String questionText) {
        int index = getQuestionIndexByText(questionText);
        return driver.findElement(answerLocators[index]).getText();
    }


    public boolean isAnswerDisplayedByQuestionText(String questionText) {
        try {
            int index = getQuestionIndexByText(questionText);
            return driver.findElement(answerLocators[index]).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}