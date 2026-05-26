import page.OrderPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends BestUiTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String color;
    private final String comment;
    private final String buttonLocation;

    public OrderTest(String name, String surname, String address, String metroStation,
                     String phone, String deliveryDate, String rentalPeriod,
                     String color, String comment, String buttonLocation) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
        this.buttonLocation = buttonLocation;
    }

    @Parameterized.Parameters(name = "Тест заказа: {0} {1}, кнопка: {9}")
    public static Object[][] getOrderData() {
        return new Object[][]{
                {
                        "Иван", "Петров", "ул. Ленина, д. 10", "Черкизовская",
                        "89001234567", "25.05.2026", "сутки", "black",
                        "Позвоните за 10 минут",
                        "top"
                },
                {
                        "Анна", "Иванова", "пр. Мира, д. 5", "Сокольники",
                        "89007654321", "30.05.2026", "трое суток", "grey",
                        "Домофон не работает",
                        "bottom"
                }
        };
    }

    @Test
    public void orderTest() {
        mainPage.open();
        mainPage.acceptCookies();

        mainPage.clickOrderButton(buttonLocation);

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

        assertTrue("Заказ не был создан для " + name + " " + surname,
                orderPage.isOrderSuccess());

        System.out.println("✓ Заказ успешно создан для: " + name + " " + surname +
                " (кнопка: " + buttonLocation + ")");
    }
}

