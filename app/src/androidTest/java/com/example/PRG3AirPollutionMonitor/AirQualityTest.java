package com.example.PRG3AirPollutionMonitor;

import static androidx.test.espresso.Espresso.onView;
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

public class AirQualityTest {

    @Test
    public void test_isAirQualityMenuInView() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),AirQuality.class);
        ActivityScenario<AirQuality> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.air_quality_menu)).check(matches(isDisplayed()));
    }

    @Test
    public void test_visibility_titleAndButtons() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),AirQuality.class);
        ActivityScenario<AirQuality> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.air_quality_text)).check(matches(isDisplayed()));
        onView(withId(R.id.live_london_pollution_levels)).check(matches(isDisplayed()));
        onView(withId(R.id.recommended_places_to_live)).check(matches(isDisplayed()));
        onView(withId(R.id.news_updates)).check(matches(isDisplayed()));
        onView(withId(R.id.list_of_areas)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isTitleAndButtonTextDisplayed() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),AirQuality.class);
        ActivityScenario<AirQuality> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.air_quality_text)).check(matches(withText(R.string.air_quality_title)));
        onView(withId(R.id.live_london_pollution_levels)).check(matches(withText(R.string.live_air_pollution_title)));
        onView(withId(R.id.recommended_places_to_live)).check(matches(withText(R.string.air_quality_plot_title)));
        onView(withId(R.id.news_updates)).check(matches(withText(R.string.news_updates_title)));
        onView(withId(R.id.list_of_areas)).check(matches(withText(R.string.sorted_areas_title)));
        onView(withId(R.id.list_of_bad_areas)).check(matches(withText(R.string.areas_to_avoid_title)));
    }

    @Test
    public void test_livePollutionButton_toLiveAirPollutionPage() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),AirQuality.class);
        ActivityScenario<AirQuality> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.live_london_pollution_levels)).perform(click());
        onView(withId(R.id.live_air_pollution_page)).check(matches(isDisplayed()));
    }

    @Test
    public void test_airQualityPlotButton_toAirQualityPlotPage() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),AirQuality.class);
        ActivityScenario<AirQuality> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.recommended_places_to_live)).perform(click());
        onView(withId(R.id.air_quality_plot_page)).check(matches(isDisplayed()));
    }

    @Test
    public void test_newsUpdatesButton_toNewsUpdatesPage() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),AirQuality.class);
        ActivityScenario<AirQuality> activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.news_updates)).perform(click());
        onView(withId(R.id.news_updates_page)).check(matches(isDisplayed()));
    }

//    @Test
//    public void test_listAreasButton_toListAreasPage() {
//        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(),AirQuality.class);
//        ActivityScenario<AirQuality> activityScenario = ActivityScenario.launch(intent);
//
//        onView(withId(R.id.list_of_areas)).perform(click());
//        onView(withId(R.id.sorted_areas_page)).check(matches(isDisplayed()));
//    }
}