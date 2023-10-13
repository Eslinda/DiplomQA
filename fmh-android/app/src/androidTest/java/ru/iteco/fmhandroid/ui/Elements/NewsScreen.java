package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.data.MainHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.MainHelper;

public class NewsScreen {

    public static ViewInteraction news = onView(withText("News"));
    public static ViewInteraction sortNewsButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public static ViewInteraction listAllNews = onView(withId(R.id.all_news_cards_block_constraint_layout));
    public static ViewInteraction expandOneNews = onView(allOf(withId(R.id.news_list_recycler_view),
            MainHelper.childAtPosition(withId(R.id.all_news_cards_block_constraint_layout), withId(R.id.all_news_cards_block_constraint_layout), 0)));
    public static ViewInteraction descriptionNews = onView(withIndex(withId(R.id.view_news_item_image_view), 0));

    public static ViewInteraction descriptionOfFirstNews = onView(MainHelper.withIndex(withId(R.id.news_item_description_text_view), 0));
    public static ViewInteraction tittleTextNews = onView(withId(R.id.news_item_title_text_view));
    public static ViewInteraction descriptionTextNews = onView(withId(R.id.news_item_description_text_view));

}
