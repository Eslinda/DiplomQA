package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;

import static org.hamcrest.CoreMatchers.not;

import android.os.SystemClock;

import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.Elements.MainScreen;

public class MainSteps {

    public static void isMainScreen() {
        Allure.step("Проверка что это главный экран");
        MainScreen.allNewsButton.check(matches(isDisplayed()));
        MainScreen.allClaimsButton.check(matches(isDisplayed()));
    }

    public static void expandAllNews() {
        Allure.step("Развернуть новости");
        MainScreen.expandHideNewsButton.check(matches(isDisplayed()));
        MainScreen.expandHideNewsButton.perform(click());
    }

    public static void expandAllClaims() {
        Allure.step("Развернуть заявки");
        MainScreen.expandHideClaimsButton.check(matches(isDisplayed()));
        MainScreen.expandHideClaimsButton.perform(click());
    }

    public static void newsNotDisplayed() {
        Allure.step("Проверка новости скрыты");
        MainScreen.allNewsButton.check(matches(not(isDisplayed())));
    }

    public static void newsDisplayed() {
        Allure.step("Проверка новости отображаются");
        MainScreen.allNewsButton.check(matches(isDisplayed()));
    }

    public static void claimsNotDisplayed() {
        Allure.step("Проверка заявки скрыты");
        MainScreen.allClaimsButton.check(matches(not(isDisplayed())));
    }

    public static void claimsDisplayed() {
        Allure.step("Проверка заявки отображаются");
        MainScreen.allClaimsButton.check(matches(isDisplayed()));
    }

    public static void openAllNews() {
        Allure.step("Открыть все новости");
        MainScreen.allNewsButton.check(matches(isDisplayed()));
        MainScreen.allNewsButton.perform(click());
    }

    public static void openAllClaims() {
        Allure.step("Открыть все заявки");
        MainScreen.allClaimsButton.check(matches(isDisplayed()));
        MainScreen.allClaimsButton.perform(click());
    }

    public static void createClaim() {
        Allure.step("Создать заявку");
        MainScreen.addNewClaimButton.perform(click());
        SystemClock.sleep(1000);
    }

    public static void expandOneNews() {
        Allure.step("Развернуть одну новость");
        MainScreen.expandOneNews.perform(actionOnItemAtPosition(0, click()));
        MainScreen.descriptionNews.check(matches(isDisplayed()));
    }

    public static void hideOneNews() {
        Allure.step("Закрыть одну новость");
        MainScreen.categoryIcon.perform(click());
        MainScreen.descriptionNewsAfterClosing.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    public static void openOneClaim() {
        Allure.step("Развернуть одну заявку");
        MainScreen.firstClaimExecutor.perform(click());
    }

}
