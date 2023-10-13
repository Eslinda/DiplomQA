package ru.iteco.fmhandroid.ui.Elements;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.data.MainHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class QuotesScreen {

    public static ViewInteraction title = onView(withId(R.id.our_mission_title_text_view));

    public static ViewInteraction openQuote = onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0));

    public static ViewInteraction closeQuote = onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0));

   /* public static ViewInteraction titleQuote = onView(withId(R.id.our_mission_item_title_text_view));
    public ViewInteraction descriptionQuote = onView(withId(R.id.our_mission_item_description_text_view));*/

    public static ViewInteraction titleFirstQuote = onView(withText("«Хоспис для меня - это то, каким должен быть мир.\""));

    public static ViewInteraction descriptionFirstQuote = onView(withText("\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер"));


}