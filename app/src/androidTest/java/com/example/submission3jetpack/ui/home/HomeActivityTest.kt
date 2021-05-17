package com.example.submission3jetpack.ui.home

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.submission3jetpack.R
import com.example.submission3jetpack.data.MoviesTvData
import com.example.submission3jetpack.retrofit.ResponseClass
import com.example.submission3jetpack.utils.DataAPI
import com.example.submission3jetpack.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val moviesData = arrayListOf<MoviesTvData>()
    private val showData = arrayListOf<MoviesTvData>()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)
    private var resources: Resources =
        InstrumentationRegistry.getInstrumentation().targetContext.resources


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        ActivityScenario.launch(HomeActivity::class.java)

    }


    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }


    private fun loadTvDataFromAPI()
    {
        EspressoIdlingResource.increment()
        DataAPI.getAPItvShowData(object : DataAPI.DataCallBack {
            override fun onAllDataReceived(responseClass: ResponseClass) {
                for (result in responseClass.results)
                {
                    val tvData = MoviesTvData(result.original_title,
                        result.poster_path,
                        result.overview,
                        result.vote_average,
                        result.vote_count,
                        result.original_language,
                        result.popularity)
                    showData.add(tvData)
                }
                EspressoIdlingResource.decrement()
            }
        })
    }

    private fun loadMovieDataFromAPI()
    {
        EspressoIdlingResource.increment()
        DataAPI.getAPIMovieData(object : DataAPI.DataCallBack {
            override fun onAllDataReceived(responseClass: ResponseClass) {
                for (result in responseClass.results)
                {
                    val movie = MoviesTvData(result.original_title,
                        result.poster_path,
                        result.overview,
                        result.vote_average,
                        result.vote_count,
                        result.original_language,
                        result.popularity)
                    moviesData.add(movie)
                }
                EspressoIdlingResource.decrement()
            }
        })
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.recycler_view_movie)).check(matches(isDisplayed()))
        loadMovieDataFromAPI()
        onView(withId(R.id.recycler_view_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                moviesData.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        loadMovieDataFromAPI()
        onView(withId(R.id.recycler_view_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title_text)).check(matches(withText(moviesData[0].original_title)))
        onView(withId(R.id.text_rating)).check(
            matches(
                withText(
                    resources.getString(
                        R.string.rating,
                        moviesData[0].vote_average,
                        moviesData[0].vote_count
                    )
                )
            )
        )
        onView(withId(R.id.text_popularity)).check(
            matches(
                withText(
                    resources.getString(
                        R.string.popularity,
                        moviesData[0].popularity
                    )
                )
            )
        )
        onView(withId(R.id.original_language)).check(
            matches(
                withText(
                    resources.getString(
                        R.string.original_language,
                        moviesData[0].original_language
                    )
                )
            )
        )
        onView(withId(R.id.text_overview)).check(matches(withText(moviesData[0].overview)))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.recycler_view_tv)).check(matches(isDisplayed()))
        loadTvDataFromAPI()
        onView(withId(R.id.recycler_view_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                showData.size
            )
        )
    }

    @Test
    fun loadDetailShow() {
        loadTvDataFromAPI()
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.recycler_view_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title_text)).check(matches(withText(showData[0].original_title)))
        onView(withId(R.id.text_rating)).check(
            matches(
                withText(
                    resources.getString(
                        R.string.rating,
                        showData[0].vote_average,
                        showData[0].vote_count
                    )
                )
            )
        )
        onView(withId(R.id.text_popularity)).check(
            matches(
                withText(
                    resources.getString(
                        R.string.popularity,
                        showData[0].popularity
                    )
                )
            )
        )
        onView(withId(R.id.original_language)).check(
            matches(
                withText(
                    resources.getString(
                        R.string.original_language,
                        showData[0].original_language
                    )
                )
            )
        )
        onView(withId(R.id.text_overview)).check(matches(withText(showData[0].overview)))
    }

}
