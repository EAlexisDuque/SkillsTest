package io.skillstest.cdeveloper.skillstest.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.skillstest.cdeveloper.domain.model.TeamOfLeague
import io.skillstest.cdeveloper.skillstest.R
import io.skillstest.cdeveloper.skillstest.util.loadImageFromUrl

class TeamsOfLeaguesRecyclerViewAdapter(private val mTeams: ArrayList<TeamOfLeague> = ArrayList(),
                                        private val teamOfLeaguesIItemClickListener: IItemClickListener<TeamOfLeague>) :
        RecyclerView.Adapter<TeamsOfLeaguesRecyclerViewAdapter.TeamsOfLeagueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsOfLeagueViewHolder {
        return TeamsOfLeagueViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_team_of_league_template, parent, false))
    }

    override fun getItemCount(): Int = mTeams.size

    override fun onBindViewHolder(holder: TeamsOfLeagueViewHolder, position: Int) {
        holder.bind(mTeams[position])
        holder.itemView.setOnClickListener {
            teamOfLeaguesIItemClickListener.itemCLick(mTeams[position])
        }
    }

    fun addAllTeamsOfLeagues(teams: List<TeamOfLeague>) {
        mTeams.addAll(teams)
        notifyDataSetChanged()
    }

    fun clearTeamsOfLeagues() {
        mTeams.clear()
        notifyDataSetChanged()
    }

    fun listOfTeams(): ArrayList<TeamOfLeague> = mTeams

    class TeamsOfLeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamName = itemView.findViewById<TextView>(R.id.teamName)
        private val teamStadium = itemView.findViewById<TextView>(R.id.teamStadium)
        private val imageViewTeamBadge = itemView.findViewById<ImageView>(R.id.imageViewTeamBadge)

        fun bind(team: TeamOfLeague) {
            teamName?.text = team.name
            teamStadium?.text = team.stadium
            team.teamBadge?.let {
                imageViewTeamBadge.loadImageFromUrl(it)
            }

        }
    }
}

