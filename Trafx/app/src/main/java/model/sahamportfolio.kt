package model

import android.os.Parcel

class sahamportfolio(
    var qty: Int = 0,
    var buyat: Int = 0, parcel: Parcel? = null
) : saham(parcel) {

    fun addParent(Saham: saham){
        this.open = Saham.open
    }

}