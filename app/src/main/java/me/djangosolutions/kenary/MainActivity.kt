package me.djangosolutions.kenary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import me.djangosolutions.kenary.Fragments.PFragCal
import me.djangosolutions.kenary.Fragments.PFragHome.PFragHome
import me.djangosolutions.kenary.Fragments.PFragProfile

class MainActivity : AppCompatActivity() {

    var CLE = "FIRST"
    var firstTime = true
    var searchBar: SearchView? = null
    var navigationBottom: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) firstTime = savedInstanceState.getBoolean(CLE)
        if (firstTime) setHome()

        navigationBottom = findViewById(R.id.navigation)
        navigationBottom!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home ->{
                if (!(item.isChecked)) replaceFragment(PFragHome.newInstance("contents"))
                return@OnNavigationItemSelectedListener true}
            R.id.navigation_calendar ->{
                if (!(item.isChecked)) replaceFragment(PFragCal())
                return@OnNavigationItemSelectedListener true}
            R.id.navigation_profile ->{
                if (!(item.isChecked)) replaceFragment(PFragProfile())
                return@OnNavigationItemSelectedListener true}
        }
        false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(CLE, firstTime)
        super.onSaveInstanceState(outState)
    }

    private fun setHome(){
        firstTime = false
        replaceFragment(PFragHome.newInstance("contents"))
    }

    private fun replaceFragment(fragment: Fragment?){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_main, fragment)
        fragmentTransaction.commit()
    }
}

