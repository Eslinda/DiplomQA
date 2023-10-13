package ru.iteco.fmhandroid.ui.test;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
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
import ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.Steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.data.Resources;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTests {
    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() throws InterruptedException {
        Thread.sleep(5000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            ControlPanelSteps.logOut();
        }
    }

    @Test
    @DisplayName("Проверка успешной авторизации и выхода из учетной записи")
    public void shouldAuthorizationTestPositive() throws InterruptedException {
        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.enterLogin(Resources.correctLogin);
        AuthorizationSteps.enterPassword(Resources.correctPassword);
        AuthorizationSteps.clickButtonSingIn();
        ControlPanelSteps.logOut();
        AuthorizationSteps.isAuthorizationScreen();
    }

    @Test
    @DisplayName("Проверка авторизации (октаза) с пустым логином")
    public void shouldNotAuthorizationWithEmptyFieldLogin() {
        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.enterPassword(Resources.correctPassword);
        AuthorizationSteps.clickButtonSingIn();
        AuthorizationSteps.checkMessageThatFieldsShouldNotBeEmpty(ActivityTestRule);
    }

    @Test
    @DisplayName("Проверка авторизации (октаза) с пустым паролем")
    public void shouldNotAuthorizationWithEmptyFieldPassword() {
        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.enterLogin(Resources.correctLogin);
        AuthorizationSteps.clickButtonSingIn();
        AuthorizationSteps.checkMessageThatFieldsShouldNotBeEmpty(ActivityTestRule);
    }

    @Test
    @DisplayName("Проверка авторизации (октаза) с пустыми логином и паролем")
    public void shouldNotAuthorizationWithEmptyFields() {
        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.clickButtonSingIn();
        AuthorizationSteps.checkMessageThatFieldsShouldNotBeEmpty(ActivityTestRule);
    }

    @Test
    @DisplayName("Проверка авторизации (октаза) с невалидными логином и паролем")
    public void shouldNotAuthorizationWithIncorrectFields() {
        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.enterLogin(Resources.wrongLogin);
        AuthorizationSteps.enterPassword(Resources.wrongPassword);
        AuthorizationSteps.clickButtonSingIn();
        AuthorizationSteps.checkMessageThatIncorrectFields(ActivityTestRule);
    }

}
