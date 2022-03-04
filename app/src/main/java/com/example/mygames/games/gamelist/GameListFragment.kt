package com.example.mygames.games.gamelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mygames.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameListFragment : Fragment() {

    companion object {
        fun newInstance() = GameListFragment()
    }

    private lateinit var viewModel: GameListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[GameListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_list_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.gameListLiveData.observe(viewLifecycleOwner, Observer { gameList ->
            if (gameList.isNotEmpty()) {
                gameList.forEach { listItem -> Log.d("Prashant", listItem.author) }
            }
        })
        viewModel.getGameList()
    }
}