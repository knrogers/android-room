package com.roguekingapps.bgdb.ui.launcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roguekingapps.bgdb.R
import com.roguekingapps.bgdb.application.BGDbApplication
import com.roguekingapps.bgdb.data.network.Status.ERROR
import com.roguekingapps.bgdb.data.network.Status.LOADING
import com.roguekingapps.bgdb.data.network.Status.SUCCESS
import com.roguekingapps.bgdb.data.database.BoardGame
import com.roguekingapps.bgdb.di.DaggerMainActivityComponent
import com.roguekingapps.bgdb.ui.boardgame.BoardGamesViewModel
import com.roguekingapps.bgdb.ui.launcher.MainActivity.BoardGamesAdapter.BoardGameViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.mainButton
import kotlinx.android.synthetic.main.activity_main.mainRecyclerView
import kotlinx.android.synthetic.main.activity_main.mainTextView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: BoardGamesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainActivityComponent.factory()
            .create(this, (application as BGDbApplication).applicationComponent)
            .inject(this)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = BoardGamesAdapter(emptyList())
        viewModel.getBoardGames()

        viewModel.boardGames.observe(this, Observer {
            when(it.status) {
                LOADING -> {}
                SUCCESS  -> {
                    (mainRecyclerView.adapter as BoardGamesAdapter).boardGames = it.data as List<BoardGame>
                    (mainRecyclerView.adapter as BoardGamesAdapter).notifyDataSetChanged()
                }
                ERROR -> mainTextView.text = it.toString()

            }
        })

        mainButton.setOnClickListener { viewModel.getBoardGames() }

    }

    class BoardGamesAdapter(var boardGames: List<BoardGame>): RecyclerView.Adapter<BoardGameViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardGameViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_board_game, parent, false)
            return BoardGameViewHolder(view)
        }

        override fun onBindViewHolder(holder: BoardGameViewHolder, position: Int) {
            val boardGame = boardGames[position]
            holder.nameTextView.text = boardGame.name
            holder.yearTextView.text = boardGame.year
            if (!boardGame.thumbnailUrl.isNullOrEmpty()) {
                Picasso.get().load(boardGame.thumbnailUrl).into(holder.thumbnailImageView)
            }
        }

        override fun getItemCount(): Int = boardGames.size

        class BoardGameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
            val yearTextView: TextView = itemView.findViewById(R.id.yearTextView)
            val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)

        }
    }

}
