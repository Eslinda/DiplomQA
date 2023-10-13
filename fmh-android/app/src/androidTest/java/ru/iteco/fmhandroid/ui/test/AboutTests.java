package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.hamcrest.Matchers.allOf;

import android.content.Intent;
import android.os.SystemClock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.Steps.AboutSteps;
import ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.Steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.Steps.MainSteps;
import ru.iteco.fmhandroid.ui.data.Resources;

@RunWith(AllureAndroidJUnit4.class)
public class AboutTests {

    @Rule
    public IntentsTestRule intentsTestRule =
            new IntentsTestRule(ru.iteco.fmhandroid.ui.AppActivity.class);

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
    @DisplayName("Проверка открытия экрана о приложении и возвращении назад")
    public void shouldBeFullContentInAboutBlock() {
        ControlPanelSteps.goToAboutPage();
        SystemClock.sleep(2000);
        AboutSteps.isAboutScreen();
        AboutSteps.goBack();
        MainSteps.isMainScreen();
    }

    @Test
    @DisplayName("Проверка открытия ссылки на политику конфиденциальности")
    public void shouldGoToPrivacyPolicy() {
        SystemClock.sleep(2000);
        ControlPanelSteps.goToAboutPage();
        SystemClock.sleep(2000);
        AboutSteps.goToPrivacyPolicy();
        intended(allOf(
                hasData(Resources.urlPrivacyPolicy),
                hasAction(Intent.ACTION_VIEW)));
    }

    @Test
    @DisplayName("Проверка открытия ссылки на пользовательское соглашение")
    public void shouldGoToTermsOfUse() {
        SystemClock.sleep(2000);
        ControlPanelSteps.goToAboutPage();
        SystemClock.sleep(2000);
        AboutSteps.goToTermsOfUse();
        intended(allOf(
                hasData(Resources.urlTermsOfUse),
                hasAction(Intent.ACTION_VIEW)));
    }

}
