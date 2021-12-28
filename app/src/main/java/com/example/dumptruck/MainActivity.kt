package com.example.dumptruck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


    lateinit var toggle : ActionBarDrawerToggle
    var myDummy : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this,findViewById(R.id.baseDrawer),R.string.open,R.string.close)
        findViewById<DrawerLayout>(R.id.baseDrawer).addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<NavigationView>(R.id.navDrawer).setNavigationItemSelectedListener{

            when(it.itemId){
                R.id.menuItemLiveData ->myDummy=1
                R.id.menuItemLiveData -> myDummy=2


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
}