package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.data.MainHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.MainHelper;

public class MainScreen {

    public static ViewInteraction expandHideNewsButton  = onView(allOf(withId(R.id.expand_material_button),
            MainHelper.childAtPosition(MainHelper.childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), withId(R.id.all_news_cards_block_constraint_layout), 0), withId(R.id.all_news_cards_block_constraint_layout), 4)));
    public static ViewInteraction expandOneNews = onView(allOf(withId(R.id.news_list_recycler_view),
            MainHelper.childAtPosition(withId(R.id.all_news_cards_block_constraint_layout), withId(R.id.all_news_cards_block_constraint_layout), 0)));
    public static ViewInteraction descriptionNews = onView(withIndex(withId(R.id.view_news_item_image_view), 0));
    public static ViewInteraction descriptionNewsAfterClosing = onView(withIndex(withId(R.id.view_news_item_image_view), 0));
    public static ViewInteraction categoryIcon = onView(withIndex(withId(R.id.category_icon_image_view), 0));
    public static ViewInteraction allNewsButton  = onView(withId(R.id.all_news_text_view));

    public static ViewInteraction expandHideClaimsButton  = onView(allOf(withId(R.id.expand_material_button),
            MainHelper.childAtPosition(MainHelper.childAtPosition(withId(R.id.container_list_claim_include_on_fragment_main), withId(R.id.all_news_cards_block_constraint_layout), 0), withId(R.id.all_news_cards_block_constraint_layout), 3)));
    public static ViewInteraction allClaimsButton  = onView(withId(R.id.all_claims_text_view));
    public static ViewInteraction firstClaimExecutor = onView(allOf(withIndex(withId(R.id.executor_name_material_text_view), 0)));
    public static ViewInteraction addNewClaimButton = onView(allOf(withId(R.id.add_new_claim_material_button)));


}
