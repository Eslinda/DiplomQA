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
import ru.iteco.fmhandroid.ui.Elements.QuotesScreen;
import ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.Steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.Steps.QuotesSteps;
import ru.iteco.fmhandroid.ui.data.Resources;

@RunWith(AllureAndroidJUnit4.class)
public class QuotesTests {

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
    @DisplayName("Проверка видимости цитат и разворачивания/сворачивания одной")
    public void shouldBeFullContentInEachQuote() {
        SystemClock.sleep(2000);
        ControlPanelSteps.goToMissionPage();
        QuotesSteps.openFirstQuote();
        SystemClock.sleep(2000);
        QuotesSteps.checkThatFirstQuoteContentIsFull();
        QuotesSteps.closeFirstQuote();
    }

}