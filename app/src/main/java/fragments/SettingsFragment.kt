package fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dumptruck.MainActivity
import com.example.dumptruck.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import data.SettingsData
import database.DataBaseHandler
import java.lang.StringBuilder


class SettingsFragment : Fragment() {

lateinit var appdb : DataBaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)



        view.findViewById<EditText>(R.id.ipValue).hint=SettingsData.IP
        view.findViewById<EditText>(R.id.portValue).hint=SettingsData.PORT
        view.findViewById<EditText>(R.id.userValue).hint=SettingsData.USERNAME
        view.findViewById<EditText>(R.id.passValue).hint=SettingsData.PASSWORD

        view.findViewById<FloatingActionButton>(R.id.btnSave).setOnClickListener {
            val ipaddress = view.findViewById<EditText>(R.id.ipValue).text?.toString()
            val portaddress = view.findViewById<EditText>(R.id.portValue).text?.toString()
            val useraddress = view.findViewById<EditText>(R.id.userValue).text?.toString()
            val passaddress = view.findViewById<EditText>(R.id.passValue).text?.toString()


           (activity as MainActivity).saveToDB(ipaddress,portaddress, useraddress, passaddress)

            true
        }
        return view
    }


}