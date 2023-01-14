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

        // Check that emergency information page displayed correctly
        onView(withId(R.id.emergency_info_page)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_titleAndTextViews() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),Emergency.class);
        ActivityScenario<Emergency> activityScenario = ActivityScenario.launch(intent);

        // Check that title and textviews displayed correctly
        onView(withId(R.id.emergency_information)).check(matches(isDisplayed()));
        onView(withId(R.id.whatToDo_info)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_cardViews() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),Emergency.class);
        ActivityScenario<Emergency> activityScenario = ActivityScenario.launch(intent);

        // Check that cardviews displayed correctly
        onView(withId(R.id.whatToDo_cardview)).check(matches(isDisplayed()));
    }

}