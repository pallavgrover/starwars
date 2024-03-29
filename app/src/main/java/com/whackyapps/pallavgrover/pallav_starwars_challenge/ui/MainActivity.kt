package com.whackyapps.pallavgrover.pallav_starwars_challenge.ui


import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.whackyapps.pallavgrover.pallav_starwars_challenge.R
import com.whackyapps.pallavgrover.pallav_starwars_challenge.core.FragmentFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : DaggerAppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar by lazy { toolbar_main_activity }
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        showHomeFragment()
    }

    private fun showHomeFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                FragmentFactory.getHomeFragment(supportFragmentManager),
                HomeFragment.FRAGMENT_NAME
            )
        fragmentTransaction.commit()
    }
}
