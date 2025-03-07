package com.example.lorempicsum.data.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.readRawBytes
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ImageApiServiceImplTest {

    private lateinit var successImageApiService: ImageApiServiceImpl
    private lateinit var successMockEngine: MockEngine
    private lateinit var failImageApiService: ImageApiServiceImpl
    private lateinit var failMockEngine: MockEngine
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        /**
         * NOTE: The tests in this class fail due to an error with the ktor mock engine.
         * It's likely a syntax issue with how I am setting the MockEngine up here but my setup
         * looks just like the example on their docs:
         *
         * https://ktor.io/docs/client-testing.html#test-client
         *
         * I don't have time to fix it before submitting this project but will look into it afterwards.
         */
        successMockEngine = MockEngine {
            respond(
                content = ByteReadChannel("[]"),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val successClient =
            HttpClient(successMockEngine) {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }
        successImageApiService = ImageApiServiceImpl(successClient)

        failMockEngine = MockEngine {
            respond(
                content = "",
                status = HttpStatusCode.BadRequest,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val failClient =
            HttpClient(successMockEngine) {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }
        failImageApiService = ImageApiServiceImpl(failClient)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getImageList_correctRequestURL_correctParameters() = runTest(testDispatcher) {
        successImageApiService.getImageList(1, 10)

        val request = successMockEngine.requestHistory.first()
        assertEquals("https://picsum.photos/v2/list?page=1&limit=10", request.url.toString())
    }

    @Test
    fun getImageList_successfulApiCall() = runTest(testDispatcher) {
        val response = successImageApiService.getImageList(1, 10)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("[]", response.readRawBytes().decodeToString())
        assertEquals("application/json", response.headers[HttpHeaders.ContentType])
    }

    @Test
    fun getImageList_failedApiCall() = runTest(testDispatcher) {
        val response = failImageApiService.getImageList(1, 10)

        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals("", response.readRawBytes().decodeToString())
    }
}