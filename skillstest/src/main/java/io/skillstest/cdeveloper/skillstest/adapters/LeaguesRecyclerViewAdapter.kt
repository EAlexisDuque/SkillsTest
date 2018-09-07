package io.skillstest.cdeveloper.skillstest.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.skillstest.cdeveloper.domain.model.League
import io.skillstest.cdeveloper.skillstest.R

class LeaguesRecyclerViewAdapter(private val mLeagues: ArrayList<League> = ArrayList(),
                                 private val leaguesIItemClickListener: IItemClickListener<League>) :
        RecyclerView.Adapter<LeaguesRecyclerViewAdapter.LeaguesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaguesViewHolder {
        return LeaguesViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_league_template, parent, false))
    }

    override fun getItemCount(): Int = mLeagues.size

    override fun onBindViewHolder(holder: LeaguesViewHolder, position: Int) {
        holder.bind(mLeagues[position], position + 1)
        holder.itemView.setOnClickListener {
            leaguesIItemClickListener.itemCLick(mLeagues[position])
        }
    }

    fun addAllLeagues(leagues: List<League>) {
        mLeagues.addAll(leagues)
        notifyDataSetChanged()
    }

    fun clearLeagues() {
        mLeagues.clear()
        notifyDataSetChanged()
    }

    fun listOfLeagues(): ArrayList<League> = mLeagues

    class LeaguesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvLeagueName = itemView.findViewById<TextView>(R.id.tvLeagueName)
        private val tvLeagueSport = itemView.findViewById<TextView>(R.id.tvLeagueSport)
        private val tvLeagueIndex = itemView.findViewById<TextView>(R.id.tvLeagueIndex)

        fun bind(league: League, posItem: Int) {
            tvLeagueName?.text = league.strLeague
            tvLeagueSport?.text = league.strSport
            tvLeagueIndex?.text = posItem.toString()
        }
    }
}

