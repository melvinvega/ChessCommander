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
public class fpBoardSetupTest {
    @Rule
    public final ActivityTestRule<home_screen> homeScreen = new ActivityTestRule<>(home_screen.class);



    @Test
    public void ItemsAreDisplayed() {
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Player")).perform(click());

        onView(withId(R.id.A8_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.B8_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.C8_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.D8_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.E8_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.F8_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.G8_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.H8_tile)).check(matches(isDisplayed()));

        onView(withId(R.id.A7_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.B7_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.C7_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.D7_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.E7_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.F7_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.G7_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.H7_tile)).check(matches(isDisplayed()));

        onView(withId(R.id.A6_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.B6_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.C6_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.D6_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.E6_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.F6_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.G6_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.H6_tile)).check(matches(isDisplayed()));

        onView(withId(R.id.A5_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.B5_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.C5_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.D5_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.E5_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.F5_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.G5_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.H5_tile)).check(matches(isDisplayed()));

        onView(withId(R.id.A4_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.B4_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.C4_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.D4_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.E4_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.F4_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.G4_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.H4_tile)).check(matches(isDisplayed()));

        onView(withId(R.id.A3_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.B3_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.C3_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.D3_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.E3_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.F3_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.G3_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.H3_tile)).check(matches(isDisplayed()));

        onView(withId(R.id.A2_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.B2_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.C2_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.D2_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.E2_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.F2_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.G2_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.H2_tile)).check(matches(isDisplayed()));

        onView(withId(R.id.A1_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.B1_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.C1_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.D1_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.E1_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.F1_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.G1_tile)).check(matches(isDisplayed()));
        onView(withId(R.id.H1_tile)).check(matches(isDisplayed()));

        onView(withId(R.id.rook_b_button)).check(matches(isDisplayed()));
        onView(withId(R.id.knight_b_button)).check(matches(isDisplayed()));
        onView(withId(R.id.bishop_b_button)).check(matches(isDisplayed()));
        onView(withId(R.id.queen_b_button)).check(matches(isDisplayed()));
        onView(withId(R.id.king_b_button)).check(matches(isDisplayed()));
        onView(withId(R.id.pawn_b_button)).check(matches(isDisplayed()));

        onView(withId(R.id.rook_w_button)).check(matches(isDisplayed()));
        onView(withId(R.id.knight_w_button)).check(matches(isDisplayed()));
        onView(withId(R.id.bishop_w_button)).check(matches(isDisplayed()));
        onView(withId(R.id.queen_w_button)).check(matches(isDisplayed()));
        onView(withId(R.id.king_w_button)).check(matches(isDisplayed()));
        onView(withId(R.id.pawn_w_button)).check(matches(isDisplayed()));

        onView(withId(R.id.start_button)).check(matches(isDisplayed()));


        //Board
        //Pieces
        //Start Button
    }

