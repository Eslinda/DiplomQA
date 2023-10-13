package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ControlPanel {

    public static ViewInteraction tradeMark = onView(withId(R.id.trademark_image_view));

    public static ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction mainOfMenu = onView(withText("Main"));
    public static ViewInteraction claimsOfMenu = onView(withText("Claims"));
    public static ViewInteraction newsOfMenu = onView(withText("News"));
    public static ViewInteraction aboutOfMenu = onView(withText("About"));
    public static ViewInteraction missionButton = onView(withId(R.id.our_mission_image_button));
    public static ViewInteraction logOutMenu = onView(allOf(withId(R.id.authorization_image_button)));;
    public static ViewInteraction logOutButton = onView(withText("Log out"));
}
