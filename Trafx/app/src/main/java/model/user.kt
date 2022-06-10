package model

import android.os.Parcel
import android.os.Parcelable

class user(
    var nama:String?,
    var email:String?,
    var password:String?,
    var alamat: String?,
    var credit_card: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(alamat)
        parcel.writeString(credit_card)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<user> {
        override fun createFromParcel(parcel: Parcel): user {
            return user(parcel)
        }

        override fun newArray(size: Int): Array<user?> {
            return arrayOfNulls(size)
        }
    }

}