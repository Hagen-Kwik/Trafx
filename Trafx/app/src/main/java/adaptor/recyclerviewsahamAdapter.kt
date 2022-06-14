package adaptor

import Interface.cardlistener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a706012110039.signup.R
import model.saham
import com.a706012110039.signup.databinding.CardviewSahammarketBinding
import kotlin.math.log
import kotlin.math.roundToInt

class recyclerviewsahamAdapter(val listSaham: ArrayList<saham>, val cardlistener: cardlistener):
    RecyclerView.Adapter<recyclerviewsahamAdapter.viewHolder>() {

    //memanage cardview saham market dan recyclerviewnya
    class viewHolder (itemView: View, val cardlistener: cardlistener): RecyclerView.ViewHolder(itemView){
        val binding = CardviewSahammarketBinding.bind(itemView)

        fun setDatasahammarket(data: saham){
            binding.Value.text = data.open.toString()

            binding.timeupdated.text = data.lastupdate
            binding.judul.text = data.symbol
            binding.namaperusahaan.text = data.companyname
            var opena = data.openasli
            var closea = data.closeAsli
            var temppppp = closea?.minus(opena!!)
            var temppp = data.closeAsli?.let { temppppp?.div(it) }
            var multiply = (temppp?.times(100)?.roundToInt() ?:0) / 100.0
            var temppstring = "$multiply%"

            binding.change.text = temppstring
//mengubah warna (dikomen karena value nya belum ada)
//            if(binding.change.text.toString().toInt() < 0){
//                binding.change.setTextColor(Color.parseColor("#FF1fbf44"))
//            }else{
//                binding.change.setTextColor(Color.parseColor("#FFbf1f1f"))
//            }


            itemView.setOnClickListener {
                cardlistener.onCardClick(adapterPosition)
            }
        }
    }

    //membuat cardviewnya
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        // declare cardview
        val view = layoutInflater.inflate(R.layout.cardview_sahammarket, parent, false)
        return viewHolder(view, cardlistener)
    }

    //mengset data di cardviewnya
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setDatasahammarket(listSaham[position])
    }

    //jumlah dari recyclerview
    override fun getItemCount(): Int {
        return listSaham.size
    }
}