package fragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ekndev.gaugelibrary.ArcGauge
import com.ekndev.gaugelibrary.Range
import com.example.dumptruck.R
import data.DataUnit

class ViewAdapter(myarray : ArrayList<DataUnit>) : RecyclerView.Adapter<ViewAdapter.VHOLER>() {
    var dataArray = myarray



    inner class VHOLER(itemview : View) : RecyclerView.ViewHolder(itemview){
        fun bind(dataBase : DataUnit){
            var temp =""
            val range = Range()
            range.color = Color.parseColor("#ce0000")
            range.from = 0.0
            range.to = 250.0

            val range2 = Range()
            range2.color = Color.parseColor("#E3E500")
            range2.from = 250.0
            range2.to = 500.0

            val range3 = Range()
            range3.color = Color.parseColor("#00b20b")
            range3.from = 500.0
            range3.to = 750.0

            //add color ranges to gauge


            itemView.findViewById<ArcGauge>(R.id.halfGauge).apply {


                addRange(range)
                addRange(range2)
                addRange(range3)

                //set min max and current value
                minValue = 0.0
                maxValue = 750.0
                 temp = String.format("%.2f",dataBase.value)
                 value = temp.toDouble()

            }

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

    fun newDataAccept(arrayOfDat : ArrayList<DataUnit>){
        dataArray = arrayOfDat
        notifyDataSetChanged()
    }
}