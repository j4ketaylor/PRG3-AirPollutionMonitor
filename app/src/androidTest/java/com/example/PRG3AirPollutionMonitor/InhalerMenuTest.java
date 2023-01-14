package com.example.PRG3AirPollutionMonitor;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class InhalerMenuTest {

    @Test
    public void test_isInhalerMenuInView() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),InhalerMenu.class);
        ActivityScenario<InhalerMenu> activityScenario = ActivityScenario.launch(intent);

        // Check that inhaler use menu is displayed correctly
        onView(withId(R.id.inhaler_use_menu)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_titleAndButtons() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),InhalerMenu.class);
        ActivityScenario<InhalerMenu> activityScenario = ActivityScenario.launch(intent);

        // Check that title and buttons displayed correctly
        onView(withId(R.id.inhaler_menu_text)).check(matches(isDisplayed()));
        onView(withId(R.id.check_calendar)).check(matches(isDisplayed()));
        onView(withId(R.id.check_prescription_details)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isTitleAndButtonTextDisplayed() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),InhalerMenu.class);
        ActivityScenario<InhalerMenu> activityScenario = ActivityScenario.launch(intent);

        // Check that text in title and buttons displayed correctly
        onView(withId(R.id.inhaler_menu_text)).check(matches(withText(R.string.inhaler_title)));
        onView(withId(R.id.check_calendar)).check(matches(withText(R.string.check_calendar_title)));
        onView(withId(R.id.check_prescription_details)).check(matches(withText(R.string.check_prescription_title)));
    }

    @Test
    public void test_calendarButton_toUsageCalendarPage() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),InhalerMenu.class);
        ActivityScenario<InhalerMenu> activityScenario = ActivityScenario.launch(intent);

        // Check that pressing calendar button leads to correct page
        onView(withId(R.id.check_calendar)).perform(click());
        onView(withId(R.id.usage_calendar_page)).check(matches(isDisplayed()));

        // Check that pressing back returns to inhaler use menu
        pressBack();
        onView(withId(R.id.inhaler_use_menu)).check(matches(isDisplayed()));
    }

    @Test
    public void test_prescriptionButton_toPrescriptionDetailsPage() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),InhalerMenu.class);
        ActivityScenario<InhalerMenu> activityScenario = ActivityScenario.launch(intent);

        // Check that pressing prescription details button leads to correct page
        onView(withId(R.id.check_prescription_details)).perform(click());
        onView(withId(R.id.prescription_details_page)).check(matches(isDisplayed()));

        // Check that pressing back returns to inhaler use menu
        pressBack();
        onView(withId(R.id.inhaler_use_menu)).check(matches(isDisplayed()));
    }

}