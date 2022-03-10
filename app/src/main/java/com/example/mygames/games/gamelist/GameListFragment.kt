package com.example.mygames.games.gamelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mygames.databinding.FragmentGameListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameListFragment : Fragment() {

    private val TAG = GameListFragment::class.qualifiedName
    private lateinit var gamesRecyclerView: RecyclerView
    private lateinit var adapter: GameListAdapter
    lateinit var viewBinding: FragmentGameListBinding

    private lateinit var viewModel: GameListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentGameListBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[GameListViewModel::class.java]
        initViews()
        initObservers()
        getGameList()
    }

    private fun getGameList() {
        viewModel.getGameList()
    }

    private fun initViews() {
        adapter = GameListAdapter()
        gamesRecyclerView = viewBinding.listGames
        gamesRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        gamesRecyclerView.adapter = adapter
    }

    private fun initObservers() {
        viewModel.gameListLiveData.observe(viewLifecycleOwner) { gameList ->
            if (gameList.isNotEmpty()) {
                gameList.forEach { listItem -> Log.d(TAG, listItem.external) }
                adapter.setGameList(gameList)
            }
        }
    }

}