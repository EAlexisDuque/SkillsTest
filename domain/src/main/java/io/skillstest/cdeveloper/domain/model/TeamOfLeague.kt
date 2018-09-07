package io.skillstest.cdeveloper.domain.model

import android.os.Parcel
import android.os.Parcelable

class TeamOfLeague(
        val idTeam: Int,
        var name: String,
        var stadium: String?,
        var teamBadge: String?,
        var teamJersey: String?,
        var formedYear: Int?,
        var descriptionEN: String?,
        var descriptionES: String?,
        var website: String?,
        var twitter: String?,
        var facebook: String?,
        var instagram: String?,
        var youtube: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idTeam)
        parcel.writeString(name)
        parcel.writeString(stadium)
        parcel.writeString(teamBadge)
        parcel.writeString(teamJersey)
        parcel.writeValue(formedYear)
        parcel.writeString(descriptionEN)
        parcel.writeString(descriptionES)
        parcel.writeString(website)
        parcel.writeString(twitter)
        parcel.writeString(facebook)
        parcel.writeString(instagram)
        parcel.writeString(youtube)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TeamOfLeague> {
        override fun createFromParcel(parcel: Parcel): TeamOfLeague {
            return TeamOfLeague(parcel)
        }

        override fun newArray(size: Int): Array<TeamOfLeague?> {
            return arrayOfNulls(size)
        }
    }
}