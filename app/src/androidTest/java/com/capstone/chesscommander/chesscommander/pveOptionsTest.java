package com.capstone.chesscommander.chesscommander;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Melvin on 4/3/16.
 */
@RunWith(AndroidJUnit4.class)
//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class)
public class pveOptionsTest {

    @Rule
   public final ActivityTestRule<home_screen> homeScreen = new ActivityTestRule<>(home_screen.class);


    @Test
    public void ItemsAreDisplayed(){
        onView(withId(R.id.home_screen_pve_button)).perform(click());

        onView(withId(R.id.pve_logo_imageView)).check(matches(isDisplayed()));

        onView(withId(R.id.pve_difficulty_textview)).check(matches(isDisplayed()));
        onView(withId(R.id.pve_difficulty_easy_radioButton)).check(matches(isDisplayed()));
        onView(withId(R.id.pve_difficulty_medium_radioButton)).check(matches(isDisplayed()));
        onView(withId(R.id.pve_difficulty_hard_radioButton)).check(matches(isDisplayed()));

        onView(withId(R.id.pve_color_textView)).check(matches(isDisplayed()));
        onView(withId(R.id.pve_color_white_radioButton)).check(matches(isDisplayed()));
        onView(withId(R.id.pve_color_black_radioButton)).check(matches(isDisplayed()));

        onView(withId(R.id.pve_start_button)).check(matches(isDisplayed()));
    }

    @Test
    public void ItemsAreClickable(){
        onView(withId(R.id.home_screen_pve_button)).perform(click());

        onView(withId(R.id.pve_difficulty_easy_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.pve_difficulty_medium_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.pve_difficulty_hard_radioButton)).check(matches(isClickable()));

        onView(withId(R.id.pve_color_white_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.pve_color_black_radioButton)).check(matches(isClickable()));

        onView(withId(R.id.pve_start_button)).check(matches(isClickable()));

    }

    @Test
    public void ItemsHaveCorrectContentDescription(){
        onView(withId(R.id.home_screen_pve_button)).perform(click());

        onView(withId(R.id.pve_logo_imageView)).check(matches(withContentDescription("Play against Computer Options")));

        onView(withId(R.id.pve_difficulty_textview)).check(matches(withContentDescription("Select Computer Difficulty")));
        onView(withId(R.id.pve_difficulty_easy_radioButton)).check(matches(withContentDescription("Easy Difficulty")));
        onView(withId(R.id.pve_difficulty_medium_radioButton)).check(matches(withContentDescription("Medium Difficulty")));
        onView(withId(R.id.pve_difficulty_hard_radioButton)).check(matches(withContentDescription("Hard Difficulty")));

        onView(withId(R.id.pve_color_textView)).check(matches(withContentDescription("Select player color")));
        onView(withId(R.id.pve_color_white_radioButton)).check(matches(withContentDescription("Play as white Pieces")));
        onView(withId(R.id.pve_color_black_radioButton)).check(matches(withContentDescription("Play as black Pieces")));

        onView(withId(R.id.pve_start_button)).check(matches(withContentDescription("Start game")));

    }

    @Test
    public void DontStartWithDifficultyOnly(){
        onView(withId(R.id.home_screen_pve_button)).perform(click());

        onView(withId(R.id.pve_difficulty_easy_radioButton)).perform(click());
        onView(withId(R.id.pve_start_button)).perform(click());
        onView(withId(R.id.pve_logo_imageView)).check(matches(isDisplayed()));

        onView(withId(R.id.pve_difficulty_medium_radioButton)).perform(click());
        onView(withId(R.id.pve_start_button)).perform(click());
        onView(withId(R.id.pve_logo_imageView)).check(matches(isDisplayed()));

        onView(withId(R.id.pve_difficulty_hard_radioButton)).perform(click());
        onView(withId(R.id.pve_start_button)).perform(click());
        onView(withId(R.id.pve_logo_imageView)).check(matches(isDisplayed()));
    }

    @Test
    public void DontStartWithColorOnly(){
        onView(withId(R.id.home_screen_pve_button)).perform(click());

        onView(withId(R.id.pve_color_white_radioButton)).perform(click());
        onView(withId(R.id.pve_start_button)).perform(click());
        onView(withId(R.id.pve_logo_imageView)).check(matches(isDisplayed()));

        onView(withId(R.id.pve_color_black_radioButton)).perform(click());
        onView(withId(R.id.pve_start_button)).perform(click());
        onView(withId(R.id.pve_logo_imageView)).check(matches(isDisplayed()));
    }

    @Test
    public void ExtrasCorrectlySent(){
        onView(withId(R.id.home_screen_pve_button)).perform(click());

        onView(withId(R.id.pve_difficulty_easy_radioButton)).perform(click());
        onView(withId(R.id.pve_color_black_radioButton)).perform(click());
        Intents.init();
        onView(withId(R.id.pve_start_button)).perform(click());

        intended(hasExtra("GameType", "pve"));
        intended(hasExtra("PlayerColor", "black"));
        intended(hasExtra("Difficulty", "easy"));
        Intents.release();
    }

}
