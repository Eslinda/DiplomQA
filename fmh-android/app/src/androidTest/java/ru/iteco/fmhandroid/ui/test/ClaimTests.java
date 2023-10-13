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
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Elements.ClaimCreateEditScreen;
import ru.iteco.fmhandroid.ui.Steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.Steps.ClaimCreateEditSteps;
import ru.iteco.fmhandroid.ui.Steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.Steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.data.MainHelper;
import ru.iteco.fmhandroid.ui.data.Resources;

@RunWith(AllureAndroidJUnit4.class)
public class ClaimTests {

    @Rule
    public ActivityTestRule<AppActivity> ActivityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        Thread.sleep(3000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            SystemClock.sleep(2000);
            ControlPanelSteps.goToClaimsPage();
            return;
        }
        AuthorizationSteps.enterLogin(Resources.correctLogin);
        AuthorizationSteps.enterPassword(Resources.correctPassword);
        AuthorizationSteps.clickButtonSingIn();
        ControlPanelSteps.goToClaimsPage();
    }

    @Test
    @DisplayName("Успешное создание заяки")
    public void shouldCreateClaims() {
        String titleText = MainHelper.getRandomClaimTitle();
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInTitle(titleText);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimCreateEditScreen.saveButton.perform(click());
        ClaimsSteps.scrollToClaimWithTittleAndClick(titleText);
        ClaimsSteps.checkCreatingClaim(titleText);
    }

    @Test
    @DisplayName("Создание (отказ) заяки с пустым полем тема (Title)")
    public void shouldNotCreateClaimWithoutTitle() {
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimCreateEditScreen.saveButton.perform(click());
        ClaimsSteps.checkErrorMessageBySomeParameterEmpty();
    }

    @Test
    @DisplayName("Создание (отказ) заяки с пробелами в поле тема (Title)")
    public void shouldNotCreateClaimWithSpacesInTitle() {
        String titleText = "          ";
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInTitle(titleText);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimCreateEditScreen.saveButton.perform(click());
        ClaimsSteps.checkErrorMessageBySomeParameterEmpty();
    }

    @Test
    @DisplayName("Фильтрация заявок с пустыми чек боксами")
    public void shouldCheckNoClaimsAreDisplayed() {
        ClaimsSteps.filterWithoutStatus();
        ClaimsSteps.emptyScreenShown();
    }

    @Test
    @DisplayName("Отфильтровать заявки со статусом Open")
    public void shouldShowOpenClaims() {
        String titleText = MainHelper.getRandomClaimTitle();
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInTitle(titleText);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimCreateEditScreen.saveButton.perform(click());
        ClaimsSteps.filterWithOpenStatus();
        ClaimsSteps.scrollToClaimWithTittleAndClick(titleText);
        SystemClock.sleep(2000);
        ClaimsSteps.checkClaimWithStatusOpen();
    }

    @Test
    @DisplayName("Отмена создания новой заявки при нажатии кнопки Cancel")
    public void shouldNotCreatingNewClaimWhenPressCancelButton() {
        String titleText = MainHelper.getRandomClaimTitle();
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInTitle(titleText);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimsSteps.clickCancelButton();
        ClaimsSteps.clickOkButton();
        ClaimsSteps.isClaimsScreen();
    }

    @Test
    @DisplayName("Успешное добавление комментария к заявке")
    public void shouldAddingNewCommentToClaim() {
        String titleText = MainHelper.getRandomClaimTitle();
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        String comment = MainHelper.getRandomNewsDescription();
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInTitle(titleText);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimCreateEditScreen.saveButton.perform(click());
        ClaimsSteps.scrollToClaimWithTittleAndClick(titleText);
        ClaimsSteps.addNewCommentToClaim(comment);
        SystemClock.sleep(2000);
        ClaimsSteps.checkAddingComment(comment);
    }
    @Test
    @DisplayName("Отмена добавления комментария к заявке при нажатии кнопки Отмена (Cancel)")
    public void shouldCancelAddingNewCommentToClaimWithPressCancel() {
        String titleText = MainHelper.getRandomClaimTitle();
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        String comment = MainHelper.getRandomNewsDescription();
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInTitle(titleText);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimCreateEditScreen.saveButton.perform(click());
        ClaimsSteps.scrollToClaimWithTittleAndClick(titleText);
        ClaimsSteps.cancelAddingNewCommentToClaimWithPressCancel(comment);
        ClaimsSteps.checkNotAddingNewComment();
    }

    @Test
    @DisplayName("Добавление (отказ) пустого комментария к заявке")
    public void shouldAddingNewCommentToClaimWithEmptyField() {
        String titleText = MainHelper.getRandomClaimTitle();
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        String comment = "";
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInTitle(titleText);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimCreateEditScreen.saveButton.perform(click());
        ClaimsSteps.scrollToClaimWithTittleAndClick(titleText);
        ClaimsSteps.addNewCommentToClaim(comment);
        ClaimsSteps.checkToastByEmptyField(ActivityTestRule);
    }
    @Test
    @DisplayName("Редактирование существующего комментария у заявки")
    public void shouldEditCommentToClaim() {
        String titleText = MainHelper.getRandomClaimTitle();
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        String comment = MainHelper.getRandomNewsDescription();
        String newComment = MainHelper.getRandomNewsDescription();
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInTitle(titleText);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimCreateEditScreen.saveButton.perform(click());
        ClaimsSteps.scrollToClaimWithTittleAndClick(titleText);
        ClaimsSteps.addNewCommentToClaim(comment);
        ClaimsSteps.editExistingCommentToClaim(newComment);
        ClaimsSteps.checkAddingComment(newComment);
    }

    @Test
    @DisplayName("Редактирование темы (Title) у существующей заявки")
    public void shouldEditTitleToClaim() {
        String titleText = MainHelper.getRandomClaimTitle();
        String newTitleText = MainHelper.getRandomClaimTitle();
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInTitle(titleText);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimCreateEditScreen.saveButton.perform(click());
        ClaimsSteps.scrollToClaimWithTittleAndClick(titleText);
        ClaimsSteps.editTitleClaim(newTitleText);
        SystemClock.sleep(2000);
        ClaimsSteps.checkEditingTitle(newTitleText);
    }

    @Test
    @DisplayName("Изменение статуса Open на In progress у существующей заявки")
    public void shouldEditStatusToClaim() {
        String titleText = MainHelper.getRandomClaimTitle();
        String executor = MainHelper.randomExecutor();
        String descriptionText = MainHelper.getRandomNewsDescription();
        String climDate = MainHelper.generateDate(0);
        String climTime = MainHelper.generateTime(0);
        ClaimsSteps.createClaimButton();
        ClaimCreateEditSteps.fillInTitle(titleText);
        ClaimCreateEditSteps.fillInExecutor(executor);
        ClaimCreateEditSteps.fillInDate(climDate);
        ClaimCreateEditSteps.fillInTime(climTime);
        ClaimCreateEditSteps.fillItDescription(descriptionText);
        ClaimCreateEditScreen.saveButton.perform(click());
        ClaimsSteps.scrollToClaimWithTittleAndClick(titleText);
        ClaimsSteps.editStatusClaim();
        ClaimsSteps.checkEditingStatus();
    }

}