    @Test
    public void ItemsAreClickable() {
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Player")).perform(click());

        onView(withId(R.id.A8_button)).check(matches(isClickable()));
        onView(withId(R.id.B8_button)).check(matches(isClickable()));
        onView(withId(R.id.C8_button)).check(matches(isClickable()));
        onView(withId(R.id.D8_button)).check(matches(isClickable()));
        onView(withId(R.id.E8_button)).check(matches(isClickable()));
        onView(withId(R.id.F8_button)).check(matches(isClickable()));
        onView(withId(R.id.G8_button)).check(matches(isClickable()));
        onView(withId(R.id.H8_button)).check(matches(isClickable()));

        onView(withId(R.id.A7_button)).check(matches(isClickable()));
        onView(withId(R.id.B7_button)).check(matches(isClickable()));
        onView(withId(R.id.C7_button)).check(matches(isClickable()));
        onView(withId(R.id.D7_button)).check(matches(isClickable()));
        onView(withId(R.id.E7_button)).check(matches(isClickable()));
        onView(withId(R.id.F7_button)).check(matches(isClickable()));
        onView(withId(R.id.G7_button)).check(matches(isClickable()));
        onView(withId(R.id.H7_button)).check(matches(isClickable()));

        onView(withId(R.id.A6_button)).check(matches(isClickable()));
        onView(withId(R.id.B6_button)).check(matches(isClickable()));
        onView(withId(R.id.C6_button)).check(matches(isClickable()));
        onView(withId(R.id.D6_button)).check(matches(isClickable()));
        onView(withId(R.id.E6_button)).check(matches(isClickable()));
        onView(withId(R.id.F6_button)).check(matches(isClickable()));
        onView(withId(R.id.G6_button)).check(matches(isClickable()));
        onView(withId(R.id.H6_button)).check(matches(isClickable()));

        onView(withId(R.id.A5_button)).check(matches(isClickable()));
        onView(withId(R.id.B5_button)).check(matches(isClickable()));
        onView(withId(R.id.C5_button)).check(matches(isClickable()));
        onView(withId(R.id.D5_button)).check(matches(isClickable()));
        onView(withId(R.id.E5_button)).check(matches(isClickable()));
        onView(withId(R.id.F5_button)).check(matches(isClickable()));
        onView(withId(R.id.G5_button)).check(matches(isClickable()));
        onView(withId(R.id.H5_button)).check(matches(isClickable()));

        onView(withId(R.id.A4_button)).check(matches(isClickable()));
        onView(withId(R.id.B4_button)).check(matches(isClickable()));
        onView(withId(R.id.C4_button)).check(matches(isClickable()));
        onView(withId(R.id.D4_button)).check(matches(isClickable()));
        onView(withId(R.id.E4_button)).check(matches(isClickable()));
        onView(withId(R.id.F4_button)).check(matches(isClickable()));
        onView(withId(R.id.G4_button)).check(matches(isClickable()));
        onView(withId(R.id.H4_button)).check(matches(isClickable()));

        onView(withId(R.id.A3_button)).check(matches(isClickable()));
        onView(withId(R.id.B3_button)).check(matches(isClickable()));
        onView(withId(R.id.C3_button)).check(matches(isClickable()));
        onView(withId(R.id.D3_button)).check(matches(isClickable()));
        onView(withId(R.id.E3_button)).check(matches(isClickable()));
        onView(withId(R.id.F3_button)).check(matches(isClickable()));
        onView(withId(R.id.G3_button)).check(matches(isClickable()));
        onView(withId(R.id.H3_button)).check(matches(isClickable()));

        onView(withId(R.id.A2_button)).check(matches(isClickable()));
        onView(withId(R.id.B2_button)).check(matches(isClickable()));
        onView(withId(R.id.C2_button)).check(matches(isClickable()));
        onView(withId(R.id.D2_button)).check(matches(isClickable()));
        onView(withId(R.id.E2_button)).check(matches(isClickable()));
        onView(withId(R.id.F2_button)).check(matches(isClickable()));
        onView(withId(R.id.G2_button)).check(matches(isClickable()));
        onView(withId(R.id.H2_button)).check(matches(isClickable()));

        onView(withId(R.id.A1_button)).check(matches(isClickable()));
        onView(withId(R.id.B1_button)).check(matches(isClickable()));
        onView(withId(R.id.C1_button)).check(matches(isClickable()));
        onView(withId(R.id.D1_button)).check(matches(isClickable()));
        onView(withId(R.id.E1_button)).check(matches(isClickable()));
        onView(withId(R.id.F1_button)).check(matches(isClickable()));
        onView(withId(R.id.G1_button)).check(matches(isClickable()));
        onView(withId(R.id.H1_button)).check(matches(isClickable()));

        onView(withId(R.id.rook_b_button)).check(matches(isClickable()));
        onView(withId(R.id.knight_b_button)).check(matches(isClickable()));
        onView(withId(R.id.bishop_b_button)).check(matches(isClickable()));
        onView(withId(R.id.queen_b_button)).check(matches(isClickable()));
        onView(withId(R.id.king_b_button)).check(matches(isClickable()));
        onView(withId(R.id.pawn_b_button)).check(matches(isClickable()));

        onView(withId(R.id.rook_w_button)).check(matches(isClickable()));
        onView(withId(R.id.knight_w_button)).check(matches(isClickable()));
        onView(withId(R.id.bishop_w_button)).check(matches(isClickable()));
        onView(withId(R.id.queen_w_button)).check(matches(isClickable()));
        onView(withId(R.id.king_w_button)).check(matches(isClickable()));
        onView(withId(R.id.pawn_w_button)).check(matches(isClickable()));

        onView(withId(R.id.start_button)).check(matches(isClickable()));
        
    }

