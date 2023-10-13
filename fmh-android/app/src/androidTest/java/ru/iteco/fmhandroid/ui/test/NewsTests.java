package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.action.ViewActions.swipeDown;

import static org.junit.Assert.assertEquals;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Elements.NewsFilterScreen;
import ru.iteco.fmhandroid.ui.Elements.NewsScreen;
import ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.Steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.Steps.MainSteps;
import ru.iteco.fmhandroid.ui.Steps.NewsControlPanelSteps;
import ru.iteco.fmhandroid.ui.Steps.NewsFilterSteps;
import ru.iteco.fmhandroid.ui.Steps.NewsSteps;
import ru.iteco.fmhandroid.ui.data.MainHelper;
import ru.iteco.fmhandroid.ui.data.Resources;

@RunWith(AllureAndroidJUnit4.class)
public class NewsTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        Thread.sleep(3000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            ControlPanelSteps.goToNewsPage();
            return;
        }
        AuthorizationSteps.enterLogin(Resources.correctLogin);
        AuthorizationSteps.enterPassword(Resources.correctPassword);
        AuthorizationSteps.clickButtonSingIn();
        ControlPanelSteps.goToNewsPage();
    }

    @Test
    @DisplayName("Развернуть и свернуть новость")
    public void shouldShowNewsContent() {
        NewsSteps.expandNews();
        NewsSteps.hideNews();
    }

    @Test
    @DisplayName("Проверка сортировки новостей")
    public void shouldSortNews() {
        int position = 0;
        String firstNewsTitle = NewsSteps.getFirstNewsTitle(position);
        NewsSteps.clickSortButton();
        NewsScreen.listAllNews.perform(swipeDown());
        NewsSteps.clickSortButton();
        NewsScreen.listAllNews.perform(swipeDown());
        NewsSteps.newsListLoaded();
        String firstNewsTitleAfterSecondSorting = NewsSteps.getFirstNewsTitleAfterSecondSorting(position);
        assertEquals(firstNewsTitle, firstNewsTitleAfterSecondSorting);
    }

    @Test
    @DisplayName("Фильтрация по дате")
    public void shouldFilterByDate() {
        String newsDate = MainHelper.generateDate(0);
        NewsSteps.openFilter();
        NewsFilterSteps.isFilterScreen();
        NewsFilterSteps.enterStartDate(newsDate);
        NewsFilterSteps.enterEndDate(newsDate);
        NewsFilterSteps.clickFilter();
        NewsSteps.checkFirstNewsPublicationDate(newsDate);
    }

    @Test
    @DisplayName("Отмена фильтрации")
    public void shouldCancelFilter() {
        String newsDate = MainHelper.generateDate(0);
        NewsSteps.openFilter();
        NewsFilterSteps.isFilterScreen();
        NewsFilterSteps.enterStartDate(newsDate);
        NewsFilterSteps.clickCancel();
        NewsSteps.isNewsScreen();
    }

    @Test
    @DisplayName("Перейти в панель управления")
    public void shouldTransferToControlPanel() {
        NewsSteps.clickEditButton();
        NewsControlPanelSteps.isControlPanelScreen();
    }

    @Test
    @DisplayName("Проверка чек-боксов фильтра в панели управления")
    public void shouldOpenFilter() {
        NewsSteps.clickEditButton();
        NewsControlPanelSteps.openNewsFilterScreen();
        NewsFilterSteps.clickOnActiveCheckBox();
        NewsFilterSteps.checkActiveCheckBoxStatus(false);
        NewsFilterSteps.clickOnNotActiveCheckBox();
        NewsFilterSteps.checkNotActiveCheckBoxStatus(false);
    }

    @Test
    @DisplayName("Фильтрация новостей через панель управления по статусу")
    public void shouldCheckFilteredNewsStatus() {
        NewsSteps.clickEditButton();
        NewsControlPanelSteps.openNewsFilterScreen();
        NewsFilterSteps.clickOnNotActiveCheckBox();
        NewsFilterSteps.clickFilter();
        NewsSteps.newsListLoaded();
        NewsFilterSteps.checkActiveNewsStatus();
        NewsControlPanelSteps.openNewsFilterScreen();
        NewsFilterSteps.clickOnActiveCheckBox();
        NewsFilterSteps.clickFilter();
        NewsSteps.newsListLoaded();
        NewsFilterSteps.checkNotActiveNewsStatus();
    }

    @Test
    @DisplayName("Проверка отсутствия новостей, соответствующих критериям, в панели управления")
    public void shouldShowNothingToShowScreenInControlPanel() {
        String newsDate = MainHelper.generateDate(+2);
        NewsSteps.openFilter();
        NewsFilterSteps.isFilterScreen();
        NewsFilterSteps.enterStartDate(newsDate);
        NewsFilterSteps.enterEndDate(newsDate);
        NewsFilterSteps.clickFilter();
        NewsControlPanelSteps.checkNewsImage();
        NewsControlPanelSteps.checkEmptyScreen();
    }
    @Test
    @DisplayName("Отмена фильтрации новостей через панель управления")
    public void shouldCancelFiltering() {
        NewsSteps.clickEditButton();
        NewsControlPanelSteps.openNewsFilterScreen();
        NewsFilterSteps.clickCancel();
        NewsControlPanelSteps.isControlPanelScreen();
    }

    @Test
    @DisplayName("Успешное создание новости")
    public void shouldCreateNews() {
        String titleText = MainHelper.getRandomNewsTitle();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String newsDate = MainHelper.generateDate(0);
        String newsTime = MainHelper.generateTime(0);
        NewsSteps.clickEditButton();
        NewsControlPanelSteps.createNewsButton();
        NewsControlPanelSteps.isCreatingNewsScreen();
        NewsControlPanelSteps.createNews(MainHelper.randomCategory(), titleText, newsDate, newsTime, descriptionText);
        NewsControlPanelSteps.clickSave();
        NewsControlPanelSteps.scrollAndClickToNewsWithTittle(titleText);
        NewsControlPanelSteps.editNews(titleText);
        NewsControlPanelSteps.checkAttributesNews(titleText, newsDate, newsTime, descriptionText);
    }

    @Test
    @DisplayName("Успешное удаление новости")
    public void shouldDeleteNews() {
        String titleText = MainHelper.getRandomNewsTitle();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String newsDate = MainHelper.generateDate(0);
        String newsTime = MainHelper.generateTime(0);
        NewsSteps.clickEditButton();
        NewsControlPanelSteps.createNewsButton();
        NewsControlPanelSteps.isCreatingNewsScreen();
        NewsControlPanelSteps.createNews(MainHelper.randomCategory(), titleText, newsDate, newsTime, descriptionText);
        NewsControlPanelSteps.clickSave();
        NewsControlPanelSteps.scrollAndClickToNewsWithTittle(titleText);
        NewsControlPanelSteps.deleteNews(titleText);
        NewsControlPanelSteps.confirmDeleting();
    }

    @Test
    @DisplayName("Успешное создание новости на латинице")
    public void shouldCreateNewsLat() {
        String titleText = MainHelper.getRandomNewsTitleLat();
        String descriptionText = MainHelper.getRandomNewsDescriptionLat();
        String newsDate = MainHelper.generateDate(0);
        String newsTime = MainHelper.generateTime(0);
        NewsSteps.clickEditButton();
        NewsControlPanelSteps.createNewsButton();
        NewsControlPanelSteps.isCreatingNewsScreen();
        NewsControlPanelSteps.createNews(MainHelper.randomCategory(), titleText, newsDate, newsTime, descriptionText);
        NewsControlPanelSteps.clickSave();
        NewsControlPanelSteps.scrollAndClickToNewsWithTittle(titleText);
        NewsControlPanelSteps.editNews(titleText);
        NewsControlPanelSteps.checkAttributesNews(titleText, newsDate, newsTime, descriptionText);
    }

    @Test
    @DisplayName("Редактирование описания новости")
    public void shouldChangeNewsDescription() {
        String titleText = MainHelper.getRandomNewsTitle();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String newsDate = MainHelper.generateDate(0);
        String newsTime = MainHelper.generateTime(0);
        String newDescriptionText = MainHelper.getRandomNewsDescription();
        NewsSteps.clickEditButton();
        NewsControlPanelSteps.createNewsButton();
        NewsControlPanelSteps.isCreatingNewsScreen();
        NewsControlPanelSteps.createNews(MainHelper.randomCategory(), titleText, newsDate, newsTime, descriptionText);
        NewsControlPanelSteps.clickSave();
        NewsControlPanelSteps.scrollAndClickToNewsWithTittle(titleText);
        NewsControlPanelSteps.editNews(titleText);
        NewsControlPanelSteps.editNewsDescriptionText(newDescriptionText);
        NewsControlPanelSteps.clickSave();
        NewsControlPanelSteps.scrollAndClickToNewsWithTittle(titleText);
        NewsControlPanelSteps.editNews(titleText);
        NewsControlPanelSteps.checkAttributesNews(titleText, newsDate, newsTime, newDescriptionText);
    }

    @Test
    @DisplayName("Изменение статуса новости на неактивна")
    public void shouldChangeNewsStatus() {
        String titleText = MainHelper.getRandomNewsTitle();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String newsDate = MainHelper.generateDate(0);
        String newsTime = MainHelper.generateTime(0);
        NewsSteps.clickEditButton();
        NewsControlPanelSteps.createNewsButton();
        NewsControlPanelSteps.isCreatingNewsScreen();
        NewsControlPanelSteps.createNews(MainHelper.randomCategory(), titleText, newsDate, newsTime, descriptionText);
        NewsControlPanelSteps.clickSave();
        NewsControlPanelSteps.scrollAndClickToNewsWithTittle(titleText);
        NewsControlPanelSteps.editNews(titleText);
        NewsControlPanelSteps.changeStatusNewsToNotActive(titleText);
        NewsControlPanelSteps.clickSave();
        NewsControlPanelSteps.scrollAndClickToNewsWithTittle(titleText);
        NewsControlPanelSteps.editNews(titleText);
        NewsControlPanelSteps.checkNotActiveStatus();
    }
}
