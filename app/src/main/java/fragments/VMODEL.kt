package fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ekndev.gaugelibrary.HalfGauge
import com.example.dumptruck.R
import data.DataBase

class VMODEL(myarray : ArrayList<DataBase>) : RecyclerView.Adapter<VMODEL.VHOLER>() {
    val dataArray = myarray




    inner class VHOLER(itemview : View) : RecyclerView.ViewHolder(itemview){
        fun bind(dataBase : DataBase){
            itemView.findViewById<HalfGauge>(R.id.halfGauge).value = dataBase.value.toDouble()
            itemView.findViewById<TextView>(R.id.txtDescription).text = dataBase.description.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHOLER {
return VHOLER(LayoutInflater.from(parent.context).inflate(R.layout.itemview,parent,false))

    }

    override fun onBindViewHolder(holder: VHOLER, position: Int) {
       holder.bind(dataArray[position])
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }
}