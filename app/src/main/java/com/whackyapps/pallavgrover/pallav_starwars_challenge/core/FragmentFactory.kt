package com.whackyapps.pallavgrover.pallav_starwars_challenge.core

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PersonModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.Constants
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.HomeFragment
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.PersonDetailsFragment

object FragmentFactory{

    fun getHomeFragment(supportFragmentManager: FragmentManager): HomeFragment {
        var fragment = supportFragmentManager.findFragmentByTag(HomeFragment.FRAGMENT_NAME)
        if (fragment == null) {
            fragment = HomeFragment()
        }
        return fragment as HomeFragment
    }

    fun showDetailsFragment(supportFragmentManager: FragmentManager,data:PersonModel): PersonDetailsFragment {
        val b = Bundle();
        b.putParcelable(Constants.PERSON_EXTRA_KEY,data)
        var fragment = supportFragmentManager.findFragmentByTag(PersonDetailsFragment.FRAGMENT_NAME)
        if (fragment == null) {
            fragment = PersonDetailsFragment()
        }
        fragment.arguments = b
        return fragment as PersonDetailsFragment
    }

}