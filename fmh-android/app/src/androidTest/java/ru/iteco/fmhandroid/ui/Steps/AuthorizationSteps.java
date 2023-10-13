package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.rule.ActivityTestRule;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Elements.AuthorizationScreen;

public class AuthorizationSteps {

    public static void isAuthorizationScreen() {
        Allure.step("Проверка что это экран авторизации");
        AuthorizationScreen.authorization.check(matches(isDisplayed()));
    }

    public static void enterLogin(String login) {
        Allure.step("Ввод логина");
        AuthorizationScreen.login.perform(replaceText(login));
    }

    public static void enterPassword(String password) {
        Allure.step("Ввод пароля");
        AuthorizationScreen.password.perform(replaceText(password));
    }

    public static void  clickButtonSingIn(){
        Allure.step("Нажать кнопку войти");
        AuthorizationScreen.buttonSingIn.perform(click());
    }

    public static void checkMessageThatFieldsShouldNotBeEmpty(ActivityTestRule<AppActivity> activityTestRule) {
        Allure.step("Проверка появления сообщения о том что поля не могут быть пустыми");
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Login and password cannot be empty")));
    }

    public static void checkMessageThatIncorrectFields(ActivityTestRule<AppActivity> activityTestRule) {
        Allure.step("Проверка сообщения о неверном пароле или логине");
        onView(withText(R.string.wrong_login_or_password))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("Wrong login or password")));
    }

}
