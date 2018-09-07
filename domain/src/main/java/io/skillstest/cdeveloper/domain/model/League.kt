package io.skillstest.cdeveloper.domain.model

import android.os.Parcel
import android.os.Parcelable

class League(
        val idLeague: Int,
        var strLeague: String,
        var strLeagueAlternate: String,
        var strSport: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idLeague)
        parcel.writeString(strLeague)
        parcel.writeString(strLeagueAlternate)
        parcel.writeString(strSport)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<League> {
        override fun createFromParcel(parcel: Parcel): League {
            return League(parcel)
        }

        override fun newArray(size: Int): Array<League?> {
            return arrayOfNulls(size)
        }
    }
}