    @Test
    public void ItemsHaveCorrectContentDescription() {
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Player")).perform(click());


        onView(withId(R.id.A8_button)).check(matches(withContentDescription("A8")));
        onView(withId(R.id.B8_button)).check(matches(withContentDescription("B8")));
        onView(withId(R.id.C8_button)).check(matches(withContentDescription("C8")));
        onView(withId(R.id.D8_button)).check(matches(withContentDescription("D8")));
        onView(withId(R.id.E8_button)).check(matches(withContentDescription("E8")));
        onView(withId(R.id.F8_button)).check(matches(withContentDescription("F8")));
        onView(withId(R.id.G8_button)).check(matches(withContentDescription("G8")));
        onView(withId(R.id.H8_button)).check(matches(withContentDescription("H8")));

        onView(withId(R.id.A7_button)).check(matches(withContentDescription("A7")));
        onView(withId(R.id.B7_button)).check(matches(withContentDescription("B7")));
        onView(withId(R.id.C7_button)).check(matches(withContentDescription("C7")));
        onView(withId(R.id.D7_button)).check(matches(withContentDescription("D7")));
        onView(withId(R.id.E7_button)).check(matches(withContentDescription("E7")));
        onView(withId(R.id.F7_button)).check(matches(withContentDescription("F7")));
        onView(withId(R.id.G7_button)).check(matches(withContentDescription("G7")));
        onView(withId(R.id.H7_button)).check(matches(withContentDescription("H7")));

        onView(withId(R.id.A6_button)).check(matches(withContentDescription("A6")));
        onView(withId(R.id.B6_button)).check(matches(withContentDescription("B6")));
        onView(withId(R.id.C6_button)).check(matches(withContentDescription("C6")));
        onView(withId(R.id.D6_button)).check(matches(withContentDescription("D6")));
        onView(withId(R.id.E6_button)).check(matches(withContentDescription("E6")));
        onView(withId(R.id.F6_button)).check(matches(withContentDescription("F6")));
        onView(withId(R.id.G6_button)).check(matches(withContentDescription("G6")));
        onView(withId(R.id.H6_button)).check(matches(withContentDescription("H6")));

        onView(withId(R.id.A5_button)).check(matches(withContentDescription("A5")));
        onView(withId(R.id.B5_button)).check(matches(withContentDescription("B5")));
        onView(withId(R.id.C5_button)).check(matches(withContentDescription("C5")));
        onView(withId(R.id.D5_button)).check(matches(withContentDescription("D5")));
        onView(withId(R.id.E5_button)).check(matches(withContentDescription("E5")));
        onView(withId(R.id.F5_button)).check(matches(withContentDescription("F5")));
        onView(withId(R.id.G5_button)).check(matches(withContentDescription("G5")));
        onView(withId(R.id.H5_button)).check(matches(withContentDescription("H5")));

        onView(withId(R.id.A4_button)).check(matches(withContentDescription("A4")));
        onView(withId(R.id.B4_button)).check(matches(withContentDescription("B4")));
        onView(withId(R.id.C4_button)).check(matches(withContentDescription("C4")));
        onView(withId(R.id.D4_button)).check(matches(withContentDescription("D4")));
        onView(withId(R.id.E4_button)).check(matches(withContentDescription("E4")));
        onView(withId(R.id.F4_button)).check(matches(withContentDescription("F4")));
        onView(withId(R.id.G4_button)).check(matches(withContentDescription("G4")));
        onView(withId(R.id.H4_button)).check(matches(withContentDescription("H4")));

        onView(withId(R.id.A3_button)).check(matches(withContentDescription("A3")));
        onView(withId(R.id.B3_button)).check(matches(withContentDescription("B3")));
        onView(withId(R.id.C3_button)).check(matches(withContentDescription("C3")));
        onView(withId(R.id.D3_button)).check(matches(withContentDescription("D3")));
        onView(withId(R.id.E3_button)).check(matches(withContentDescription("E3")));
        onView(withId(R.id.F3_button)).check(matches(withContentDescription("F3")));
        onView(withId(R.id.G3_button)).check(matches(withContentDescription("G3")));
        onView(withId(R.id.H3_button)).check(matches(withContentDescription("H3")));

        onView(withId(R.id.A2_button)).check(matches(withContentDescription("A2")));
        onView(withId(R.id.B2_button)).check(matches(withContentDescription("B2")));
        onView(withId(R.id.C2_button)).check(matches(withContentDescription("C2")));
        onView(withId(R.id.D2_button)).check(matches(withContentDescription("D2")));
        onView(withId(R.id.E2_button)).check(matches(withContentDescription("E2")));
        onView(withId(R.id.F2_button)).check(matches(withContentDescription("F2")));
        onView(withId(R.id.G2_button)).check(matches(withContentDescription("G2")));
        onView(withId(R.id.H2_button)).check(matches(withContentDescription("H2")));

        onView(withId(R.id.A1_button)).check(matches(withContentDescription("A1")));
        onView(withId(R.id.B1_button)).check(matches(withContentDescription("B1")));
        onView(withId(R.id.C1_button)).check(matches(withContentDescription("C1")));
        onView(withId(R.id.D1_button)).check(matches(withContentDescription("D1")));
        onView(withId(R.id.E1_button)).check(matches(withContentDescription("E1")));
        onView(withId(R.id.F1_button)).check(matches(withContentDescription("F1")));
        onView(withId(R.id.G1_button)).check(matches(withContentDescription("G1")));
        onView(withId(R.id.H1_button)).check(matches(withContentDescription("H1")));

        onView(withId(R.id.rook_b_button)).check(matches(withContentDescription("Black Rook")));
        onView(withId(R.id.knight_b_button)).check(matches(withContentDescription("Black Knight")));
        onView(withId(R.id.bishop_b_button)).check(matches(withContentDescription("Black Bishop")));
        onView(withId(R.id.queen_b_button)).check(matches(withContentDescription("Black Queen")));
        onView(withId(R.id.king_b_button)).check(matches(withContentDescription("Black King")));
        onView(withId(R.id.pawn_b_button)).check(matches(withContentDescription("Black Pawn")));

        onView(withId(R.id.rook_w_button)).check(matches(withContentDescription("White Rook")));
        onView(withId(R.id.knight_w_button)).check(matches(withContentDescription("White Knight")));
        onView(withId(R.id.bishop_w_button)).check(matches(withContentDescription("White Bishop")));
        onView(withId(R.id.queen_w_button)).check(matches(withContentDescription("White Queen")));
        onView(withId(R.id.king_w_button)).check(matches(withContentDescription("White King")));
        onView(withId(R.id.pawn_w_button)).check(matches(withContentDescription("White Pawn")));

        onView(withId(R.id.start_button)).check(matches(withContentDescription("Start Game")));
    }

