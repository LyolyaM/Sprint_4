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

        mainPage.closeCurrentWindowAndSwitchToMain();

    }
}

