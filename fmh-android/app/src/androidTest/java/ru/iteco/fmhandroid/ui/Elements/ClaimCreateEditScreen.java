package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class ClaimCreateEditScreen {

    public static ViewInteraction creatingClaimsName = onView(allOf(withId(R.id.custom_app_bar_title_text_view), withText("Creating")));
    public static ViewInteraction titleClaim = onView(withId(R.id.title_edit_text));
    public static ViewInteraction executorListClaim = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    public static ViewInteraction dateClaim = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public static ViewInteraction timeClaim = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public static ViewInteraction descriptionClaim = onView(withId(R.id.description_edit_text));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction claimStatus = onView(withId(R.id.status_label_text_view));
    public static ViewInteraction backButton = onView(withId(R.id.close_image_button));
    public static ViewInteraction editClimButton = onView(withId(R.id.edit_processing_image_button));
    public static ViewInteraction editStatusButton = onView(withId(R.id.status_processing_image_button));
    public static ViewInteraction takeToWorkClaim = onView(withText("take to work"));
    public static ViewInteraction addCommentButton = onView(withId(R.id.add_comment_image_button));
    public static ViewInteraction editCommentButton = onView(withId(R.id.edit_comment_image_button));
    public static ViewInteraction errorAddingMessage = onView(allOf(withId(android.R.id.message)));
    public static int errorAddingMessageId = R.string.empty_fields;

}
