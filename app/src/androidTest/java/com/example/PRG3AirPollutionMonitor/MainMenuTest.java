package com.example.PRG3AirPollutionMonitor;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;

public class MainMenuTest {
//    @Rule
//    public ActivityScenarioRule<MainMenu> activityScenarioRule = new ActivityScenarioRule<>(MainMenu.class);
    
    @Test
    public void test_isMainMenuInView() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),MainMenu.class);
        ActivityScenario<MainMenu> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.Main_Menu)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_titleAndButtons() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),MainMenu.class);
        ActivityScenario<MainMenu> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.welcome_title)).check(matches(isDisplayed()));
        onView(withId(R.id.air_quality_button)).check(matches(isDisplayed()));
        onView(withId(R.id.emergency_button)).check(matches(isDisplayed()));
        onView(withId(R.id.inhaler_button)).check(matches(isDisplayed()));
        onView(withId(R.id.countdown_button)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isTitleAndButtonTextDisplayed() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),MainMenu.class);
        ActivityScenario<MainMenu> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.welcome_title)).check(matches(withText(R.string.main_title)));
        onView(withId(R.id.air_quality_button)).check(matches(withText(R.string.air_quality_title)));
        onView(withId(R.id.emergency_button)).check(matches(withText(R.string.emergency_title)));
        onView(withId(R.id.inhaler_button)).check(matches(withText(R.string.inhaler_title)));
        onView(withId(R.id.countdown_button)).check(matches(withText(R.string.countdown_title)));
    }

    @Test
    public void test_airQualityButton_toAirQualityMenu() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),MainMenu.class);
        ActivityScenario<MainMenu> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.air_quality_button)).perform(click());
        onView(withId(R.id.air_quality_menu)).check(matches(isDisplayed()));
    }

    @Test
    public void test_emergencyButton_toEmergencyInfoPage() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),MainMenu.class);
        ActivityScenario<MainMenu> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.emergency_button)).perform(click());
        onView(withId(R.id.emergency_info_page)).check(matches(isDisplayed()));
    }

    @Test
    public void test_inhalerUseButton_toInhalerUseMenu() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),MainMenu.class);
        ActivityScenario<MainMenu> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.inhaler_button)).perform(click());
        onView(withId(R.id.inhaler_use_menu)).check(matches(isDisplayed()));
    }

}