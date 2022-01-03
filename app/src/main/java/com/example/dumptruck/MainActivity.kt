package com.example.dumptruck

import NavigateHost
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.google.android.material.navigation.NavigationView
import data.SettingsData
import database.DataBaseHandler
import fragments.LoginFragment
import fragments.MonitorFragment
import fragments.SettingsFragment

class MainActivity : AppCompatActivity(),NavigateHost {
val settingFragment = SettingsFragment()
    val monitorFragment = MonitorFragment()
    val loginFragment = LoginFragment()
    lateinit var appdb : DataBaseHandler
    lateinit var toggle : ActionBarDrawerToggle
    var myDummy : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appdb = DataBaseHandler(this)
        appdb.checkCompleteSettings()

        toggle = ActionBarDrawerToggle(this,findViewById(R.id.baseDrawer),R.string.open,R.string.close)
        findViewById<DrawerLayout>(R.id.baseDrawer).addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)




        findViewById<NavigationView>(R.id.navDrawer).setNavigationItemSelectedListener{

            when(it.itemId){
                R.id.menuItemSetting ->{

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.mainFrame, settingFragment)
                        commit()
                        findViewById<DrawerLayout>(R.id.baseDrawer).closeDrawer(GravityCompat.START)
                    }
                }//Setting
                R.id.menuItemLiveData -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.mainFrame, monitorFragment)
                        commit()
                        findViewById<DrawerLayout>(R.id.baseDrawer).closeDrawer(GravityCompat.START)
                    }
                }


            }
            true
        }





    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun NavigateTo(fragment: Fragment, addToBackstack: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFrame, settingFragment)
            commit()
        }
    }


    override fun saveToDB(ip : String? , port :String?, user : String?, pass : String?){

        val strBuffer = java.lang.StringBuilder()
        val regexPort = Regex("[0-9]{1,4}")
        val regexIP = Regex("^([0-9]{1,3}\\.){3}[0-9]{1,3}\$")
     //   val regexPort = Regex("^(0|[1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])\$")

        if(ip != null && port != null){
            if(regexIP.matches(ip as String)){
                SettingsData.IP = ip
            }else{
                strBuffer.append("IP is Invalid")
            }
            if(regexPort.matches(port as String)){
                SettingsData.PORT = port
            }else{
                strBuffer.append("& Port is Invalid = $port")
            }
        }else{
            strBuffer.append(" & IP or Port is NULL")
        }

        if(user != null && pass != null){

                SettingsData.USERNAME = user as String




                SettingsData.PASSWORD = pass as String



        }else{
            strBuffer.append(" & User or Password is NULL")
        }


        if(!strBuffer.isEmpty()){
            //Toast.makeText(mycontext, strBuffer.toString(),Toast.LENGTH_LONG).show()
            Log.i("settingHHF","${SettingsData.IP} and ${SettingsData.PORT} ${strBuffer.toString()}")

        }


        //appdb.refreshSettings()
        appdb.insertSettings()
        //appdb.checkCompleteSettings()
       // Log.i("settingHHF","IP=${SettingsData.IP} PORT=${SettingsData.PORT}")



    }

    override fun onStart() {
        super.onStart()


    }
}