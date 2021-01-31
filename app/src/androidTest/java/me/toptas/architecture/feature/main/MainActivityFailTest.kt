package me.toptas.architecture.feature.main

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import me.toptas.architecture.common.model.AError
import me.toptas.architecture.common.model.ApiResponse
import me.toptas.architecture.data.repository.MainRepository
import me.toptas.architecture.features.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext

@RunWith(AndroidJUnit4::class)
class MainActivityFailTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java, false, false)

    private val repo: MainRepository = mock()

    @Before
    fun setup() {
        runBlocking {
            whenever(repo.getAlbums()).thenReturn(ApiResponse(error = AError.Network))

        }
        StandAloneContext.loadKoinModules(module {
            single(override = true) { repo }
        })
        val intent = Intent()
        rule.launchActivity(intent)
    }

    @Test
    fun testFetchAlbumsFailed() {
        onView(withId(android.R.id.message)).check(matches(withText("Network error")))
    }


}