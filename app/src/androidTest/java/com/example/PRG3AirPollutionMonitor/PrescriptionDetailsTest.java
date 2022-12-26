package com.example.PRG3AirPollutionMonitor;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.example.PRG3AirPollutionMonitor.PrescriptionDetails.SHARED_PREFS;
import static com.example.PRG3AirPollutionMonitor.PrescriptionDetails.TEXT3;
import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

public class PrescriptionDetailsTest {

    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent(getInstrumentation().getTargetContext(),PrescriptionDetails.class);
        ActivityScenario<PrescriptionDetails> activityScenario = ActivityScenario.launch(intent);
    }

    @Test
    public void test_numOfUsesInputSavedAndDisplayed() {
        // Enter 30 into 'Number of uses' input and save
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("30"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that output textview matches input
        onView(withId(R.id.remaining_inhaler_uses)).check(matches(withText("30")));
    }

    @Test
    public void test_expiryDateInputSavedAndDisplayed() {
        // Enter 18/12/2023 into 'Expiry date (DD/MM/YYYY)' input and save
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("18/12/2023"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that output textview matches input
        onView(withId(R.id.prescription_expiration_date)).check(matches(withText("18/12/2023")));
    }

    @Test
    public void test_dosageIntervalInputSaved() {
        // Enter 2 into 'Dosage interval (minutes)' input and save
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("2"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that input saved in system
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedDosageInterval = sharedPreferences.getString(TEXT3,"");
        assertEquals(savedDosageInterval,"2");
    }

    @Test
    public void test_invalidInput_negativeNumOfUses() {
        // Enter -1 into 'Number of uses' input
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("-1"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
    }

    @Test
    public void test_invalidInput_zeroNumOfUses() {
        // Enter 0 into 'Number of uses' input
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("0"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
    }

    @Test
    public void test_invalidInput_decimalNumOfUses() {
        // Enter 1.5 into 'Number of uses' input
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("1.5"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
    }

    @Test
    public void test_invalidInput_dayMonthExpiryDate() {
    }

    @Test
    public void test_invalidInput_negativeDosageInterval() {
    }

    @Test
    public void test_invalidInput_zeroDosageInterval() {
    }

    @Test
    public void test_invalidInput_decimalDosageInterval() {
    }
}