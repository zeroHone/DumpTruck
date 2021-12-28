package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.dumptruck.MainActivity
import com.example.dumptruck.R
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
             val user =view.findViewById<TextInputEditText>(R.id.txtUser).text.toString()
            val password = view.findViewById<TextInputEditText>(R.id.txtPass).text.toString()
            if(loginAttempt(user, password)){
                (activity as MainActivity).NavigateTo(SettingsFragment(),false)
            }
        }
    return view
    }

    fun loginAttempt(user : String , pass: String):Boolean{
        return pass.length == 8
    }

}