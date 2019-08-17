package com.whackyapps.pallavgrover.pallav_starwars_challenge.ui


import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.Toolbar
import com.whackyapps.pallavgrover.pallav_starwars_challenge.R
import com.whackyapps.pallavgrover.pallav_starwars_challenge.core.FragmentFactory
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PersonModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.Constants.PERSON_EXTRA_KEY
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar_main.*

class PersonDetailsActivity : DaggerAppCompatActivity() {

    private val TAG = PersonDetailsActivity::class.java.simpleName
    lateinit var data:PersonModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_details)
        val toolbar: Toolbar by lazy { toolbar_main_activity }
        setSupportActionBar(toolbar)
        toolbar_title.text = ""
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        data = intent.getParcelableExtra(PERSON_EXTRA_KEY) as PersonModel
        toolbar.title = data.name
        showDetailsFragment()
    }

    private fun showDetailsFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                FragmentFactory.showDetailsFragment(supportFragmentManager,data),
                PersonDetailsFragment.FRAGMENT_NAME
            )
        fragmentTransaction.commit()
    }
}
