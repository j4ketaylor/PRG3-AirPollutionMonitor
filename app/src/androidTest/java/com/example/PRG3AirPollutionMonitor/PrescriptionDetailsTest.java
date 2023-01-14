package com.example.PRG3AirPollutionMonitor;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.example.PRG3AirPollutionMonitor.PrescriptionDetails.SHARED_PREFS;
import static com.example.PRG3AirPollutionMonitor.PrescriptionDetails.TEXT;
import static com.example.PRG3AirPollutionMonitor.PrescriptionDetails.TEXT2;
import static com.example.PRG3AirPollutionMonitor.PrescriptionDetails.TEXT3;
import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.Root;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrescriptionDetailsTest {

    @Before
    public void setUp() throws Exception {
        // Creates and launches an instance of the PrescriptionDetails activity
        Intent intent = new Intent(getInstrumentation().getTargetContext(),PrescriptionDetails.class);
        ActivityScenario<PrescriptionDetails> activityScenario = ActivityScenario.launch(intent);
    }

    @Test
    public void test_numOfUsesInputSaved() {

        // Enter valid inputs and save (all inputs must be filled to save properly)
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("30"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("18/12/2023"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("2"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that input saved in system
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedNumOfUses = sharedPreferences.getString(TEXT,"");
        assertEquals(savedNumOfUses,"30");
    }

    @Test
    public void test_numOfUsesInputDisplayed() {
        // Enter valid inputs and save (all inputs must be filled to save properly)
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("30"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("18/12/2023"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("2"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that output textview matches input
        onView(withId(R.id.remaining_inhaler_uses)).check(matches(withText("30")));
    }

    @Test
    public void test_expiryDateInputSaved() {
        // Enter valid inputs and save (all inputs must be filled to save properly)
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("30"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("18/12/2023"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("2"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that input saved in system
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedExpiryDate = sharedPreferences.getString(TEXT2,"");
        assertEquals(savedExpiryDate,"18/12/2023");
    }

    @Test
    public void test_expiryDateInputDisplayed() {
        // Enter valid inputs and save (all inputs must be filled to save properly)
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("30"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("18/12/2023"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("2"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that output textview matches input
        onView(withId(R.id.prescription_expiration_date)).check(matches(withText("18/12/2023")));
    }

    @Test
    public void test_dosageIntervalInputSaved() {
        // Enter valid inputs and save (all inputs must be filled to save properly)
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("30"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("18/12/2023"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("2"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that input saved in system
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        String savedDosageInterval = sharedPreferences.getString(TEXT3,"");
        assertEquals(savedDosageInterval,"2");
    }

    @Test
    public void test_invalidInput_zeroNumOfUses() {
        // Enter 0 into 'Number of uses' (other inputs valid) and save (all inputs must be filled to save properly)
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("0"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("19/12/2023"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("3"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that all inputs NOT saved in system
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedNoOfUses = sharedPreferences.getString(TEXT, "");
        String savedExpiryDate = sharedPreferences.getString(TEXT2,"");
        String savedDosageInterval = sharedPreferences.getString(TEXT3,"");
        assertNotEquals(savedNoOfUses, "0");
        assertNotEquals(savedExpiryDate,"19/12/2023");
        assertNotEquals(savedDosageInterval,"3");
    }

    @Test
    public void test_invalidInput_pastExpiryDate() {
        // Enter 12/12/2022 into 'Expiry date' (other inputs valid) and save
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("31"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("12/12/2022"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("1"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that all inputs NOT saved in system
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedNoOfUses = sharedPreferences.getString(TEXT, "");
        String savedExpiryDate = sharedPreferences.getString(TEXT2,"");
        String savedDosageInterval = sharedPreferences.getString(TEXT3,"");
        assertNotEquals(savedNoOfUses, "31");
        assertNotEquals(savedExpiryDate,"12/12/2022");
        assertNotEquals(savedDosageInterval,"1");
    }

    @Test
    public void test_invalidInput_invalidDayExpiryDate() {
        // Enter 32/12/2024 into 'Expiry date' (other inputs valid) and save
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("31"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("32/12/2024"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("1"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that all inputs NOT saved in system
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedNoOfUses = sharedPreferences.getString(TEXT, "");
        String savedExpiryDate = sharedPreferences.getString(TEXT2,"");
        String savedDosageInterval = sharedPreferences.getString(TEXT3,"");
        assertNotEquals(savedNoOfUses, "31");
        assertNotEquals(savedExpiryDate,"32/12/2024");
        assertNotEquals(savedDosageInterval,"1");
    }

    @Test
    public void test_invalidInput_invalidMonthExpiryDate() {
        // Enter 20/13/2024 into 'Expiry date' (other inputs valid) and save
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("31"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("20/13/2024"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("1"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that all inputs NOT saved in system
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedNoOfUses = sharedPreferences.getString(TEXT, "");
        String savedExpiryDate = sharedPreferences.getString(TEXT2,"");
        String savedDosageInterval = sharedPreferences.getString(TEXT3,"");
        assertNotEquals(savedNoOfUses, "31");
        assertNotEquals(savedExpiryDate,"20/13/2024");
        assertNotEquals(savedDosageInterval,"1");
    }

    @Test
    public void test_invalidInput_negativeDosageInterval() {
        // Enter -1 into 'Dosage interval' (other inputs valid) and save
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("31"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("19/12/2023"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("-1"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that all inputs NOT saved in system
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedNoOfUses = sharedPreferences.getString(TEXT, "");
        String savedExpiryDate = sharedPreferences.getString(TEXT2,"");
        String savedDosageInterval = sharedPreferences.getString(TEXT3,"");
        assertNotEquals(savedNoOfUses, "31");
        assertNotEquals(savedExpiryDate,"19/12/2023");
        assertNotEquals(savedDosageInterval,"-1");
    }

    @Test
    public void test_invalidInput_zeroDosageInterval() {
        // Enter 0 into 'Dosage interval' (other inputs valid) and save
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(replaceText("31"));
        onView(withId(R.id.new_prescription_number_of_uses_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(replaceText("19/12/2023"));
        onView(withId(R.id.new_prescription_expiry_date_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(replaceText("0"));
        onView(withId(R.id.new_prescription_dosage_interval_input)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.save_text_button)).perform(click());

        // Check that all inputs NOT saved in system
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedNoOfUses = sharedPreferences.getString(TEXT, "");
        String savedExpiryDate = sharedPreferences.getString(TEXT2,"");
        String savedDosageInterval = sharedPreferences.getString(TEXT3,"");
        assertNotEquals(savedNoOfUses, "31");
        assertNotEquals(savedExpiryDate,"19/12/2023");
        assertNotEquals(savedDosageInterval,"0");
    }

}