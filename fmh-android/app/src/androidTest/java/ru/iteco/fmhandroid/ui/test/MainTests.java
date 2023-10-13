package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.action.ViewActions.click;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Elements.ClaimCreateEditScreen;
import ru.iteco.fmhandroid.ui.Elements.ControlPanel;
import ru.iteco.fmhandroid.ui.Steps.AboutSteps;
import ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.Steps.ClaimCreateEditSteps;
import ru.iteco.fmhandroid.ui.Steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.Steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.Steps.MainSteps;
import ru.iteco.fmhandroid.ui.Steps.NewsSteps;
import ru.iteco.fmhandroid.ui.data.MainHelper;
import ru.iteco.fmhandroid.ui.data.Resources;

@RunWith(AllureAndroidJUnit4.class)
public class MainTests {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        Thread.sleep(5000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.enterLogin(Resources.correctLogin);
        AuthorizationSteps.enterPassword(Resources.correctPassword);
        AuthorizationSteps.clickButtonSingIn();
    }

    @Test
    @DisplayName("Проверка видимости меню навигации на главном экране")
    public void shouldMenuElementsShouldBeVisible() {
        SystemClock.sleep(2000);
        ControlPanel.menuButton.perform(click());
        ControlPanelSteps.checkThatAllItemsOfMenuAreDisplayed();
    }


    @Test
    @DisplayName("Открытие экрана новостей с главного экрана")
    public void shouldOpenAllNews() {
        MainSteps.openAllNews();
        NewsSteps.isNewsScreen();
    }

   @Test
    @DisplayName("Открытие экрана заявок с главного экрана")
    public void shouldOpenAllClaims() {
       MainSteps.openAllClaims();
       ClaimsSteps.isClaimsScreen();
    }

    @Test
    @DisplayName("Открытие экрана заявок через меню навигации и возвращение на главный экран")
    public void shouldOpenClaims() {
        ControlPanelSteps.goToClaimsPage();
        ClaimsSteps.isClaimsScreen();
        ControlPanelSteps.goToMainPage();
        MainSteps.isMainScreen();
    }

    @Test
    @DisplayName("Открытие экрана новостей через меню навигации")
    public void shouldOpenNews() {
        ControlPanelSteps.goToNewsPage();
        NewsSteps.isNewsScreen();
    }

    @Test
    @DisplayName("Открытие экрана о приложении меню навигации")
    public void shouldOpenAbout() {
        ControlPanelSteps.goToAboutPage();
        AboutSteps.isAboutScreen();
    }

    @Test
    @DisplayName("Сворачивание и раскрытие новостей на главном экране")
    public void shouldExpandHideNews() {
        MainSteps.expandAllNews();
        MainSteps.newsNotDisplayed();
        MainSteps.expandAllNews();
        MainSteps.newsDisplayed();
    }

    @Test
    @DisplayName("Сворачивание и раскрытие заявок на главном экране")
    public void shouldExpandHideClaims() {
        MainSteps.expandAllClaims();
        MainSteps.claimsNotDisplayed();
        MainSteps.expandAllClaims();
        MainSteps.claimsDisplayed();
    }

    @Test
    @DisplayName("Разворачивание и сворачивание одной новости")
    public void shouldExpandSingleNews() {
        MainSteps.expandOneNews();
        MainSteps.hideOneNews();
    }

    @Test
    @DisplayName("Открытие заявки и возвращение обратно")
    public void shouldOpenOneClaim() {
        MainSteps.openOneClaim();
        ClaimCreateEditSteps.claimFullOpen();
        ClaimCreateEditSteps.isClaimsEditScreen();
        ClaimCreateEditSteps.backFromClaim();
        MainSteps.isMainScreen();
    }

    @Test
    @DisplayName("Создание заявки с главного экрана")
    public void shouldCreateNewClaim() {
        String title = MainHelper.getRandomClaimTitle();
        String executor = Resources.correctExecutor;
        String date = MainHelper.generateDate(0);
        String time = MainHelper.generateTime(1);
        String description = MainHelper.getRandomNewsDescription();
        MainSteps.createClaim();
        ClaimCreateEditSteps.isClaimsCreateScreen();
        ClaimCreateEditSteps.fillInTitle(title);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(date);
        ClaimCreateEditSteps.fillInTime(time);
        ClaimCreateEditSteps.fillItDescription(description);
        ClaimCreateEditScreen.saveButton.perform(click());
        ControlPanelSteps.goToClaimsPage();
        ClaimsSteps.scrollToClaimWithTittleAndClick(title);
    }

}
