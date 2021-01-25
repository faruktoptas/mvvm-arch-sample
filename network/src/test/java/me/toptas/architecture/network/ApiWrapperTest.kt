package me.toptas.architecture.network

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import me.toptas.architecture.common.model.AError
import me.toptas.architecture.common.model.Album
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ApiWrapperTest {

    private val errorBody = "{ \"errorMessage\": \"Business error\" }"

    private val api: Api = mock()

    @Test
    fun testApiWrapperSuccess() {
        runBlocking {
            val albums = listOf(Album("title"))
            whenever(api.getAlbums()).thenReturn(
                Response.success(200, albums)
            )

            val response = apiWrapper { api.getAlbums() }

            Assert.assertEquals(null, response.error)
            assertEquals(albums, response.success)
        }
    }

    @Test
    fun testApiWrapperNetworkError() {
        runBlocking {

            whenever(api.getAlbums()).thenAnswer { throw UnknownHostException() }

            val response = apiWrapper { api.getAlbums() }
            Assert.assertEquals(true, response.error is AError.Network)
        }
    }

    @Test
    fun testApiWrapperTimeoutError() {
        runBlocking {

            whenever(api.getAlbums()).thenAnswer { throw TimeoutException() }

            val response = apiWrapper { api.getAlbums() }
            Assert.assertEquals(true, response.error is AError.Timeout)
        }
    }

    @Test
    fun testApiWrapperGenericError() {
        runBlocking {

            whenever(api.getAlbums()).thenAnswer { Exception("failed!!") }

            val response = apiWrapper { api.getAlbums() }
            Assert.assertEquals(true, response.error is AError.Network)
        }
    }

    @Test
    fun testApiWrapperAuthError() {
        runBlocking {

            whenever(api.getAlbums()).thenReturn(
                Response.error(401, errorBody.toResponseBody())
            )

            val response = apiWrapper { api.getAlbums() }
            val err = response.error as AError.Authorization

            Assert.assertEquals("Business error", err.msg)
        }
    }

    @Test
    fun testApiWrapperInvalidErrorrFormat() {
        runBlocking {

            whenever(api.getAlbums()).thenReturn(
                Response.error(412, "invalid_json".toResponseBody())
            )

            val response = apiWrapper { api.getAlbums() }
            val err = response.error as AError.Generic

            Assert.assertEquals(AError.GENERIC_ERROR_NOT_PARSED, err.code)
        }
    }


    @Test
    fun testInvalidMessage() {
        runBlocking {
            whenever(api.getAlbums()).thenReturn(
                Response.error(412, "not a json string".toResponseBody())
            )

            val response = apiWrapper { api.getAlbums() }
            val err = response.error as AError.Generic

            assertEquals(AError.GENERIC_ERROR_NOT_PARSED, err.code)
        }
    }

}