package com.capstone.chesscommander.chesscommander;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Melvin on 3/31/16.
 */
@RunWith(AndroidJUnit4.class)
public class HomeScreenTest {
    @Rule
    public final ActivityTestRule<home_screen> main = new ActivityTestRule<>(home_screen.class);

    @Test
    public void ItemsAreDisplayed(){
        onView(withId(R.id.home_screen_logo_imageView)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.home_screen_pve_button)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.home_screen_pvp_button)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.home_screen_freeplay_button)).check(ViewAssertions.matches(isDisplayed()));
    }
    @Test
    public void ItemsAreClickable(){
        onView(withId(R.id.home_screen_pve_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.home_screen_pvp_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.home_screen_freeplay_button)).check(ViewAssertions.matches(isClickable()));

    }
    @Test
    public void ItemsHaveCorrectContentDescription(){
        onView(withId(R.id.home_screen_logo_imageView)).check(ViewAssertions.matches(withContentDescription("Chess Commander Home Screen")));
        onView(withId(R.id.home_screen_pve_button)).check(ViewAssertions.matches(withContentDescription("Play against computer")));
        onView(withId(R.id.home_screen_pvp_button)).check(ViewAssertions.matches(withContentDescription("Play against another Player")));
        onView(withId(R.id.home_screen_freeplay_button)).check(ViewAssertions.matches(withContentDescription("Free Play")));
    }
    @Test
    public void ItemsSendToCorrectActivity(){
        onView(withId(R.id.home_screen_pve_button)).perform(click());
        onView(withId(R.id.pve_color_textView)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.pve_color_textView)).perform(pressBack());

        onView(withId(R.id.home_screen_pvp_button)).perform(click());
        onView(withId(R.id.game_screen_voice_button)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.game_screen_voice_button)).perform(pressBack());

        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Choose opponent type")).check(ViewAssertions.matches(isDisplayed()));
        onView(withText("Computer")).perform(click());
        onView(withId(R.id.freeplay_p1Color_textView)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Choose opponent type")).check(ViewAssertions.matches(isDisplayed()));
        onView(withText("Player")).perform(click());
        onView(withId(R.id.fp_boardsetup_piecesbackground_imageview)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.pressBack();
    }

}
