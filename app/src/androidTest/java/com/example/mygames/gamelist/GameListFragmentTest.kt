package com.example.mygames.gamelist

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mygames.MainActivity
import com.example.mygames.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Prashant Rane on 08-03-2022.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class GameListFragmentTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    //Recyclerview comes on to the screen
    @Test
    fun test_list_visible() {
        Espresso.onView(withId(R.id.list_games)).check(ViewAssertions.matches(isDisplayed()))
    }
}