package com.capstone.chesscommander.chesscommander;

import android.os.Bundle;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Melvin on 4/12/16.
 */
@RunWith(AndroidJUnit4.class)
public class gameScreenTest {
    @Rule
    public final ActivityTestRule<home_screen> homeScreen = new ActivityTestRule<>(home_screen.class);



    @Test
    public void ItemsAreDisplayed() {
        onView(withId(R.id.home_screen_pvp_button)).perform(click());

        onView(withId(R.id.A8_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.B8_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.C8_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.D8_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.E8_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.F8_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.G8_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.H8_tile)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.A7_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.B7_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.C7_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.D7_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.E7_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.F7_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.G7_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.H7_tile)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.A6_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.B6_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.C6_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.D6_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.E6_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.F6_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.G6_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.H6_tile)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.A5_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.B5_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.C5_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.D5_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.E5_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.F5_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.G5_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.H5_tile)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.A4_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.B4_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.C4_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.D4_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.E4_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.F4_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.G4_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.H4_tile)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.A3_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.B3_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.C3_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.D3_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.E3_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.F3_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.G3_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.H3_tile)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.A2_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.B2_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.C2_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.D2_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.E2_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.F2_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.G2_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.H2_tile)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.A1_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.B1_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.C1_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.D1_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.E1_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.F1_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.G1_tile)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.H1_tile)).check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.game_screen_voice_button)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.game_screen_options_button)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.game_screen_undo_button)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.game_screen_redo_button)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void ItemsAreClickable() {
        onView(withId(R.id.home_screen_pvp_button)).perform(click());

        onView(withId(R.id.A8_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.B8_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.C8_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.D8_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.E8_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.F8_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.G8_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.H8_button)).check(ViewAssertions.matches(isClickable()));

        onView(withId(R.id.A7_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.B7_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.C7_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.D7_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.E7_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.F7_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.G7_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.H7_button)).check(ViewAssertions.matches(isClickable()));

        onView(withId(R.id.A6_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.B6_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.C6_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.D6_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.E6_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.F6_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.G6_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.H6_button)).check(ViewAssertions.matches(isClickable()));

        onView(withId(R.id.A5_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.B5_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.C5_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.D5_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.E5_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.F5_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.G5_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.H5_button)).check(ViewAssertions.matches(isClickable()));

        onView(withId(R.id.A4_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.B4_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.C4_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.D4_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.E4_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.F4_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.G4_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.H4_button)).check(ViewAssertions.matches(isClickable()));

        onView(withId(R.id.A3_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.B3_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.C3_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.D3_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.E3_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.F3_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.G3_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.H3_button)).check(ViewAssertions.matches(isClickable()));

        onView(withId(R.id.A2_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.B2_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.C2_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.D2_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.E2_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.F2_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.G2_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.H2_button)).check(ViewAssertions.matches(isClickable()));

        onView(withId(R.id.A1_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.B1_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.C1_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.D1_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.E1_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.F1_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.G1_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.H1_button)).check(ViewAssertions.matches(isClickable()));

        onView(withId(R.id.game_screen_voice_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.game_screen_options_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.game_screen_undo_button)).check(ViewAssertions.matches(isClickable()));
        onView(withId(R.id.game_screen_redo_button)).check(ViewAssertions.matches(isClickable()));
    }

    @Test
    public void ItemsHaveCorrectContentDescription() {
        onView(withId(R.id.home_screen_pvp_button)).perform(click());

        onView(withId(R.id.A8_button)).check(ViewAssertions.matches(withContentDescription("A8 black rook")));
        onView(withId(R.id.B8_button)).check(ViewAssertions.matches(withContentDescription("B8 black knight")));
        onView(withId(R.id.C8_button)).check(ViewAssertions.matches(withContentDescription("C8 black bishop")));
        onView(withId(R.id.D8_button)).check(ViewAssertions.matches(withContentDescription("D8 black queen")));
        onView(withId(R.id.E8_button)).check(ViewAssertions.matches(withContentDescription("E8 black king")));
        onView(withId(R.id.F8_button)).check(ViewAssertions.matches(withContentDescription("F8 black bishop")));
        onView(withId(R.id.G8_button)).check(ViewAssertions.matches(withContentDescription("G8 black knight")));
        onView(withId(R.id.H8_button)).check(ViewAssertions.matches(withContentDescription("H8 black rook")));

        onView(withId(R.id.A7_button)).check(ViewAssertions.matches(withContentDescription("A7 black pawn")));
        onView(withId(R.id.B7_button)).check(ViewAssertions.matches(withContentDescription("B7 black pawn")));
        onView(withId(R.id.C7_button)).check(ViewAssertions.matches(withContentDescription("C7 black pawn")));
        onView(withId(R.id.D7_button)).check(ViewAssertions.matches(withContentDescription("D7 black pawn")));
        onView(withId(R.id.E7_button)).check(ViewAssertions.matches(withContentDescription("E7 black pawn")));
        onView(withId(R.id.F7_button)).check(ViewAssertions.matches(withContentDescription("F7 black pawn")));
        onView(withId(R.id.G7_button)).check(ViewAssertions.matches(withContentDescription("G7 black pawn")));
        onView(withId(R.id.H7_button)).check(ViewAssertions.matches(withContentDescription("H7 black pawn")));

        onView(withId(R.id.A6_button)).check(ViewAssertions.matches(withContentDescription("A6")));
        onView(withId(R.id.B6_button)).check(ViewAssertions.matches(withContentDescription("B6")));
        onView(withId(R.id.C6_button)).check(ViewAssertions.matches(withContentDescription("C6")));
        onView(withId(R.id.D6_button)).check(ViewAssertions.matches(withContentDescription("D6")));
        onView(withId(R.id.E6_button)).check(ViewAssertions.matches(withContentDescription("E6")));
        onView(withId(R.id.F6_button)).check(ViewAssertions.matches(withContentDescription("F6")));
        onView(withId(R.id.G6_button)).check(ViewAssertions.matches(withContentDescription("G6")));
        onView(withId(R.id.H6_button)).check(ViewAssertions.matches(withContentDescription("H6")));

        onView(withId(R.id.A5_button)).check(ViewAssertions.matches(withContentDescription("A5")));
        onView(withId(R.id.B5_button)).check(ViewAssertions.matches(withContentDescription("B5")));
        onView(withId(R.id.C5_button)).check(ViewAssertions.matches(withContentDescription("C5")));
        onView(withId(R.id.D5_button)).check(ViewAssertions.matches(withContentDescription("D5")));
        onView(withId(R.id.E5_button)).check(ViewAssertions.matches(withContentDescription("E5")));
        onView(withId(R.id.F5_button)).check(ViewAssertions.matches(withContentDescription("F5")));
        onView(withId(R.id.G5_button)).check(ViewAssertions.matches(withContentDescription("G5")));
        onView(withId(R.id.H5_button)).check(ViewAssertions.matches(withContentDescription("H5")));

        onView(withId(R.id.A4_button)).check(ViewAssertions.matches(withContentDescription("A4")));
        onView(withId(R.id.B4_button)).check(ViewAssertions.matches(withContentDescription("B4")));
        onView(withId(R.id.C4_button)).check(ViewAssertions.matches(withContentDescription("C4")));
        onView(withId(R.id.D4_button)).check(ViewAssertions.matches(withContentDescription("D4")));
        onView(withId(R.id.E4_button)).check(ViewAssertions.matches(withContentDescription("E4")));
        onView(withId(R.id.F4_button)).check(ViewAssertions.matches(withContentDescription("F4")));
        onView(withId(R.id.G4_button)).check(ViewAssertions.matches(withContentDescription("G4")));
        onView(withId(R.id.H4_button)).check(ViewAssertions.matches(withContentDescription("H4")));

        onView(withId(R.id.A3_button)).check(ViewAssertions.matches(withContentDescription("A3")));
        onView(withId(R.id.B3_button)).check(ViewAssertions.matches(withContentDescription("B3")));
        onView(withId(R.id.C3_button)).check(ViewAssertions.matches(withContentDescription("C3")));
        onView(withId(R.id.D3_button)).check(ViewAssertions.matches(withContentDescription("D3")));
        onView(withId(R.id.E3_button)).check(ViewAssertions.matches(withContentDescription("E3")));
        onView(withId(R.id.F3_button)).check(ViewAssertions.matches(withContentDescription("F3")));
        onView(withId(R.id.G3_button)).check(ViewAssertions.matches(withContentDescription("G3")));
        onView(withId(R.id.H3_button)).check(ViewAssertions.matches(withContentDescription("H3")));

        onView(withId(R.id.A2_button)).check(ViewAssertions.matches(withContentDescription("A2 white pawn")));
        onView(withId(R.id.B2_button)).check(ViewAssertions.matches(withContentDescription("B2 white pawn")));
        onView(withId(R.id.C2_button)).check(ViewAssertions.matches(withContentDescription("C2 white pawn")));
        onView(withId(R.id.D2_button)).check(ViewAssertions.matches(withContentDescription("D2 white pawn")));
        onView(withId(R.id.E2_button)).check(ViewAssertions.matches(withContentDescription("E2 white pawn")));
        onView(withId(R.id.F2_button)).check(ViewAssertions.matches(withContentDescription("F2 white pawn")));
        onView(withId(R.id.G2_button)).check(ViewAssertions.matches(withContentDescription("G2 white pawn")));
        onView(withId(R.id.H2_button)).check(ViewAssertions.matches(withContentDescription("H2 white pawn")));

        onView(withId(R.id.A1_button)).check(ViewAssertions.matches(withContentDescription("A1 white rook")));
        onView(withId(R.id.B1_button)).check(ViewAssertions.matches(withContentDescription("B1 white knight")));
        onView(withId(R.id.C1_button)).check(ViewAssertions.matches(withContentDescription("C1 white bishop")));
        onView(withId(R.id.D1_button)).check(ViewAssertions.matches(withContentDescription("D1 white queen")));
        onView(withId(R.id.E1_button)).check(ViewAssertions.matches(withContentDescription("E1 white king")));
        onView(withId(R.id.F1_button)).check(ViewAssertions.matches(withContentDescription("F1 white bishop")));
        onView(withId(R.id.G1_button)).check(ViewAssertions.matches(withContentDescription("G1 white knight")));
        onView(withId(R.id.H1_button)).check(ViewAssertions.matches(withContentDescription("H1 white rook")));

        onView(withId(R.id.game_screen_voice_button)).check(ViewAssertions.matches(withContentDescription("Activate Voice Command")));
        onView(withId(R.id.game_screen_options_button)).check(ViewAssertions.matches(withContentDescription("Options")));
        onView(withId(R.id.game_screen_undo_button)).check(ViewAssertions.matches(withContentDescription("Undo")));
        onView(withId(R.id.game_screen_redo_button)).check(ViewAssertions.matches(withContentDescription("Redo")));
    }

    @Test
    public void ModifyBoardContentDescription() {
        onView(withId(R.id.home_screen_pvp_button)).perform(click());

        onView(withId(R.id.A8_button)).perform(click());
        onView(withId(R.id.A5_button)).perform(click());
        onView(withId(R.id.A5_button)).check(ViewAssertions.matches(withContentDescription("A5 black rook")));
    }

    @Test
    public void ExtrasCorrectlyReceived() {
        Bundle receivedExtras = homeScreen.getActivity().getIntent().getExtras();

    }

    @Test
    public void GameOptionsNewGame() {
        onView(withId(R.id.home_screen_pvp_button)).perform(click());

        onView(withId(R.id.game_screen_options_button)).perform(click());
        onView(withText("New Game")).perform(click());
        onView(withId(R.id.A8_button)).check(ViewAssertions.matches(withContentDescription("A8 black rook")));
        onView(withId(R.id.B8_button)).check(ViewAssertions.matches(withContentDescription("B8 black knight")));
        onView(withId(R.id.C8_button)).check(ViewAssertions.matches(withContentDescription("C8 black bishop")));
        onView(withId(R.id.D8_button)).check(ViewAssertions.matches(withContentDescription("D8 black queen")));
        onView(withId(R.id.E8_button)).check(ViewAssertions.matches(withContentDescription("E8 black king")));
        onView(withId(R.id.F8_button)).check(ViewAssertions.matches(withContentDescription("F8 black bishop")));
        onView(withId(R.id.G8_button)).check(ViewAssertions.matches(withContentDescription("G8 black knight")));
        onView(withId(R.id.H8_button)).check(ViewAssertions.matches(withContentDescription("H8 black rook")));

        onView(withId(R.id.A7_button)).check(ViewAssertions.matches(withContentDescription("A7 black pawn")));
        onView(withId(R.id.B7_button)).check(ViewAssertions.matches(withContentDescription("B7 black pawn")));
        onView(withId(R.id.C7_button)).check(ViewAssertions.matches(withContentDescription("C7 black pawn")));
        onView(withId(R.id.D7_button)).check(ViewAssertions.matches(withContentDescription("D7 black pawn")));
        onView(withId(R.id.E7_button)).check(ViewAssertions.matches(withContentDescription("E7 black pawn")));
        onView(withId(R.id.F7_button)).check(ViewAssertions.matches(withContentDescription("F7 black pawn")));
        onView(withId(R.id.G7_button)).check(ViewAssertions.matches(withContentDescription("G7 black pawn")));
        onView(withId(R.id.H7_button)).check(ViewAssertions.matches(withContentDescription("H7 black pawn")));

        onView(withId(R.id.A6_button)).check(ViewAssertions.matches(withContentDescription("A6")));
        onView(withId(R.id.B6_button)).check(ViewAssertions.matches(withContentDescription("B6")));
        onView(withId(R.id.C6_button)).check(ViewAssertions.matches(withContentDescription("C6")));
        onView(withId(R.id.D6_button)).check(ViewAssertions.matches(withContentDescription("D6")));
        onView(withId(R.id.E6_button)).check(ViewAssertions.matches(withContentDescription("E6")));
        onView(withId(R.id.F6_button)).check(ViewAssertions.matches(withContentDescription("F6")));
        onView(withId(R.id.G6_button)).check(ViewAssertions.matches(withContentDescription("G6")));
        onView(withId(R.id.H6_button)).check(ViewAssertions.matches(withContentDescription("H6")));

        onView(withId(R.id.A5_button)).check(ViewAssertions.matches(withContentDescription("A5")));
        onView(withId(R.id.B5_button)).check(ViewAssertions.matches(withContentDescription("B5")));
        onView(withId(R.id.C5_button)).check(ViewAssertions.matches(withContentDescription("C5")));
        onView(withId(R.id.D5_button)).check(ViewAssertions.matches(withContentDescription("D5")));
        onView(withId(R.id.E5_button)).check(ViewAssertions.matches(withContentDescription("E5")));
        onView(withId(R.id.F5_button)).check(ViewAssertions.matches(withContentDescription("F5")));
        onView(withId(R.id.G5_button)).check(ViewAssertions.matches(withContentDescription("G5")));
        onView(withId(R.id.H5_button)).check(ViewAssertions.matches(withContentDescription("H5")));

        onView(withId(R.id.A4_button)).check(ViewAssertions.matches(withContentDescription("A4")));
        onView(withId(R.id.B4_button)).check(ViewAssertions.matches(withContentDescription("B4")));
        onView(withId(R.id.C4_button)).check(ViewAssertions.matches(withContentDescription("C4")));
        onView(withId(R.id.D4_button)).check(ViewAssertions.matches(withContentDescription("D4")));
        onView(withId(R.id.E4_button)).check(ViewAssertions.matches(withContentDescription("E4")));
        onView(withId(R.id.F4_button)).check(ViewAssertions.matches(withContentDescription("F4")));
        onView(withId(R.id.G4_button)).check(ViewAssertions.matches(withContentDescription("G4")));
        onView(withId(R.id.H4_button)).check(ViewAssertions.matches(withContentDescription("H4")));

        onView(withId(R.id.A3_button)).check(ViewAssertions.matches(withContentDescription("A3")));
        onView(withId(R.id.B3_button)).check(ViewAssertions.matches(withContentDescription("B3")));
        onView(withId(R.id.C3_button)).check(ViewAssertions.matches(withContentDescription("C3")));
        onView(withId(R.id.D3_button)).check(ViewAssertions.matches(withContentDescription("D3")));
        onView(withId(R.id.E3_button)).check(ViewAssertions.matches(withContentDescription("E3")));
        onView(withId(R.id.F3_button)).check(ViewAssertions.matches(withContentDescription("F3")));
        onView(withId(R.id.G3_button)).check(ViewAssertions.matches(withContentDescription("G3")));
        onView(withId(R.id.H3_button)).check(ViewAssertions.matches(withContentDescription("H3")));

        onView(withId(R.id.A2_button)).check(ViewAssertions.matches(withContentDescription("A2 white pawn")));
        onView(withId(R.id.B2_button)).check(ViewAssertions.matches(withContentDescription("B2 white pawn")));
        onView(withId(R.id.C2_button)).check(ViewAssertions.matches(withContentDescription("C2 white pawn")));
        onView(withId(R.id.D2_button)).check(ViewAssertions.matches(withContentDescription("D2 white pawn")));
        onView(withId(R.id.E2_button)).check(ViewAssertions.matches(withContentDescription("E2 white pawn")));
        onView(withId(R.id.F2_button)).check(ViewAssertions.matches(withContentDescription("F2 white pawn")));
        onView(withId(R.id.G2_button)).check(ViewAssertions.matches(withContentDescription("G2 white pawn")));
        onView(withId(R.id.H2_button)).check(ViewAssertions.matches(withContentDescription("H2 white pawn")));

        onView(withId(R.id.A1_button)).check(ViewAssertions.matches(withContentDescription("A1 white rook")));
        onView(withId(R.id.B1_button)).check(ViewAssertions.matches(withContentDescription("B1 white knight")));
        onView(withId(R.id.C1_button)).check(ViewAssertions.matches(withContentDescription("C1 white bishop")));
        onView(withId(R.id.D1_button)).check(ViewAssertions.matches(withContentDescription("D1 white queen")));
        onView(withId(R.id.E1_button)).check(ViewAssertions.matches(withContentDescription("E1 white king")));
        onView(withId(R.id.F1_button)).check(ViewAssertions.matches(withContentDescription("F1 white bishop")));
        onView(withId(R.id.G1_button)).check(ViewAssertions.matches(withContentDescription("G1 white knight")));
        onView(withId(R.id.H1_button)).check(ViewAssertions.matches(withContentDescription("H1 white rook")));

    }

    @Test
    public void GameOptionsHomeScreen() {
        onView(withId(R.id.home_screen_pvp_button)).perform(click());

        onView(withId(R.id.game_screen_options_button)).perform(click());
        onView(withText("Home Screen")).perform(click());
        onView(withId(R.id.home_screen_logo_imageView)).check(ViewAssertions.matches(isDisplayed()));
    }

}
