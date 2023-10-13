package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;


import android.os.SystemClock;

import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.NewsControlPanel;
import ru.iteco.fmhandroid.ui.data.MainHelper;

public class NewsControlPanelSteps {

    public static void isControlPanelScreen() {
        Allure.step("Проверка что это Control panel");
        NewsControlPanel.newsControlPanel.check(matches(isDisplayed()));
        NewsControlPanel.addNews.check(matches(isDisplayed()));
    }

    public static void openNewsFilterScreen() {
        Allure.step("Открыть окно расширенного фильтра");
        NewsControlPanel.newsFilterButton.perform(click());
    }

    public static void checkNewsImage() {
        Allure.step("Проверка картинки с бабочкой вкладки News");
        NewsControlPanel.butterflyImageNews.check(matches(isDisplayed()));
    }

    public static void checkEmptyScreen() {
        Allure.step("Проверка элементов пустого экрана");
        NewsControlPanel.emptyWarning.check(matches(isDisplayed()));
        NewsControlPanel.refreshButton.check(matches(isDisplayed()));
    }

    public static void createNewsButton() {
        Allure.step("Нажать кнопку создания новости");
        NewsControlPanel.addNews.perform(click());
    }

    public static void isCreatingNewsScreen() {
        Allure.step("Проверка что это экран Creating News");
        NewsControlPanel.creatingNewsScreenName.check(matches(isDisplayed()));
        NewsControlPanel.categoryListNews.check(matches(isDisplayed()));
        NewsControlPanel.newsTitle.check(matches(isDisplayed()));
        NewsControlPanel.dateNews.check(matches(isDisplayed()));
        NewsControlPanel.timeNews.check(matches(isDisplayed()));
        NewsControlPanel.descriptionCreateNews.check(matches(isDisplayed()));
    }

    public static void fillInNewsCategory(String category) {
        Allure.step("Заполнить поле категория");
        NewsControlPanel.categoryListNews.perform(replaceText(category));
    }

    public static void fillInNewsTitle(String title) {
        Allure.step("Заполнить поле заголовок");
        NewsControlPanel.newsTitle.perform(replaceText(title));
    }

    public static void fillInPublicationDate(String date) {
        Allure.step("Заполнить поле дата публикации");
        NewsControlPanel.dateNews.perform(replaceText(date));
    }

    public static void fillInTime(String time) {
        Allure.step("Заполнить поле время");
        NewsControlPanel.timeNews.perform(replaceText(time));
    }

    public static void fillInNewsDescription(String description) {
        Allure.step("Заполнить поле описание");
        NewsControlPanel.descriptionCreateNews.perform(replaceText(description));
    }

    public static void createNews(String category, String title, String publicationDate, String publicationTime, String description) {
        Allure.step("Создать новость");
        fillInNewsCategory(category);
        fillInNewsTitle(title);
        fillInPublicationDate(publicationDate);
        fillInTime(publicationTime);
        fillInNewsDescription(description);
    }

    public static void clickSave() {
        Allure.step("Нажать сохранить (SAVE)");
        NewsControlPanel.saveButton.perform(click());
    }

    public static void editNews(String tittle) {
        Allure.step("Открыть новость для редактирования");
        scrollAndClickToNewsWithTittle(tittle);
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(tittle))))
                .perform(MainHelper.clickChildViewWithId(R.id.edit_news_item_image_view));
    }

    public static void editNewsDescriptionText(String newDescriptionText) {
        Allure.step("Отредактировать описание у новости");
        fillInNewsDescription(newDescriptionText);
    }

    public static void deleteNews(String title) {
        Allure.step("Удалить новость");
        scrollAndClickToNewsWithTittle(title);
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.news_item_material_card_view), hasDescendant(withText(title))))
                .perform(MainHelper.clickChildViewWithId(R.id.delete_news_item_image_view));
    }

    public static void scrollAndClickToNewsWithTittle(String title) {
        Allure.step("Прокрутка до новости с выбранным заголовком и нажатие на нее");
        MainHelper.waitElement(withId(R.id.news_list_recycler_view), 10000);
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(allOf(withText(title)))));
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(isDisplayed()))
                .perform(actionOnItem(hasDescendant(withText(title)), click()));
    }

    public static void checkAttributesNews(String title, String date, String time, String description) {
        Allure.step("Проверка что атрибуты новости соответствуют заданным при создании");
        onView(withText(title)).check(matches(isDisplayed()));
        onView(withText(date)).check(matches(isDisplayed()));
        onView(withText(time)).check(matches(isDisplayed()));
        onView(withText(description)).check(matches(isDisplayed()));
    }

    public static void clickOkButton() {
        Allure.step("Нажать ок");
        MainHelper.waitElement((withText("OK")), 1000);
        onView(withText("OK")).perform(click());
    }

    public static void confirmDeleting() {
        Allure.step("Подтвердить удаление");
        NewsControlPanel.deleteDialog.check(matches(isDisplayed()));
        clickOkButton();
    }

    public static void changeStatusNewsToNotActive(String tittle) {
        Allure.step("Изменить статус новости на Неактивна");
        NewsControlPanel.switcherNews.perform(click());
    }

    public static void checkNotActiveStatus() {
        Allure.step("Проверка что статус новости Неактивна");
        onView(withText("Not active")).check(matches(isDisplayed()));
    }

}
