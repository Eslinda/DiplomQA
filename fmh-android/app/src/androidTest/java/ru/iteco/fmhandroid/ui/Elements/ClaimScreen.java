package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimScreen {

    public static ViewInteraction claims = onView(withText("Claims"));
    public static ViewInteraction filterButton = onView((withId(R.id.filters_material_button)));
    public static ViewInteraction addNewClaimButton = onView(withId(R.id.add_new_claim_material_button));
    public static ViewInteraction openClaim = onView(withId(R.id.description_material_text_view));
    public static ViewInteraction titleFiltering = onView(withId(R.id.claim_filter_dialog_title));
    public static ViewInteraction open = onView(withId(R.id.item_filter_open));
    public static ViewInteraction inProgress = onView(withId(R.id.item_filter_in_progress));
    public static ViewInteraction executed = onView(withId(R.id.item_filter_executed));
    public static ViewInteraction cancelled = onView(withId(R.id.item_filter_cancelled));
    public static ViewInteraction buttonOk = onView(withId(R.id.claim_list_filter_ok_material_button));
    public static ViewInteraction buttonCancel = onView(withId(R.id.claim_filter_cancel_material_button));
    public static ViewInteraction butterflyImageClaim = onView(withId(R.id.empty_claim_list_image_view));
    public static ViewInteraction emptyWarning = onView(withText("There is nothing here yet…"));
    public static ViewInteraction refreshButton  = onView(withText("Refresh"));

}
