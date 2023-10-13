package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Elements.ClaimCreateEditScreen;
import ru.iteco.fmhandroid.ui.data.MainHelper;

public class ClaimCreateEditSteps {

    public static void isClaimsCreateScreen() {
        Allure.step("Проверка что это экран заявок");
        ClaimCreateEditScreen.creatingClaimsName.check(matches(isDisplayed()));
        ClaimCreateEditScreen.executorListClaim.check(matches(isDisplayed()));
    }

    public static void fillInTitle(String title) {
        Allure.step("Заполнить поле заголовок");
        ClaimCreateEditScreen.titleClaim.perform(replaceText(title));
    }

    public static void fillInExecutor(String executor) {
        Allure.step("Заполнить поле исполнитель");
        ClaimCreateEditScreen.executorListClaim.perform(replaceText(executor));
    }

    public static void fillInDate(String date) {
        Allure.step("Заполнить поле дата");
        ClaimCreateEditScreen.dateClaim.perform(replaceText(date));
    }

    public static void fillInTime(String time) {
        Allure.step("Заполнить поле время");
        ClaimCreateEditScreen.timeClaim.perform(replaceText(time));
    }

    public static void fillItDescription(String description) {
        Allure.step("Заполнить поле описание");
        ClaimCreateEditScreen.descriptionClaim.perform(replaceText(description));
    }

    public static void isClaimsEditScreen() {
        Allure.step("Убедиться что в открытой заявке видно статус");
        ClaimCreateEditScreen.claimStatus.check(matches(isDisplayed()));
    }

    public static void claimFullOpen() {
        Allure.step("Загрузка всех элементов заявки");
        MainHelper.waitElement(withId(R.id.status_processing_image_button), 10000);
    }

    public static void backFromClaim() {
        Allure.step("Уйти назад из заявки");
        ClaimCreateEditScreen.claimStatus.check(matches(isDisplayed()));
        ClaimCreateEditScreen.backButton.perform(MainHelper.scrollTo());
        ClaimCreateEditScreen.backButton.perform(click());
    }

    public static void errorWithAddingNewClaimWithoutSomeParameters() {
        Allure.step("Проверка сообщения о недопустимости пустых полей");
        ClaimCreateEditScreen.errorAddingMessage.check(matches(isDisplayed()));
        String actualErrorMessage = MainHelper.getTextFromViewInteraction(ClaimCreateEditScreen.errorAddingMessage);
        String expectedErrorMessage = MainHelper.getStringFromResource(ClaimCreateEditScreen.errorAddingMessageId);
        assertEquals(expectedErrorMessage, actualErrorMessage);
        MainHelper.waitElement((withText("OK")), 1000);
        onView(withText("OK")).perform(click());
        Espresso.pressBack();
    }
}
