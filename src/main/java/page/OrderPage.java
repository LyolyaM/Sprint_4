package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;

public class OrderPage {
    private WebDriver driver;


    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[contains(text(),'Далее')]");


    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.xpath("//div[@class='Dropdown-placeholder']");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By confirmButton = By.xpath("//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");


    private final By successMessage = By.xpath("//div[@class='Order_Modal__YZ-d3']//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    public OrderPage enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public OrderPage enterSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
        return this;
    }

    public OrderPage enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
        return this;
    }

    public OrderPage selectMetro(String metroStation) {
        WebElement metroInput = driver.findElement(metroField);
        metroInput.click();
        metroInput.sendKeys(metroStation);
        metroInput.sendKeys(Keys.ENTER);

        return this;
    }

    public OrderPage enterPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
        return this;
    }

    public OrderPage clickNextButton() {
        driver.findElement(nextButton).click();
        return this;
    }


    public OrderPage selectDate(String date) {
        WebElement dateInput = driver.findElement(dateField);
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ESCAPE);
        return this;
    }

    public OrderPage selectRentalPeriod(String period) {
        driver.findElement(rentalPeriodField).click();
        By periodOption = By.xpath("//div[contains(@class, 'Dropdown-option') and text()='" + period + "']");
        driver.findElement(periodOption).click();
        return this;
    }

    public OrderPage selectColor(String color) {
        By colorCheckbox = By.xpath("//input[@id='" + color + "']");
        driver.findElement(colorCheckbox).click();
        return this;
    }

    public OrderPage enterComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
        return this;
    }

    public OrderPage clickOrderButtonInForm() {
        driver.findElement(orderButton).click();
        return this;
    }

    public OrderPage confirmOrder() {
        driver.findElement(confirmButton).click();
        return this;
    }


    public boolean isOrderSuccess() {
        try {

            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