    @Test
    public void StartCondition() {
        //Less than 1 King
            //White
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.pawn_w_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();
            //Black
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.pawn_b_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();

        //More than 1 king
            //White
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.A3_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();
            //Black
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.A3_button)).perform(click());
            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();

        //More than 9 Queens
            //White
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.queen_w_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();
            //Black
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.queen_b_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();

        //More than 8 Pawns
            //White
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.pawn_w_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();
            //Black
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.pawn_b_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();

        //More than 10 Knights
            //White
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.knight_w_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.D2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();
            //Black
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.knight_b_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.D2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();

        //More than 10 Bishops
            //White
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.bishop_w_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.D2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();
            //Black
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.bishop_b_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.D2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();

        //More than 10 Rooks
            //White
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.rook_w_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.D2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();
            //Black
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.rook_b_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.D2_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();

        //More than 17 Pieces
            //White
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.knight_w_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.D2_button)).perform(click());
            onView(withId(R.id.E2_button)).perform(click());
            onView(withId(R.id.F2_button)).perform(click());
            onView(withId(R.id.G2_button)).perform(click());
            onView(withId(R.id.H2_button)).perform(click());
            onView(withId(R.id.A3_button)).perform(click());
            onView(withId(R.id.B3_button)).perform(click());
            onView(withId(R.id.C3_button)).perform(click());
            onView(withId(R.id.D3_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();
            //Black
            onView(withId(R.id.home_screen_freeplay_button)).perform(click());
            onView(withText("Player")).perform(click());

            onView(withId(R.id.king_w_button)).perform(click());
            onView(withId(R.id.A8_button)).perform(click());
            onView(withId(R.id.king_b_button)).perform(click());
            onView(withId(R.id.B8_button)).perform(click());

            onView(withId(R.id.knight_b_button)).perform(click());
            onView(withId(R.id.A1_button)).perform(click());
            onView(withId(R.id.B1_button)).perform(click());
            onView(withId(R.id.C1_button)).perform(click());
            onView(withId(R.id.D1_button)).perform(click());
            onView(withId(R.id.E1_button)).perform(click());
            onView(withId(R.id.F1_button)).perform(click());
            onView(withId(R.id.G1_button)).perform(click());
            onView(withId(R.id.H1_button)).perform(click());
            onView(withId(R.id.A2_button)).perform(click());
            onView(withId(R.id.B2_button)).perform(click());
            onView(withId(R.id.C2_button)).perform(click());
            onView(withId(R.id.D2_button)).perform(click());
            onView(withId(R.id.E2_button)).perform(click());
            onView(withId(R.id.F2_button)).perform(click());
            onView(withId(R.id.G2_button)).perform(click());
            onView(withId(R.id.H2_button)).perform(click());
            onView(withId(R.id.A3_button)).perform(click());
            onView(withId(R.id.B3_button)).perform(click());
            onView(withId(R.id.C3_button)).perform(click());
            onView(withId(R.id.D3_button)).perform(click());
            onView(withId(R.id.start_button)).perform(click());
            onView(withId(R.id.start_button)).check(matches(isDisplayed()));
            Espresso.pressBack();
    }

    @Test
    public void StartSendsToCorrectActivity() {
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Player")).perform(click());

        onView(withId(R.id.king_w_button)).perform(click());
        onView(withId(R.id.A8_button)).perform(click());
        onView(withId(R.id.king_b_button)).perform(click());
        onView(withId(R.id.B8_button)).perform(click());

        onView(withId(R.id.start_button)).perform(click());
        onView(withId(R.id.game_screen_redo_button)).check(matches(isDisplayed()));
    }


    @Test
    public void ModifyBoardContentDescription() {
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Player")).perform(click());

        onView(withId(R.id.king_w_button)).perform(click());
        onView(withId(R.id.A8_button)).perform(click());
        onView(withId(R.id.A8_button)).check(matches(withContentDescription("A8 white king")));
    }

    @Test
    public void ExtrasCorrectlySent() {
        int[] expectedBoard = {1,7,0,0,0,0,0,0,
                               0,0,0,0,0,0,0,0,
                               0,0,0,0,0,0,0,0,
                               0,0,0,0,0,0,0,0,
                               0,0,0,0,0,0,0,0,
                               0,0,0,0,0,0,0,0,
                               0,0,0,0,0,0,0,0,
                               0,0,0,0,0,0,0,0};

        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Player")).perform(click());

        onView(withId(R.id.king_w_button)).perform(click());
        onView(withId(R.id.A1_button)).perform(click());
        onView(withId(R.id.king_b_button)).perform(click());
        onView(withId(R.id.B1_button)).perform(click());

        Intents.init();
        onView(withId(R.id.start_button)).perform(click());

        intended(hasExtra("GameType", "fp"));
        intended(hasExtra("OpponentType", "Player"));
        intended(hasExtra("Board", expectedBoard));
        Intents.release();
    }

    @Test
    public void ReplacePiece() {
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Player")).perform(click());

        onView(withId(R.id.king_w_button)).perform(click());
        onView(withId(R.id.A1_button)).perform(click());
        onView(withId(R.id.king_b_button)).perform(click());
        onView(withId(R.id.A1_button)).perform(click());
        onView(withId(R.id.A1_button)).check(matches(withContentDescription("A1 black king")));
    }

    @Test
    public void RemovePiece() {
        onView(withId(R.id.home_screen_freeplay_button)).perform(click());
        onView(withText("Player")).perform(click());

        onView(withId(R.id.king_w_button)).perform(click());
        onView(withId(R.id.A1_button)).perform(click());
        onView(withId(R.id.A1_button)).check(matches(withContentDescription("A1 white king")));
        onView(withId(R.id.A1_button)).perform(click());
        onView(withId(R.id.A1_button)).check(matches(withContentDescription("A1")));
    }

}
