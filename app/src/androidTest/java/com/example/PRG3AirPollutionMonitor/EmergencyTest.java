package com.example.PRG3AirPollutionMonitor;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class EmergencyTest {

    @Test
    public void test_isEmergencyInfoInView() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),Emergency.class);
        ActivityScenario<Emergency> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.emergency_info_page)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_titleAndTextViews() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),Emergency.class);
        ActivityScenario<Emergency> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.emergency_information)).check(matches(isDisplayed()));

        onView(withId(R.id.whatToDo_info)).check(matches(isDisplayed()));
//        onView(withId(R.id.AE_info)).check(matches(isDisplayed()));
//        onView(withId(R.id.NHS111_info)).check(matches(isDisplayed()));
//        onView(withId(R.id.urgentSymptoms_info)).check(matches(isDisplayed()));
//        onView(withId(R.id.reference_text)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_cardViews() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),Emergency.class);
        ActivityScenario<Emergency> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.whatToDo_cardview)).check(matches(isDisplayed()));
//        onView(withId(R.id.call_999_cardview)).check(matches(isDisplayed()));
//        onView(withId(R.id.call_111_cardview)).check(matches(isDisplayed()));
//        onView(withId(R.id.urgentSymptoms_cardview)).check(matches(isDisplayed()));
//        onView(withId(R.id.routine_cardview)).check(matches(isDisplayed()));
    }

    //    @Test
//    public void test_isTitleAndTextViewTextDisplayed() {
//        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),Emergency.class);
//        ActivityScenario<Emergency> activityScenario = ActivityScenario.launch(intent);
//
//        onView(withId(R.id.welcome_title)).check(matches(withText(R.string.main_title)));
//    }
//
//    @Test
//    public void test_ifSourceLinksCanBeOpened() {
//    }

}