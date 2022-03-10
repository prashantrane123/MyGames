package com.example.mygames.games.gamelist

import com.example.mygames.games.data.network.GamesApi
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Prashant Rane on 06-03-2022.
 */

class GameListRepositoryTest {


    lateinit var service: GamesApi
    private lateinit var repository: GameListRepository

    private var gameApiMock: GamesApi = mock()
    private val mockWebServer = MockWebServer()
    lateinit var retrofit: Retrofit

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun beforeTests() {
        Dispatchers.setMain(mainThreadSurrogate)
        repository = GameListRepository(gameApiMock)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        mockWebServer.shutdown()
    }

    @Test
    fun `test_game_api_response`() = runBlocking {

        mockResponseWithDummyServer()

        val size = service.getGamesList().body()?.size

        delay(5000)
        Assert.assertTrue(size == 4)
    }

    private fun mockResponseWithDummyServer() {
        mockWebServer.enqueue(MockResponse().setBody(getResponseBody()))

        mockWebServer.start(8080)

        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/v1/test/"))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        service = retrofit.create(GamesApi::class.java)
    }

    @Test
    fun `test_repository_returns_list`() = runBlocking {

        mockResponseWithDummyServer()

        whenever(gameApiMock.getGamesList()).thenReturn(service.getGamesList())

        val gameListToTest = repository.getGamesList()

        delay(5000)
        Assert.assertTrue(gameListToTest?.size == 4)
    }


    private fun getResponseBody(): String {
        return "[\n" +
                "  {\n" +
                "    \"gameID\": \"612\",\n" +
                "    \"steamAppID\": \"21000\",\n" +
                "    \"cheapest\": \"4.39\",\n" +
                "    \"cheapestDealID\": \"0f%2B4gT2VVUn4UcmFzPxXnuqoXKAOYoJ5mpFZRWNyohc%3D\",\n" +
                "    \"external\": \"LEGO Batman\",\n" +
                "    \"internalName\": \"LEGOBATMAN\",\n" +
                "    \"thumb\": \"https://originassets.akamaized.net/origin-com-store-final-assets-prod/195763/142.0x200.0/1040463_MB_142x200_en_US_^_2017-09-08-15-21-36_d7034d41216b6dc201fb20e0cee37c1e66190a11.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"gameID\": \"167613\",\n" +
                "    \"steamAppID\": null,\n" +
                "    \"cheapest\": \"4.39\",\n" +
                "    \"cheapestDealID\": \"2XSMlnYtPjLoKI9g2vhZch9deHZ%2BE%2BpL7IoBprkWtgM%3D\",\n" +
                "    \"external\": \"LEGO Batman 2\",\n" +
                "    \"internalName\": \"LEGOBATMAN2\",\n" +
                "    \"thumb\": \"https://cdn.fanatical.com/production/product/400x225/4cf0701e-77bf-4539-bda7-129ab3e81f8b.jpeg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"gameID\": \"213979\",\n" +
                "    \"steamAppID\": null,\n" +
                "    \"cheapest\": \"59.98\",\n" +
                "    \"cheapestDealID\": \"g8bndKSh36TtB0Ac6s%2FOyR7MgjwADkJ5Jpru%2FiLDvoo%3D\",\n" +
                "    \"external\": \"Batman Bundle\",\n" +
                "    \"internalName\": \"BATMANBUNDLE\",\n" +
                "    \"thumb\": \"https://images.2game.com/boxshotcu/500062_full.jpg\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"gameID\": \"167910\",\n" +
                "    \"steamAppID\": \"502820\",\n" +
                "    \"cheapest\": \"4.39\",\n" +
                "    \"cheapestDealID\": \"VcpkOmgsL8LmPiKN7%2BzVQm1g4xi%2B0dXAk3fMyi%2B6Xnw%3D\",\n" +
                "    \"external\": \"Batman: Arkham VR\",\n" +
                "    \"internalName\": \"BATMANARKHAMVR\",\n" +
                "    \"thumb\": \"https://cdn.cloudflare.steamstatic.com/steam/apps/502820/capsule_sm_120.jpg?t=1617134787\"\n" +
                "  }\n" +
                "]"
    }
}

