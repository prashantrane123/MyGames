package com.example.mygames.games.gamelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mygames.R
import com.example.mygames.databinding.ItemGameBinding
import com.example.mygames.games.data.model.Game

/**
 * Created by Prashant Rane on 6-03-2022.
 */
class GameListAdapter() : RecyclerView.Adapter<GameListAdapter.GameItemViewHolder>() {

    private val gameList: MutableList<Game> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameItemViewHolder {
        return GameItemViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GameItemViewHolder, position: Int) {
        val game = gameList[position]
        holder.gameThumbImg.load(game.thumb)
        {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
        }
        holder.gameNameTxt.text = game.external
    }

    // Total number of cells
    override fun getItemCount(): Int {
        return gameList.size
    }

    fun setGameList(games: List<Game>) {
        gameList.clear() //to avoid duplicate entries in list
        gameList.addAll(games)
        notifyDataSetChanged()//can be better not to use this
    }

    // Stores and recycles views as they are scrolled off screen
    inner class GameItemViewHolder(var itemImageBinding: ItemGameBinding) :
        RecyclerView.ViewHolder(
            itemImageBinding.root
        ) {
        var gameThumbImg = itemImageBinding.gameImageView
        var gameNameTxt = itemImageBinding.gameName
    }
}