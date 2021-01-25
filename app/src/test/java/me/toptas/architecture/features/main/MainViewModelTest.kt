package me.toptas.architecture.features.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import kotlinx.coroutines.runBlocking
import me.toptas.architecture.base.BaseTest
import me.toptas.architecture.base.observedValue
import me.toptas.architecture.common.model.AError
import me.toptas.architecture.common.model.Album
import me.toptas.architecture.common.model.ApiResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest : BaseTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repo: MainRepository = mock()
    private val vm = MainViewModel(repo)

    @Before
    fun setup() {
        setDispatcher()
    }

    @Test
    fun testSuccessfulFetch() {
        runBlocking {
            // when
            whenever(repo.getAlbums()).thenReturn(ApiResponse(success = listOf(Album("title"))))
            vm.fetchItems()

            // then
            assertEquals(vm.albumsLive.observedValue()?.firstOrNull()?.title, "title")
        }
    }

    @Test
    fun testFailedFetch() {
        runBlocking {
            whenever(repo.getAlbums()).thenReturn(ApiResponse(error = AError.Business("Business error")))

            vm.fetchItems()

            assertNull(vm.albumsLive.observedValue())
            val error = vm.baseErrorLive.observedValue() as AError.Business
            assertEquals("Business error", error.msg)
        }
    }

    @Test
    fun testFailedFetchWithEmptyErrorr() {
        runBlocking {
            whenever(repo.getAlbums()).thenReturn(ApiResponse())

            vm.fetchItems()

            assertNull(vm.albumsLive.observedValue())
            val error = vm.baseErrorLive.observedValue() as AError.Generic
            assertEquals(AError.GENERIC_ERROR_NOT_PARSED, error.code)
        }
    }

}