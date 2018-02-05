package com.example.android.bakingapplication.view.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.android.bakingapplication.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DetailListActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void detailListActivity() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_recipe_list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction cardView = onView(
                allOf(withId(R.id.ingredient_card_container),
                        withParent(allOf(withId(R.id.fragment_detail_list_constraint_container),
                                withParent(withId(R.id.detail_list_container)))),
                        isDisplayed()));
        cardView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textview_single_ingredient), withText("Graham Cracker crumbs: 2.0 CUP"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv_ingredient_list),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textview_single_ingredient), withText("Unsalted butter, melted: 6.0 TBLSP"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv_ingredient_list),
                                        1),
                                0),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textview_single_ingredient), withText("Granulated sugar: 0.5 CUP"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv_ingredient_list),
                                        2),
                                0),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textview_single_ingredient), withText("Salt: 1.5 TSP"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv_ingredient_list),
                                        3),
                                0),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textview_single_ingredient), withText("Vanilla: 5.0 TBLSP"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv_ingredient_list),
                                        4),
                                0),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textview_single_ingredient), withText("Nutella or other chocolate-hazelnut spread: 1.0 K"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv_ingredient_list),
                                        5),
                                0),
                        isDisplayed()));
        textView6.check(matches(isDisplayed()));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.textview_single_ingredient), withText("Mascapone Cheese(room temperature): 500.0 G"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv_ingredient_list),
                                        6),
                                0),
                        isDisplayed()));
        textView7.check(matches(isDisplayed()));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.textview_single_ingredient), withText("Heavy cream(cold): 1.0 CUP"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv_ingredient_list),
                                        7),
                                0),
                        isDisplayed()));
        textView8.check(matches(isDisplayed()));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.textview_single_ingredient), withText("Cream cheese(softened): 4.0 OZ"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv_ingredient_list),
                                        8),
                                0),
                        isDisplayed()));
        textView9.check(matches(isDisplayed()));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.detail_card_text), withText("Recipe Introduction"),
                        childAtPosition(
                                allOf(withId(R.id.detail_card),
                                        childAtPosition(
                                                withId(R.id.rv_detail_list),
                                                0)),
                                0),
                        isDisplayed()));
        textView10.check(matches(withText("Recipe Introduction")));

        ViewInteraction cardView2 = onView(
                allOf(withId(R.id.ingredient_card_container),
                        withParent(allOf(withId(R.id.fragment_detail_list_constraint_container),
                                withParent(withId(R.id.detail_list_container)))),
                        isDisplayed()));
        cardView2.perform(click());

        ViewInteraction cardView3 = onView(
                allOf(withId(R.id.ingredient_card_container),
                        withParent(allOf(withId(R.id.fragment_detail_list_constraint_container),
                                withParent(withId(R.id.detail_list_container)))),
                        isDisplayed()));
        cardView3.perform(click());

        ViewInteraction cardView4 = onView(
                allOf(withId(R.id.ingredient_card_container),
                        withParent(allOf(withId(R.id.fragment_detail_list_constraint_container),
                                withParent(withId(R.id.detail_list_container)))),
                        isDisplayed()));
        cardView4.perform(click());

        ViewInteraction cardView5 = onView(
                allOf(withId(R.id.ingredient_card_container),
                        withParent(allOf(withId(R.id.fragment_detail_list_constraint_container),
                                withParent(withId(R.id.detail_list_container)))),
                        isDisplayed()));
        cardView5.perform(click());

        ViewInteraction cardView6 = onView(
                allOf(withId(R.id.ingredient_card_container),
                        withParent(allOf(withId(R.id.fragment_detail_list_constraint_container),
                                withParent(withId(R.id.detail_list_container)))),
                        isDisplayed()));
        cardView6.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
