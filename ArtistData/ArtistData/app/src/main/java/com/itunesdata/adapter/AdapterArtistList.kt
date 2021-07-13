package com.itunesdata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itunesdata.R
import com.itunesdata.model.ResArtist
import kotlinx.android.synthetic.main.item_artist_list.view.*
import java.text.SimpleDateFormat

class AdapterArtistList(private val resArtist: ResArtist) :
    RecyclerView.Adapter<AdapterArtistList.ArtistHolder>() {

    class ArtistHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_artist_list, parent, false)
        return ArtistHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.itemView.tvTrackName.text = resArtist.results[position].trackName
        holder.itemView.tvArtistName.text = resArtist.results[position].artistName
        holder.itemView.tvGenreName.text = resArtist.results[position].primaryGenreName
        holder.itemView.tvPrice.text = "$${resArtist.results[position].trackPrice}"
        holder.itemView.tvReleaseDate.text =
            changeDateFormat(resArtist.results[position].releaseDate)
    }

    override fun getItemCount(): Int {
        return resArtist.results.size
    }

    fun changeDateFormat(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(parser.parse(date))
    }
}