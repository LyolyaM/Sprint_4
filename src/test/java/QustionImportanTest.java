import Page.MainPage;
import Page.OrderPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class QustionImportanTest extends BestUiTest {


    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String color;
    private final String comment;


    public QustionImportanTest(String name, String surname, String address, String metroStation,
                               String phone, String deliveryDate, String rentalPeriod,
                               String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }


    @Parameterized.Parameters(name = "Тест заказа: {0} {1}")
    public static Object[][] getOrderData() {
        return new Object[][]{
                {
                        "Иван", "Петров", "ул. Ленина, д. 10", "Черкизовская",
                        "89001234567", "25.05.2026", "сутки", "black",
                        "Позвоните за 10 минут"
                },
                {
                        "Анна", "Иванова", "пр. Мира, д. 5", "Сокольники",
                        "89007654321", "30.05.2026", "трое суток", "grey",
                        "Домофон не работает"
                }
        };
    }


    private final String[] expectedAnswers = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };


    @Test
    public void questonTest() {

        mainPage.open();
        mainPage.acceptCookies();
        mainPage.scrollToBottom();


        for (int i = 0; i < mainPage.getQuestionsCount(); i++) {

            mainPage.clickQuestion(i);


            assertTrue("Ответ на вопрос " + i + " не отображается",
                    mainPage.isAnswerDisplayed(i));

            String actualAnswer = mainPage.getAnswerText(i);
            assertEquals("Текст ответа на вопрос " + i + " не совпадает",
                    expectedAnswers[i], actualAnswer);

            System.out.println("✓ Вопрос " + i + " пройден: " + actualAnswer);
        }
    }


    @Test
    public void orderTest() {

        mainPage.open();
        mainPage.acceptCookies();


        mainPage.clickOrderButton();


        OrderPage orderPage = new OrderPage(driver);


        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.selectMetro(metroStation);
        orderPage.enterPhone(phone);
        orderPage.clickNextButton();


        orderPage.selectDate(deliveryDate);
        orderPage.selectRentalPeriod(rentalPeriod);
        orderPage.selectColor(color);
        orderPage.enterComment(comment);
        orderPage.clickOrderButtonInForm();

        orderPage.confirmOrder();


        assertTrue("Заказ не был создан", orderPage.isOrderSuccess());

        System.out.println("✓ Заказ успешно создан!");
    }

    @Test
    public void yandexLogoTest() {

        mainPage.open();
        mainPage.acceptCookies();


        mainPage.clickYandexLogo();


        mainPage.switchToNewWindow();


        boolean isYandexPageOpened = mainPage.isCurrentUrlContains("dzen.ru") ||
                mainPage.isCurrentUrlContains("yandex.ru");

        assertTrue("Страница Яндекса не открылась", isYandexPageOpened);

        System.out.println("✓ Логотип Яндекса работает, открыта страница: " + driver.getCurrentUrl());


        mainPage.closeCurrentWindowAndSwitchToMain();

        System.out.println("✓ Тест логотипа Яндекса пройден!");
    }
}





