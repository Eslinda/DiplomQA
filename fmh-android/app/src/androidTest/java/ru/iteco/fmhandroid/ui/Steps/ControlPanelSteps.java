package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.Elements.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.Elements.ControlPanel;
import ru.iteco.fmhandroid.ui.Elements.QuotesScreen;

public class ControlPanelSteps {

    public static void logOut() throws InterruptedException {
        Allure.step("Выход из системы");

        Thread.sleep(1000);
        ControlPanel.logOutMenu.perform(click());
        ControlPanel.logOutButton.perform(click());
        Thread.sleep(2000);
        AuthorizationScreen.authorization.check(matches(isDisplayed()));
    }

    public static void goToNewsPage() {
        Allure.step("Переход на экран новостей через меню");
        SystemClock.sleep(5000);
        ControlPanel.menuButton.perform(click());
        ControlPanel.newsOfMenu.check(matches(isDisplayed()));
        ControlPanel.newsOfMenu.perform(click());
    }

    public static void goToClaimsPage() {
        Allure.step("Переход на экран заявок через меню");
        ControlPanel.menuButton.perform(click());
        ControlPanel.claimsOfMenu.check(matches(isDisplayed()));
        ControlPanel.claimsOfMenu.perform(click());
    }

    public static void goToAboutPage() {
        Allure.step("Переход на экран о приложении через меню");
        ControlPanel.menuButton.perform(click());
        ControlPanel.aboutOfMenu.check(matches(isDisplayed()));
        ControlPanel.aboutOfMenu.perform(click());
    }

    public static void goToMainPage() {
        Allure.step("Переход на главный экран через меню");
        ControlPanel.menuButton.perform(click());
        ControlPanel.mainOfMenu.check(matches(isDisplayed()));
        ControlPanel.mainOfMenu.perform(click());
    }

    public static void goToMissionPage() {
        Allure.step("Переход на экран с цитатами");
        ControlPanel.missionButton.perform(click());
        QuotesScreen.title.check(matches(isDisplayed()));
    }

    public static void checkThatAllItemsOfMenuAreDisplayed() {
        Allure.step("Провека, что видны все пункты меню");
        ControlPanel.mainOfMenu.check(matches(isDisplayed()));
        ControlPanel.claimsOfMenu.check(matches(isDisplayed()));
        ControlPanel.newsOfMenu.check(matches(isDisplayed()));
        ControlPanel.aboutOfMenu.check(matches(isDisplayed()));
    }
}
