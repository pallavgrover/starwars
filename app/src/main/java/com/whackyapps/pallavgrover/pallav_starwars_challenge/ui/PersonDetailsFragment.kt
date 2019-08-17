package com.whackyapps.pallavgrover.pallav_starwars_challenge.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.whackyapps.pallavgrover.pallav_starwars_challenge.R
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.*
import dagger.android.support.DaggerFragment
import com.whackyapps.pallavgrover.pallav_starwars_challenge.databinding.FragmentHomeBinding
import com.whackyapps.pallavgrover.pallav_starwars_challenge.databinding.FragmentPersonDetailsBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_person_details.*
import javax.inject.Inject

class PersonDetailsFragment : DaggerFragment(){
    private val TAG: String = PersonDetailsFragment::class.java.simpleName

    companion object {
        val FRAGMENT_NAME: String = PersonDetailsFragment::class.java.name
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val filmLis:MutableList<FilmModel> = arrayListOf()
    private val viewModel: PersonDetailsViewModel by lazy { ViewModelProviders.of(this,viewModelFactory).get(PersonDetailsViewModel::class.java) }
    val adapter : FilmAdapter by lazy { FilmAdapter(arrayListOf()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentPersonDetailsBinding = inflate(inflater, R.layout.fragment_person_details, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val person = arguments!!.getParcelable(Constants.PERSON_EXTRA_KEY) as PersonModel
        filmsRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        filmsRv.adapter = adapter
        adapter.clear()
        with(viewModel) {
            onPersonRecieved(person)
            filmLiveData.observe(this@PersonDetailsFragment, Observer {
                it?.let { it1 -> filmLis.add(it1) }
                Log.d("pallav1","test:"+filmLis.size)
                initFilmView(it)
            }
            )
            error.observe(this@PersonDetailsFragment, Observer {
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })
            speciesLiveData.observe(this@PersonDetailsFragment, Observer {
                initSpeciesView(it)
            }
            )
            planetLiveData.observe(this@PersonDetailsFragment, Observer {
                initPlanetView(it)
            }
            )
            personLiveData.observe(this@PersonDetailsFragment, Observer {
                initPersonView(it)
            }
            )
        }
    }

    private fun initFilmView(it: FilmModel?) {
        it?.let { it1 -> adapter.add(filmLis) }

    }

    private fun initSpeciesView(it: SpecieModel?) {
        languageTv.text = it!!.language
        specieNameTv.text = it.name
    }

    private fun initPlanetView(it: PlanetModel?) {
        homeworldTv.text = it!!.name
        populationTv.text = it.population

    }

    private fun initPersonView(it: PersonModel?) {
        nameTv.text = it!!.name
        birthYearTv.text = it.birth_year
        heightTv.text = it.height
    }
}