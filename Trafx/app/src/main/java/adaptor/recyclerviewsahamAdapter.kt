package adaptor

import Interface.cardlistener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a706012110039.signup.R
import model.saham
import com.a706012110039.signup.databinding.CardviewSahammarketBinding

class recyclerviewsahamAdapter(val listSaham: ArrayList<saham>, val cardlistener: cardlistener):
    RecyclerView.Adapter<recyclerviewsahamAdapter.viewHolder>() {

    //memanage cardview saham market dan recyclerviewnya
    class viewHolder (itemView: View, val cardlistener: cardlistener): RecyclerView.ViewHolder(itemView){
        val binding = CardviewSahammarketBinding.bind(itemView)

        fun setDatasahammarket(data: saham){
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
    ): recyclerviewsahamAdapter.viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        // declare cardview
        val view = layoutInflater.inflate(R.layout.cardview_sahammarket, parent, false)
        return viewHolder(view, cardlistener)
    }

    //mengset data di cardviewnya
    override fun onBindViewHolder(holder: recyclerviewsahamAdapter.viewHolder, position: Int) {
        holder.setDatasahammarket(listSaham[position])
    }

    //jumlah dari recyclerview
    override fun getItemCount(): Int {
        return listSaham.size
    }
}