package com.example.PRG3AirPollutionMonitor;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class MainMenuTest {
    
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


}