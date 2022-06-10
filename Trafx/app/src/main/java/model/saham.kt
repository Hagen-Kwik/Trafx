package model

import android.os.Parcel
import android.os.Parcelable

class saham(
    var open: Int?,
    var high: Int?,
    var low: Int?,
    var close: Int?,
    var volume: Int?,
    var symbol:String?,
    var companyname: String?,
    var lastupdate: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(open)
        parcel.writeValue(high)
        parcel.writeValue(low)
        parcel.writeValue(close)
        parcel.writeValue(volume)
        parcel.writeString(symbol)
        parcel.writeString(companyname)
        parcel.writeString(lastupdate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<saham> {
        override fun createFromParcel(parcel: Parcel): saham {
            return saham(parcel)
        }

        override fun newArray(size: Int): Array<saham?> {
            return arrayOfNulls(size)
        }
    }


}