package me.djangosolutions.kenary

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.util.Log
import android.widget.Toast
import me.djangosolutions.kenary.Fragments.PFragCal
import me.djangosolutions.kenary.Fragments.PFragHome.PFragHome
import me.djangosolutions.kenary.Fragments.PFragHome.PFragHomeContent
import me.djangosolutions.kenary.Fragments.PFragHome.PFragHomeContentTab
import me.djangosolutions.kenary.Fragments.PFragProfile

class MainActivity : AppCompatActivity() , PFragHomeContentTab.OnPFragHomeContentTabInteractionListener{

    val PFragHome = me.djangosolutions.kenary.Fragments.PFragHome.PFragHome.newInstance("contents")
    var CLE = "FIRST"
    var firstTime = true
    var searchBar: SearchView? = null
    var navigationBottom: BottomNavigationView? = null

    companion object{
        var token: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) firstTime = savedInstanceState.getBoolean(CLE)
        if (firstTime) setHome()
        val sharedPref = this.getSharedPreferences("log", Context.MODE_PRIVATE)?: return
        Log.d("PUTUPTODATE", sharedPref.contains(getString(R.string.saved_token)).toString())
        navigationBottom = findViewById(R.id.navigation)
        navigationBottom!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home ->{
                if (!(item.isChecked)) replaceFragment(PFragHome)
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
        replaceFragment(PFragHome)
    }

    private fun replaceFragment(fragment: Fragment?){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        fragmentTransaction.replace(R.id.frame_main, fragment)
        fragmentTransaction.commit()
    }

    override fun onFragmentInteraction(type: String) {
        PFragHome.replaceFragment(PFragHomeContentTab.newInstance("classroomitem"))
    }
}

