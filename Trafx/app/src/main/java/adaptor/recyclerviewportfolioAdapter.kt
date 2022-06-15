package adaptor

import Interface.cardlistener
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a706012110039.signup.R
import model.saham
import com.a706012110039.signup.databinding.CardviewSahammarketBinding
import com.a706012110039.signup.databinding.CardviewSahamportfolioBinding
import model.sahamportfolio
import kotlin.math.roundToInt

class recyclerviewportfolioAdapter(val listSaham: ArrayList<sahamportfolio>, val cardlistener: cardlistener):
    RecyclerView.Adapter<recyclerviewportfolioAdapter.viewHolder>() {

    //memanage cardview saham market dan recyclerviewnya
    class viewHolder (itemView: View, val cardlistener: cardlistener): RecyclerView.ViewHolder(itemView){
        val binding = CardviewSahamportfolioBinding.bind(itemView)

        fun setDatasahammarket(data: sahamportfolio){
            binding.curvalue.text = (data.close?.times(data.qty)).toString()
            //          binding.change.text = (data.open!!.toInt() - data.close!!.toInt()).toString()

            //mengubah warna (dikomen karena value nya belum ada)
//            if(binding.change.text.toString().toInt() < 0){
//                binding.change.setTextColor(Color.parseColor("#FF1fbf44"))
//            }else{
//                binding.change.setTextColor(Color.parseColor("#FFbf1f1f"))
//            }
            binding.no.text = (adapterPosition + 1).toString()
            binding.code.text = data.symbol
            binding.qty.text = data.qty.toString()
            var sda = data.closeAsli?.minus(data.openasli!!)
            var temppp = data.closeAsli?.let { sda?.div(it) }
            var multiply = (temppp?.times(100)?.roundToInt() ?:0) / 100.0
            var temppstring = "$multiply%"
            binding.curchange.text = temppstring
            if(binding.curchange.text.contains("-")){
                binding.curchange.setTextColor(Color.parseColor("#FFbf1f1f"))

            }else{
                binding.curchange.setTextColor(Color.parseColor("#FF1fbf44"))

            }
            binding.price.text = data.close.toString()

            itemView.setOnClickListener {
                cardlistener.onCardClick(adapterPosition)
            }
        }
    }

    //membuat cardviewnya
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): recyclerviewportfolioAdapter.viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        // declare cardview
        val view = layoutInflater.inflate(R.layout.cardview_sahamportfolio, parent, false)
        return viewHolder(view, cardlistener)
    }



    //mengset data di cardviewnya
    override fun onBindViewHolder(holder: recyclerviewportfolioAdapter.viewHolder, position: Int) {
        holder.setDatasahammarket(listSaham[position])
    }

    //jumlah dari recyclerview
    override fun getItemCount(): Int {
        return listSaham.size
    }
}