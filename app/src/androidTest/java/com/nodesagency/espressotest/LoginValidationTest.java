package com.nodesagency.espressotest;

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static android.support.test.espresso.Espresso.onView;

/**
 * Created by liamakakpo on 14/07/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginValidationTest {

    /**
     * .       .           .
     * .    .     .       .
     * .       .
     * .       .
     * .  .      .
     * .    ..
     * .    .
     * ## .  .   .    ####
     * ##.............,## ##
     * ##..............##  ##
     * ##..............## ##
     * ##.............###
     * ##...........##
     * ############
     * ##########
     * ################
     * <p/>
     * <p/>
     * Here we use espresso to test various invalid usernames and passwords on the
     * activity's input fields and wait for the correct error message.
     * <p/>
     * When setting up Espresso on your project, beware of version conflicts with
     * the com.android.support:support-annotations library. There seems to be an
     * outdated version of this being used within the library and this can
     * be quite problematic
     * <p/>
     * Prerequisites * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * <p/>
     * This class is annotated with @RunWith(AndroidJUnit4.class) and @LargeTest.
     * <p/>
     * To call some of the essential methods used in this test and many of
     * the examples online, you will have to import these methods. Here are
     * some of the essentials
     * <p/>
     * import static android.support.test.espresso.assertion.ViewAssertions.matches;
     * import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
     * import static android.support.test.espresso.matcher.ViewMatchers.withId;
     * import static android.support.test.espresso.matcher.ViewMatchers.withText;
     * <p/>
     * import static android.support.test.espresso.Espresso.onView;
     * <p/>
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public static final String VALID_EMAIL = "android@nodes.com";
    public static final String VALID_PASSWORD = "Password1";

    public static final String INVALID_EMAIL_NO_DOMAIN = "android";
    public static final String INVALID_EMAIL_NO_DOMAIN_EXTENSION = "android@nodes";
    public static final String INVALID_EMAIL_NO_NAME = "android.com";

    public static final String INVALID_PASSWORD_TOO_SHORT = "pass";
    public static final String INVALID_PASSWORD_ALL_NO_NUMBER = "Password";
    public static final String INVALID_PASSWORD_ALL_LOWERCASE = "password1";

    private Activity activity;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class) { // This method sets the target activity to be tested
        @Override
        protected void afterActivityLaunched() {
            super.afterActivityLaunched();
            LoginValidationTest.this.activity = getActivity();                                      // You can optionally override some of the test lifecycle
        }                                                                                           // methods here. Others include
        // beforeActivityFinished() and beforeActivityLaunched()
    };

    /**
     * A simple example of an espresso based test
     * <p/>
     * onView       View of interest
     * withId       your espresso equivalent of 'findViewById'
     * perform      specify a ViewAction to use on this view
     * <p/>
     * To make an assertion, use the check and matches methods
     */

    @Test
    public void test_invalidEmail_NoDomain() {
        onView(withId(R.id.userNameInput))
                .perform(ViewActions.typeText(INVALID_EMAIL_NO_DOMAIN));    // Enter an invalid email address
        onView(withId(R.id.passwordInput))
                .perform(ViewActions.typeText(VALID_PASSWORD));             // Enter a valid password
        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());
        onView(withText(R.string.email_invalid))
                .check(matches(isDisplayed()));                             // Check if the correct error is displayed
    }

    @Test
    public void test_invalidEmail_NoDomainExtension() {
        onView(withId(R.id.userNameInput))
                .perform(ViewActions.typeText(INVALID_EMAIL_NO_DOMAIN_EXTENSION));      // Enter an invalid email address
        onView(withId(R.id.passwordInput))
                .perform(ViewActions.typeText(VALID_PASSWORD));                         // Enter a valid password
        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());
        onView(withText(R.string.email_invalid))
                .check(matches(isDisplayed()));                                         // Check if the correct error is displayed
    }

    @Test
    public void test_invalidPassword_TooShort() {
        onView(withId(R.id.userNameInput))
                .perform(ViewActions.typeText(VALID_EMAIL));                            // Enter an invalid email address
        onView(withId(R.id.passwordInput))
                .perform(ViewActions.typeText(INVALID_PASSWORD_TOO_SHORT));             // Enter a valid password
        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());
        onView(withText(R.string.password_error_too_short))
                .check(matches(isDisplayed()));                                         // Check if the correct error is displayed
    }

    @Test
    public void test_invalidPassword_AllLowerCase() {
        onView(withId(R.id.userNameInput))
                .perform(ViewActions.typeText(VALID_EMAIL));                            // Enter an invalid email address
        onView(withId(R.id.passwordInput))
                .perform(ViewActions.typeText(INVALID_PASSWORD_ALL_LOWERCASE));         // Enter a valid password
        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());
        onView(withText(R.string.password_error_case))
                .check(matches(isDisplayed()));                                         // Check if the correct error is displayed
    }

    @Test
    public void test_invalidPassword_NoNumber() {
        onView(withId(R.id.userNameInput))
                .perform(ViewActions.typeText(VALID_EMAIL));                            // Enter an invalid email address
        onView(withId(R.id.passwordInput))
                .perform(ViewActions.typeText(INVALID_PASSWORD_ALL_NO_NUMBER));         // Enter a valid password
        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());
        onView(withText(R.string.password_error_case))
                .check(matches(isDisplayed()));                                         // Check if the correct error is displayed
    }


    private void goToMonkey() {                                                         // Helper method - this procedure navigates
        // MonkeyActivity.
        onView(withId(R.id.userNameInput))
                .perform(ViewActions.typeText(VALID_EMAIL));                            // Enter an invalid email address
        onView(withId(R.id.passwordInput))
                .perform(ViewActions.typeText(VALID_PASSWORD));                         // Enter a valid password
        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());
    }

    @Test
    public void test_showMeTheMonkey() {
        goToMonkey();

        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());
        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());

    }


    @Test
    public void test_showMeTheOtherAnimals() {
        goToMonkey();

        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());
        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());
        onView(withId(R.id.submitButton))
                .perform(ViewActions.click());

        // now we should be at the pager

        for (int i = 0; i < 5; i++) {
            // First Fragment
            onView(withId(R.id.animal1_1))
                    .perform(ViewActions.click());

            // Second Fragment  - 1st animal is trigger
            onView(withId(R.id.animal2_2))
                    .perform(ViewActions.click());
            onView(withId(R.id.animal2_1))
                    .perform(ViewActions.click());

            // Third Fragment - 2nd animal is trigger
            onView(withId(R.id.animal3_1))
                    .perform(ViewActions.click());

            onView(withId(R.id.animal3_3))
                    .perform(ViewActions.click());

            onView(withId(R.id.animal3_2))
                    .perform(ViewActions.click());

            // back to start. We should be able to loop at least 20 times

        }

    }


}