package io.skillstest.cdeveloper.data.model

import android.os.Parcel
import android.os.Parcelable

class ApiTeamOfLeague(
        val idTeam: Int,
        var strTeam: String,
        var strStadium: String?,
        var strTeamBadge: String?,
        var strTeamJersey: String?,
        var intFormedYear: Int?,
        var strDescriptionEN: String?,
        var strDescriptionES: String?,
        var strWebsite: String?,
        var strTwitter: String?,
        var strFacebook: String?,
        var strInstagram: String?,
        var strYoutube: String?) : Parcelable {

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString() ?: "",
            source.readString() ?: "",
            source.readString() ?: "",
            source.readString() ?: "",
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString() ?: "",
            source.readString() ?: "",
            source.readString() ?: "",
            source.readString() ?: "",
            source.readString() ?: "",
            source.readString() ?: "",
            source.readString() ?: ""
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(idTeam)
        writeString(strTeam)
        writeString(strStadium)
        writeString(strTeamBadge)
        writeString(strTeamJersey)
        writeValue(intFormedYear)
        writeString(strDescriptionEN)
        writeString(strDescriptionES)
        writeString(strWebsite)
        writeString(strTwitter)
        writeString(strFacebook)
        writeString(strInstagram)
        writeString(strYoutube)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ApiTeamOfLeague> = object : Parcelable.Creator<ApiTeamOfLeague> {
            override fun createFromParcel(source: Parcel): ApiTeamOfLeague = ApiTeamOfLeague(source)
            override fun newArray(size: Int): Array<ApiTeamOfLeague?> = arrayOfNulls(size)
        }
    }
}