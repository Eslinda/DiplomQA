package ru.iteco.fmhandroid.ui.Steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.endsWith;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

import static ru.iteco.fmhandroid.ui.data.MainHelper.withIndex;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.CoreMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Elements.ClaimCreateEditScreen;
import ru.iteco.fmhandroid.ui.Elements.ClaimScreen;
import ru.iteco.fmhandroid.ui.Elements.NewsControlPanel;
import ru.iteco.fmhandroid.ui.data.MainHelper;

public class ClaimsSteps {

    public static void isClaimsScreen() {
        Allure.step("Проверка что это экран заявок");
        ClaimScreen.claims.check(matches(isDisplayed()));
        ClaimScreen.addNewClaimButton.check(matches(isDisplayed()));
    }

    public static void scrollToClaimWithTittleAndClick(String tittle) {
        Allure.step("Прокрутка списка заявок и выбор по заголовку");
        MainHelper.waitElement(withId(R.id.claim_list_recycler_view), 10000);
        onView(withId(R.id.claim_list_recycler_view))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(allOf(withText(tittle)))));
        onView(withId(R.id.claim_list_recycler_view))
                .check(matches(isDisplayed()))
                .perform(actionOnItem(hasDescendant(withText(tittle)), click()));
    }

    public static void createClaimButton() {
        Allure.step("Нажать кнопку создания заявки");
        ClaimScreen.addNewClaimButton.perform(click());
    }

    public static void checkCreatingClaim(String titleText) {
        Allure.step("Проверка создания заявки");
        String titleScreen = MainHelper.Text.getText(onView(withIndex(withId(R.id.title_text_view), 0)));
        assertEquals(titleScreen, titleText);
    }

    public static void filterWithoutStatus() {
        Allure.step("Фильтрация без выбора статусов");
        filterClimes(false, false, false, false);
    }

    public static void filterWithOpenStatus() {
        Allure.step("Фильтрация со статусом Open");
        filterClimes(true, false, false, false);
    }

    public static void filterWithExecutedStatus() {
        Allure.step("Фильтрация  со статусом Executed");
        filterClimes(false, true, false, false);
    }

    public static void filterWithCancelledStatus() {
        Allure.step("Фильтрация  со статусом Cancelled");
        filterClimes(false, false, true, false);
    }

    public static void filterWithInProgressStatus() {
        Allure.step("Фильтрация  со статусом In progress");
        filterClimes(false, false, false, true);
    }

    public static void emptyScreenShown() {
        Allure.step("Пустой экран заявок");
        MainHelper.waitElement(withId(R.id.empty_claim_list_image_view), 10000);
        ClaimScreen.emptyWarning.check(matches(isDisplayed()));
        ClaimScreen.refreshButton.check(matches(isDisplayed()));
    }

    public static void clickOkButton() {
        Allure.step("Нажать ок");
        MainHelper.waitElement((withText("OK")), 1000);
        onView(withText("OK")).perform(click());
    }

    public static void filterClimes(boolean open, boolean inProgress, boolean executed, boolean cancelled) {
        Allure.step("Выбор фильтров");
        MainHelper.waitElement(withId(R.id.claim_list_recycler_view), 1000);
        onView(withId(R.id.filters_material_button)).perform(click());
        if (!open) {
            onView(withId(R.id.item_filter_open)).check(matches(isChecked())).perform(click());
        }
        if (!inProgress) {
            onView(withId(R.id.item_filter_in_progress)).check(matches(isChecked())).perform(click());
        }
        if (executed) {
            onView(withId(R.id.item_filter_executed)).check(matches(isNotChecked())).perform(click());
        }
        if (cancelled) {
            onView(withId(R.id.item_filter_cancelled)).check(matches(isNotChecked())).perform(click());
        }
        onView(withId(R.id.claim_list_filter_ok_material_button)).perform(click());
        MainHelper.waitElement(withId(R.id.claim_list_recycler_view), 1000);
    }

    public static void checkClaimWithStatusOpen() {
        Allure.step("Проверка, что при фильтации заявок со статусом Открыта, отобразилась созданная заявка");
        onView(withId(R.id.status_label_text_view)).
                check(matches(CoreMatchers.allOf(isDisplayed(), withText(R.string.status_open))));
    }

    public static void clickCancelButton() {
        Allure.step("Нажать Cancel");
        ClaimCreateEditScreen.cancelButton.perform(click());
    }

    public static void checkErrorMessageBySomeParameterEmpty() {
        Allure.step("Проверка сообщения о недопустимости пустых полей при создании заявки");
        ClaimCreateEditSteps.errorWithAddingNewClaimWithoutSomeParameters();
    }

    public static void addNewCommentToClaim(String comment) {
        Allure.step("Добавить новый комментарий к заявке");
        ClaimCreateEditScreen.addCommentButton.perform(click());
        onView(withClassName(endsWith("EditText"))).perform(replaceText(comment));
        ClaimCreateEditScreen.saveButton.perform(click());
    }

    public static void checkAddingComment(String comment) {
        Allure.step("Проверка добавления к заявке комментария");
        onView(withText(comment)).check(matches(isDisplayed()));
    }

    public static void cancelAddingNewCommentToClaimWithPressCancel(String comment) {
        Allure.step("Заполнить новый комментарий к заявке и нажать Отмена (Cancel)");
        ClaimCreateEditScreen.addCommentButton.perform(click());
        onView(withClassName(endsWith("EditText"))).perform(replaceText(comment));
        ClaimCreateEditScreen.cancelButton.perform(click());
    }

    public static void checkNotAddingNewComment() {
        Allure.step("Проверка, что в заявке не появился новый комментарий");
        onView(CoreMatchers.allOf(withId(R.id.comment_description_text_view))).check(doesNotExist());
    }

    public static void checkToastByEmptyField(ActivityTestRule<AppActivity> activityTestRule) {
        Allure.step("Проверка сообщения о недопустимости пустого поля ввода");
        onView(withText(R.string.toast_empty_field))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow()
                        .getDecorView())))).check(matches(withText("The field cannot be empty.")));
    }

    public static void editExistingCommentToClaim(String comment) {
        Allure.step("Редактирование существующего комментария");
        ClaimCreateEditScreen.editCommentButton.perform(click());
        onView(withClassName(endsWith("EditText"))).perform(replaceText(comment));
        ClaimCreateEditScreen.saveButton.perform(click());
    }

    public static void editTitleClaim(String newTitleText) {
        Allure.step("Редактирование темы у существующей заявки");
        ClaimCreateEditScreen.editClimButton.perform(click());
        ClaimCreateEditScreen.titleClaim.perform(replaceText(newTitleText));
        ClaimCreateEditScreen.saveButton.perform(click());
    }

    public static void checkEditingTitle(String newTitleText) {
        Allure.step("Проверка изменения заголовка в заявке");
        String title = MainHelper.Text.getText(onView(withId(R.id.title_text_view)));
        assertEquals(newTitleText, title);
    }

    public static void editStatusClaim() {
        Allure.step("Редактируем статуса у существующей заявки");
        ClaimCreateEditScreen.editStatusButton.perform(click());
        ClaimCreateEditScreen.takeToWorkClaim.perform(click());
    }

    public static void checkEditingStatus() {
        Allure.step("Проверка изменения статуса у заявки");
        String claimStatus = MainHelper.Text.getText(onView(withId(R.id.status_label_text_view)));
        assertEquals("In progress", claimStatus);
    }

}
