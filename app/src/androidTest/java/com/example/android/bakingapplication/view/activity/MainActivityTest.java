package com.example.android.bakingapplication.view.activity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.bakingapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	@Rule
	public final ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(
			MainActivity.class);


	@Test
	public void whenMainActivityLaunchesFourRecipesAreDisplayed() {
		onView(withText("Nutella Pie")).check(matches(isDisplayed()));
		onView(withText("Brownies")).check(matches(isDisplayed()));
		onView(withText("Yellow Cake")).check(matches(isDisplayed()));
		onView(withText("Cheesecake")).check(matches(isDisplayed()));
	}

	@Test
	public void whenMainActivityLaunchesFirstCardDisplaysRecipeOverview() {
		onView(ViewMatchers.withId(R.id.rv_recipe_list))
				.check(matches(hasDescendant(withText("Nutella Pie"))))
				.check(matches(hasDescendant(withText("Steps: 7"))))
				.check(matches(hasDescendant(withText("Ingredients: 9"))))
				.check(matches(hasDescendant(withText("Servings: 8"))));
	}

	@Before
	public void setupIntents() {
		Intents.init();
	}

	@After
	public void releaseIntents() {
		Intents.release();
	}

	@Test
	public void openRecipeDetailListOnRecipeCardClick() {
		onView(withId(R.id.rv_recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
		intended(hasComponent(DetailListActivity.class.getName()));
		intended(hasExtraWithKey("id_of_recipe_selected"));
		intended(hasExtraWithKey("name_of_recipe_selected"));
	}
}