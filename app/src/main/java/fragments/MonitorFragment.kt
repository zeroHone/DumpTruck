package fragments

import android.os.Bundle
import android.os.RecoverySystem
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dumptruck.R


class MonitorFragment : Fragment() {
val monitor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_monitor, container, false)
        view.findViewById<RecyclerView>(R.id.scrolviewer).layoutManager = LinearLayoutManager(activity)

        view.findViewById<RecyclerView>(R.id.scrolviewer).adapter = ViewAdapter()



        return view
    }


}