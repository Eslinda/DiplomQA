package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

import static ru.iteco.fmhandroid.ui.data.MainHelper.withIndex;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.MainScreen;
import ru.iteco.fmhandroid.ui.Elements.NewsScreen;
import ru.iteco.fmhandroid.ui.data.MainHelper;

public class NewsSteps {

    public static void isNewsScreen() {
        Allure.step("Проверка что это раздел новостей");
        NewsScreen.news.check(matches(isDisplayed()));
        NewsScreen.sortNewsButton.check(matches(isDisplayed()));
    }

    public static void expandNews() {
        Allure.step("Развернуть одну новость");
        NewsScreen.expandOneNews.perform(actionOnItemAtPosition(0, click()));
        NewsScreen.descriptionNews.check(matches(isDisplayed()));
    }

    public static void hideNews() {
        Allure.step("Закрыть одну новость");
        MainScreen.categoryIcon.perform(click());
        MainScreen.descriptionNewsAfterClosing.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    public static void openFilter() {
        Allure.step("Открыть фильтр");
        NewsScreen.filterNewsButton.check(matches(isDisplayed()));
        NewsScreen.filterNewsButton.perform(click());
    }
    public static void clickSortButton() {
        Allure.step("Нажать кнопку сортировки");
        NewsScreen.sortNewsButton.check(matches(isDisplayed()));
        NewsScreen.sortNewsButton.perform(click());
    }

    public static String getFirstNewsTitle(int position) {
        Allure.step("Получить заголовок первой новости");
        return MainHelper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public static String getFirstNewsTitleAfterSecondSorting(int position) {
        Allure.step("Получить заголовок первой новости после сортировки");
        return MainHelper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), position)));
    }

    public static void newsListLoaded() {
        Allure.step("Дождаться загрузки списка новостей");
        MainHelper.waitElement(withId(R.id.news_list_recycler_view), 10000);
    }

    public static void checkFirstNewsPublicationDate(String date) {
        Allure.step("Проверка даты публикации первой новости");
        String firstNewsPublicationDate = MainHelper.Text.getText(onView(withIndex(withId(R.id.news_item_date_text_view), 0)));
        assertEquals(firstNewsPublicationDate, date);
    }

    public static void clickEditButton() {
        Allure.step("Нажать кнопку редактирования");
        NewsScreen.editNewsButton.check(matches(isDisplayed()));
        NewsScreen.editNewsButton.perform(click());
    }

}
