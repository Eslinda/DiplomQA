package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.Elements.NewsControlPanel;
import ru.iteco.fmhandroid.ui.Elements.NewsFilterScreen;

public class NewsFilterSteps {

    public static void isFilterScreen() {
        Allure.step("Проверка элементов экрана фильтр");
        NewsFilterScreen.filterScreenName.check(matches(isDisplayed()));
        NewsFilterScreen.categoryList.check(matches(isDisplayed()));
        NewsFilterScreen.publishDateStart.check(matches(isDisplayed()));
        NewsFilterScreen.publishDateEnd.check(matches(isDisplayed()));
        NewsFilterScreen.filterButton.check(matches(isClickable()));
    }

    public static void enterStartDate(String startDate) {
        Allure.step("Заполнить поле начальная дата");
        NewsFilterScreen.publishDateStart.perform(replaceText(startDate));
    }

    public static void enterEndDate(String endDate) {
        Allure.step("Заполнить поле конечная дата");
        NewsFilterScreen.publishDateEnd.perform(replaceText(endDate));
    }

    public static void clickFilter() {
        Allure.step("Нажать кнопку фильтр");
        NewsFilterScreen.filterButton.perform(click());
    }

    public static void clickCancel() {
        Allure.step("Нажать кнопку отмены (cancel)");
        NewsFilterScreen.cancelButton.perform(click());
    }

    public static void clickOnActiveCheckBox() {
        Allure.step("Нажать чек-бокс Active");
        NewsFilterScreen.checkboxActive.perform(click());
    }

    public static void clickOnNotActiveCheckBox() {
        Allure.step("Нажать чек-бокс Not active");
        NewsFilterScreen.checkboxNotActive.perform(click());
    }

    public static void checkActiveCheckBoxStatus(boolean checked) {
        Allure.step("Проверка нажатия чек-бокса Active");
        if (checked) {
            NewsFilterScreen.checkboxActive.check(matches(isChecked()));
        } else {
            NewsFilterScreen.checkboxActive.check(matches(isNotChecked()));
        }
    }

    public static void checkNotActiveCheckBoxStatus(boolean checked) {
        Allure.step("Проверка нажатия чек-бокса Not active");
        if (checked) {
            NewsFilterScreen.checkboxNotActive.check(matches(isChecked()));
        } else {
            NewsFilterScreen.checkboxNotActive.check(matches(isNotChecked()));
        }
    }

    public static void checkNotActiveNewsStatus() {
        Allure.step("Проверка статуса Not active");
        NewsControlPanel.newsStatusNotActive.check(matches(withText("Not active")));
    }

    public static void checkActiveNewsStatus() {
        Allure.step("Проверка статуса Active");
        NewsControlPanel.newsStatusActive.check(matches(withText("Active")));
    }

}
