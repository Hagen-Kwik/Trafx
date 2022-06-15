package model

import android.os.Parcel

class sahamportfolio(
    var qty: Int = 0,
    var buyat: Int = 0, parcel: Parcel? = null
) : saham(parcel) {

    fun addParent(Saham: saham){
        this.open = Saham.open
        this.close = Saham.close
        this.companyname = Saham.companyname
        this.symbol = Saham.symbol
        this.high = Saham.high
        this.lastupdate = Saham.lastupdate
        this.low = Saham.low
        this.volume = Saham.volume
        this.closeAsli = Saham.closeAsli
        this.openasli = Saham.openasli
    }

}