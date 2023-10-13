package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthorizationScreen {

    public static ViewInteraction authorization = onView(withText("Authorization"));
    public static ViewInteraction login = onView(allOf(supportsInputMethods(),
            isDescendantOfA(withId(R.id.login_text_input_layout))));
    public static ViewInteraction password =  onView(allOf(supportsInputMethods(),
            isDescendantOfA(withId(R.id.password_text_input_layout))));
    public static ViewInteraction buttonSingIn = onView(withId(R.id.enter_button));


}
