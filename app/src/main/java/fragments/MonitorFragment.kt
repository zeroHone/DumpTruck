package fragments

import android.os.Bundle
import android.os.RecoverySystem
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dumptruck.R
import data.DataBase
import data.ModelViewContent


class MonitorFragment : Fragment() {

    lateinit var monitor : ModelViewContent


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_monitor, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        monitor = ViewModelProvider(this).get(ModelViewContent::class.java)
        val layoumanager = LinearLayoutManager(activity)
        monitor.runCommand = true
        monitor.refreshData()
        val tempArray = arrayListOf<DataBase>()
        val viewAdapter = ViewAdapter(tempArray)
        view.findViewById<RecyclerView>(R.id.recyclerview).layoutManager = layoumanager

        view.findViewById<RecyclerView>(R.id.recyclerview).adapter = viewAdapter
        monitor.getLiveDataValue().observe(viewLifecycleOwner, Observer {
        viewAdapter.newDataAccept(it)


        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        monitor.runCommand = false
    }
}