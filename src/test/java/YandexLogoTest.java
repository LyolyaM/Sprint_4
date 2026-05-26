import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class YandexLogoTest extends BestUiTest{
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

