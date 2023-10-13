package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.data.MainHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsControlPanel {

    public static ViewInteraction newsControlPanel = onView(withText("Control panel"));
    public static ViewInteraction addNews = onView(withId(R.id.add_news_image_view));
    public static ViewInteraction tittleNewsEditing = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public static ViewInteraction datePublishNews = onView(withId(R.id.news_item_publication_date_text_view));
    public static ViewInteraction dateCreateNews = onView(withId(R.id.news_item_create_date_text_view));
    public static ViewInteraction authorNews = onView(withId(R.id.news_item_author_name_text_view));
    public static ViewInteraction statusNews = onView(withId(R.id.news_item_published_text_view));
    public static ViewInteraction deleteNewsButton(String newsTitle) {
        return onView(allOf(withId(R.id.delete_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newsTitle))))))));
    }

    public static ViewInteraction deleteDialog = onView(withText("Are you sure you want to permanently delete the document? These changes cannot be reversed in the future."));
    public static ViewInteraction okButton = onView(withText("OK"));
    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_item_image_view));
    public static ViewInteraction expandNewsButton = onView(withId(R.id.view_news_item_image_view));
    public static ViewInteraction descriptionNews = onView(withIndex(withId(R.id.news_item_description_text_view), 0));
    public static ViewInteraction newsFilterButton = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction newsStatusActive = onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public static ViewInteraction newsStatusNotActive = onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public static ViewInteraction butterflyImageNews = onView(withId(R.id.empty_news_list_image_view));
    public static ViewInteraction listOfNews = onView(withId(R.id.news_list_recycler_view));
    public static ViewInteraction emptyWarning = onView(withText("There is nothing here yet…"));
    public static ViewInteraction refreshButton  = onView(withText("Refresh"));

    public static ViewInteraction categoryListNews = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction newsTitle = onView(withId(R.id.news_item_title_text_input_edit_text));
    public static ViewInteraction dateNews = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static ViewInteraction timeNews = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction descriptionCreateNews = onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction switcherNews = onView(withId(R.id.switcher));
    public static ViewInteraction saveButton = onView(withId(R.id.save_button));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static ViewInteraction creatingNewsScreenName = onView(withText("Creating"));
    public ViewInteraction title = onView(withId(R.id.custom_app_bar_title_text_view));
    public ViewInteraction subTitle = onView(withId(R.id.custom_app_bar_sub_title_text_view));
}
