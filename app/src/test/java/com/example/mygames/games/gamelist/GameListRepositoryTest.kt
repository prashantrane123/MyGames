package com.example.mygames.games.gamelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mygames.games.data.network.GamesApi
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Prashant Rane on 06-03-2022.
 */

class GameListRepositoryTest {

    private lateinit var repository: GameListRepository

    private var gameApiMock: GamesApi = mock()

    @Before
    fun beforeTests() {
        repository = GameListRepository(gameApiMock)
    }

    @Test
    fun `getGameApi`()= runBlockingTest  {
        val server = MockWebServer()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(server.url("https://www.cheapshark.com/"))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        val service: GamesApi = retrofit.create(GamesApi::class.java)
        server.enqueue(MockResponse().setBody(getResponseBody()))
        server.start()

        Assert.assertTrue(service.getGamesList().body()?.size==4)

        repository.getGamesList()
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

