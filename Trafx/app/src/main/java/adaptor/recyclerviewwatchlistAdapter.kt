package adaptor

import Interface.cardlistener
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a706012110039.signup.R
import com.a706012110039.signup.databinding.CardviewSahammarketBinding
import model.sahamwatchlist

class recyclerviewwatchlistAdapter(val listSaham: ArrayList<sahamwatchlist>, val cardlistener: cardlistener):
    RecyclerView.Adapter<recyclerviewwatchlistAdapter.viewHolder>() {

    //memanage cardview saham market dan recyclerviewnya
    class viewHolder (itemView: View, val cardlistener: cardlistener): RecyclerView.ViewHolder(itemView){
        val binding = CardviewSahammarketBinding.bind(itemView)

        fun setDatasahammarket(data: sahamwatchlist){
            binding.Value.text = data.open.toString()
  //          binding.change.text = (data.open!!.toInt() - data.close!!.toInt()).toString()
            //mengubah warna (dikomen karena value nya belum ada)
//            if(binding.change.text.toString().toInt() < 0){
//                binding.change.setTextColor(Color.parseColor("#FF1fbf44"))
//            }else{
//                binding.change.setTextColor(Color.parseColor("#FFbf1f1f"))
//            }
            binding.timeupdated.text = data.lastupdate
            binding.judul.text = data.symbol
            binding.namaperusahaan.text = data.companyname

            itemView.setOnClickListener {
                cardlistener.onCardClick(adapterPosition)
            }
        }
    }

    //membuat cardviewnya
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): recyclerviewwatchlistAdapter.viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        // declare cardview
        val view = layoutInflater.inflate(R.layout.cardview_sahammarket, parent, false)
        return viewHolder(view, cardlistener)
    }

    //jumlah dari recyclerview
    override fun getItemCount(): Int {
        return listSaham.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setDatasahammarket(listSaham[position])
    }
}