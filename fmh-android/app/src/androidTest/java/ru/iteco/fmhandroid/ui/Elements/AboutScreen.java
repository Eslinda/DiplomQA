package ru.iteco.fmhandroid.ui.Elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutScreen {

    public static ViewInteraction versionText = onView(allOf(withId(R.id.about_version_title_text_view)));
    public static ViewInteraction versionInfo = onView(allOf(withId(R.id.about_company_info_label_text_view)));
    public static ViewInteraction aboutInfo = onView(allOf(withId(R.id.about_company_info_label_text_view)));
    public static ViewInteraction privacyPolicyValue = onView(allOf(withId(R.id.about_privacy_policy_value_text_view)));
    public static ViewInteraction termsOfUseValue = onView(allOf(withId(R.id.about_terms_of_use_value_text_view)));
    public static ViewInteraction backButton = onView(allOf(withId(R.id.about_back_image_button)));

}
