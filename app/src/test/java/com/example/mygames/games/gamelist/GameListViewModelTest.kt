package com.example.mygames.games.gamelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mygames.games.data.model.Game
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


/**
 * Created by Prashant Rane on 06-03-2022.
 */
class GameListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: GameListViewModel

    private val gameListRepoMock: GameListRepository = mock()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = GameListViewModel(gameListRepoMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    private fun getMockedGameList(): List<Game> {
        var list = ArrayList<Game>()
        repeat(20) {
            list.add(
                Game(
                    "12.6",
                    "54",
                    "Batman Leage",
                    "777",
                    "Batman Log4j",
                    "100",
                    "https://www.google.com/random"
                )
            )
        }
        return list
    }

    @Test
    fun testRepositoryResult() = runBlocking {

            val mockObserver: Observer<List<Game>> = mock()
            viewModel.gameListLiveData.observeForever(mockObserver)

            whenever(gameListRepoMock.getGamesList()).thenReturn(getMockedGameList())

            viewModel.getGameList()
            delay(5000)

            Assert.assertTrue(viewModel.gameListLiveData.value?.size == 20)

    }
}

