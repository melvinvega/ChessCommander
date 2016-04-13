package com.capstone.chesscommander.chesscommander;

import android.support.test.espresso.Espresso;
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
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Melvin on 4/12/16.
 */
@RunWith(AndroidJUnit4.class)
public class fpOptionsTest {
   @Rule
    public final ActivityTestRule<home_screen> homeScreen = new ActivityTestRule<>(home_screen.class);


    @Test
    public void ItemsAreDisplayed() {
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());

        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_current_move_textView)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_current_move_white_radioButton)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_current_move_black_radioButton)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_play_as_textView)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_play_as_white_radioButton)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_play_as_black_radioButton)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_pvedifficulty_textview)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_difficulty_easy_radioButton)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_difficulty_medium_radioButton)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_difficulty_hard_radioButton)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_next_button)).check(matches(isDisplayed()));
    }

    @Test
    public void ItemsAreClickable() {
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());

        onView(withId(R.id.fp_next_button)).check(matches(isClickable()));
        onView(withId(R.id.fp_current_move_white_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.fp_current_move_black_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.fp_play_as_white_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.fp_play_as_black_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.fp_difficulty_easy_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.fp_difficulty_medium_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.fp_difficulty_hard_radioButton)).check(matches(isClickable()));
    }

    @Test
    public void ItemsHaveCorrectContentDescription() {
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());

        onView(withId(R.id.fp_options_logo_imageView)).check(matches(withContentDescription("Free Play Options")));
        onView(withId(R.id.fp_current_move_textView)).check(matches(withContentDescription("Whose turn is it?")));
        onView(withId(R.id.fp_current_move_white_radioButton)).check(matches(withContentDescription("White pieces turn")));
        onView(withId(R.id.fp_current_move_black_radioButton)).check(matches(withContentDescription("Black pieces turn")));
        onView(withId(R.id.fp_play_as_textView)).check(matches(withContentDescription("Color to play as")));
        onView(withId(R.id.fp_play_as_white_radioButton)).check(matches(withContentDescription("Play as White")));
        onView(withId(R.id.fp_play_as_black_radioButton)).check(matches(withContentDescription("Play as Black")));
        onView(withId(R.id.fp_pvedifficulty_textview)).check(matches(withContentDescription("Select Computer Difficulty")));
        onView(withId(R.id.fp_difficulty_easy_radioButton)).check(matches(withContentDescription("Easy Difficulty")));
        onView(withId(R.id.fp_difficulty_medium_radioButton)).check(matches(withContentDescription("Medium Difficulty")));
        onView(withId(R.id.fp_difficulty_hard_radioButton)).check(matches(withContentDescription("Hard Difficulty")));
        onView(withId(R.id.fp_next_button)).check(matches(withContentDescription("Go to next page")));
    }

    @Test
    public void NextOneOptionsSelected(){

        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());
        onView(withId(R.id.fp_current_move_white_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());
        onView(withId(R.id.fp_current_move_black_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_current_move_black_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());
        onView(withId(R.id.fp_play_as_white_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_play_as_black_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());
        onView(withId(R.id.fp_difficulty_easy_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_difficulty_medium_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        onView(withId(R.id.fp_difficulty_medium_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        Espresso.pressBack();

    }


    @Test
    public void NextTwoOptionsSelected(){

        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());
        onView(withId(R.id.fp_current_move_white_radioButton)).perform(click());
        onView(withId(R.id.fp_play_as_white_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());
        onView(withId(R.id.fp_current_move_black_radioButton)).perform(click());
        onView(withId(R.id.fp_difficulty_medium_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());
        onView(withId(R.id.fp_play_as_black_radioButton)).perform(click());
        onView(withId(R.id.fp_difficulty_easy_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.fp_options_logo_imageView)).check(matches(isDisplayed()));
        Espresso.pressBack();
    }

    @Test
    public void NextAllOptionsSelected(){
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());

        onView(withId(R.id.fp_current_move_black_radioButton)).perform(click());
        onView(withId(R.id.fp_play_as_white_radioButton)).perform(click());
        onView(withId(R.id.fp_difficulty_medium_radioButton)).perform(click());
        onView(withId(R.id.fp_next_button)).perform(click());
        onView(withId(R.id.start_button)).check(matches(isDisplayed()));

    }

    @Test
    public void ExtrasCorrectlySent(){
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Computer")).perform(click());

        onView(withId(R.id.fp_current_move_white_radioButton)).perform(click());
        onView(withId(R.id.fp_play_as_white_radioButton)).perform(click());
        onView(withId(R.id.fp_difficulty_easy_radioButton)).perform(click());
        Intents.init();
        onView(withId(R.id.fp_next_button)).perform(click());


        intended(hasExtra("GameType", "fp"));
        intended(hasExtra("CurrentMove","white"));
        intended(hasExtra("PlayAs","white"));
        intended(hasExtra("Difficulty","easy"));
        intended(hasExtra("OpponentType", "Computer"));
        Intents.release();
    }

}
