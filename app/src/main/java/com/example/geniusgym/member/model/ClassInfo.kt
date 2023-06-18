package com.example.geniusgym.member.model

import android.os.Parcel
import android.os.Parcelable

data class ClassInfo(
    val ci_id: Int = -1,
    var ci_name: String? = "阿姆斯特壤螺旋有氧舞蹈",
    var ci_start_time: String? = "09:00",
    var ci_ed_time: String? = "18:00",
    val ci_place: String? = "緯育分店",
    var ci_cost: Int? = 99999999,
    var sc_id: Int? = 1,
    var ci_date: String? = "2023/12/31",
    val ci_text: String? = "本課程希望大家能認真學習",
    val ci_limit: Int = 87,
    var c_id: String? = "我是誰我在哪",
) : java.io.Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ci_id)
        parcel.writeString(ci_name)
        parcel.writeString(ci_start_time)
        parcel.writeString(ci_ed_time)
        parcel.writeString(ci_place)
        parcel.writeValue(ci_cost)
        parcel.writeValue(sc_id)
        parcel.writeString(ci_date)
        parcel.writeString(ci_text)
        parcel.writeInt(ci_limit)
        parcel.writeString(c_id)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClassInfo> {
        override fun createFromParcel(parcel: Parcel): ClassInfo {
            return ClassInfo(parcel)
        }

        override fun newArray(size: Int): Array<ClassInfo?> {
            return arrayOfNulls(size)
        }
    }
}