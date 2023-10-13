package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsFilterScreen {

    public static ViewInteraction filterScreenName = onView(withText("Filter news"));
    public static ViewInteraction categoryList = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction publishDateStart = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public static ViewInteraction publishDateEnd = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public static ViewInteraction checkboxActive = onView(withId(R.id.filter_news_active_material_check_box));
    public static ViewInteraction checkboxNotActive = onView(withId(R.id.filter_news_inactive_material_check_box));
    public static ViewInteraction filterButton = onView(withId(R.id.filter_button));
    public static ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
}
