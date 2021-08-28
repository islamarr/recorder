package com.islam.recorder.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.islam.recorder.R
import com.islam.recorder.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun launchFragment_Returns_MainButtons() {

        launchFragmentInHiltContainer<MainFragment>(Bundle(), R.style.Theme_Recorder)

        onView(withId(R.id.startRecordBtn))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.showRecordingsBtn))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun clickShowRecordingsBtn_navigateToRecordings() {

        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<MainFragment> {
            Navigation.setViewNavController(this.view!!, navController)
        }
        onView(withId(R.id.showRecordingsBtn)).perform(click())

        verify(navController)
            .navigate(MainFragmentDirections.actionMainFragmentToRecordingsFragment())

    }

}