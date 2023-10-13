package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.Elements.AboutScreen;

public class AboutSteps {

    public static void isAboutScreen() {
        Allure.step("Проверка что это экран о приложении и видны основные пункты");
        AboutScreen.versionInfo.check(matches(isDisplayed()));
        AboutScreen.privacyPolicyValue.check(matches(isDisplayed()));
        AboutScreen.termsOfUseValue.check(matches(isDisplayed()));
        AboutScreen.aboutInfo.check(matches(isDisplayed()));
    }

    public static void goToPrivacyPolicy() {
        Allure.step("Проверка перехода на страницу политики конфиденциальности");
        AboutScreen.privacyPolicyValue.perform(click());
    }

    public static void goToTermsOfUse() {
        Allure.step("Проверка перехода на страницу пользовательского соглашения");
        AboutScreen.termsOfUseValue.perform(click());
    }

    public static void goBack() {
        Allure.step("Проверка перехода назад с помощью кнопки");
        AboutScreen.backButton.perform(click());
    }
}
