package com.capstone.chesscommander.chesscommander;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.CharMatcher.is;
import static android.support.test.espresso.core.deps.guava.base.Predicates.not;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.rule.ActivityTestRule.*;

/**
 * Created by Melvin on 4/3/16.
 */
@RunWith(AndroidJUnit4.class)
//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class)
public class pveOptionsTest {
    @Rule
   public final ActivityTestRule<pve_options> main2 = new ActivityTestRule<>(pve_options.class);
@Test
public void test(){

}

    @Test
    public void ItemsAreDisplayed(){
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
        onView(withId(R.id.pve_difficulty_easy_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.pve_difficulty_medium_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.pve_difficulty_hard_radioButton)).check(matches(isClickable()));

        onView(withId(R.id.pve_color_white_radioButton)).check(matches(isClickable()));
        onView(withId(R.id.pve_color_black_radioButton)).check(matches(isClickable()));

        onView(withId(R.id.pve_start_button)).check(matches(isClickable()));

    }
    @Test
    public void ItemsHaveCorrectContentDescription(){

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
        onView(withId(R.id.pve_difficulty_easy_radioButton)).perform(click());
        onView(withId(R.id.pve_start_button)).perform(click());
        //ShadowToast.showedToast("expected message");
        //onView(withText(R.string.pve_select_color_toast)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        onView(withText("Must select the piece color")).check(matches(isDisplayed()));
        onView(withId(R.id.pve_logo_imageView)).check(matches(isDisplayed()));

        onView(withId(R.id.pve_difficulty_medium_radioButton)).perform(click());
        onView(withId(R.id.pve_start_button)).perform(click());
        onView(withText("Must select the piece color")).check(matches(isDisplayed()));
        onView(withId(R.id.pve_logo_imageView)).check(matches(isDisplayed()));

        onView(withId(R.id.pve_difficulty_hard_radioButton)).perform(click());
        onView(withId(R.id.pve_start_button)).perform(click());
        onView(withText("Must select the piece color")).check(matches(isDisplayed()));
        onView(withId(R.id.pve_logo_imageView)).check(matches(isDisplayed()));

    }
    @Test
    public void DontStartWithColorOnly(){

    }
}

// http://qaautomated.blogspot.com/2016/01/how-to-test-toast-message-using-espresso.html 