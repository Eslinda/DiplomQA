package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.Elements.QuotesScreen;

public class QuotesSteps {

    public static void openFirstQuote() {
        Allure.step("Открыть первую цитату");
        QuotesScreen.openQuote.perform(click());
    }

    public static void closeFirstQuote() {
        Allure.step("Закрыть первую цитату");
        QuotesScreen.closeQuote.perform(click());
    }

    public static void checkThatFirstQuoteContentIsFull() {
        Allure.step("Проверка что первая цитата открыта");
        QuotesScreen.titleFirstQuote.check(matches(isDisplayed()));
        QuotesScreen.descriptionFirstQuote.check(matches(isDisplayed()));
    }

}
