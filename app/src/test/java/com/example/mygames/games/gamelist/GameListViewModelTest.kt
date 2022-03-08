package com.example.mygames.games.gamelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mygames.games.data.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
@ExperimentalCoroutinesApi
class GameListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: GameListViewModel

    val gameListRepoMock: GameListRepository = mock()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
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

    @ExperimentalCoroutinesApi
    @Test
    fun testRepositoryReturnsResult() = runTest(TestCoroutineDispatcher()) {
        viewModel = GameListViewModel(gameListRepoMock)
        val mockObserver: Observer<List<Game>> = mock()
        viewModel.gameListLiveData.observeForever(mockObserver)

        whenever(gameListRepoMock.getGamesList()).thenReturn(getMockedGameList())

        viewModel.getGameList()


        Assert.assertTrue(viewModel.gameListLiveData.value?.size == 20)
    }
}

/* @Test
 fun testing()= runBlocking {
    *//* var gameListMock: List<Game> = getMockedGameList()
        assert(gameListMock.size == 20)*//*
         viewModel.getGameList()
        verify(viewModel.gameListLiveData.value?.size == 20)
    }*/
