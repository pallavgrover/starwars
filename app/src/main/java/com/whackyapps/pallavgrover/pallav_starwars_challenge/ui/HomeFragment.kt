package com.whackyapps.pallavgrover.pallav_starwars_challenge.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.SearchPersonResponseModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.databinding.FragmentHomeBinding
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.base.RecyclerViewPaginator
import javax.inject.Inject
import android.app.Activity
import android.support.v4.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager
import com.whackyapps.pallavgrover.pallav_starwars_challenge.R
import android.content.Context.INPUT_METHOD_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.view.MotionEvent




class HomeFragment : DaggerFragment(){
    private val TAG: String = HomeFragment::class.java.simpleName

    companion object {
        val FRAGMENT_NAME: String = HomeFragment::class.java.name
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by lazy { ViewModelProviders.of(this,viewModelFactory).get(HomeViewModel::class.java) }
    val adapter : HomeAdapter by lazy { HomeAdapter(arrayListOf()) }
    var flag:Boolean=false
    lateinit var query:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentHomeBinding = inflate(inflater, R.layout.fragment_home, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            homeData.observe(this@HomeFragment, Observer {
                initView(it)
            }
            )
            error.observe(this@HomeFragment, Observer {
                progressBar_home.visibility= View.GONE
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })
            resetResults.observe(this@HomeFragment, Observer {
                viewModel.loadingSearchResultsLiveData.value = false
                adapter.clear()
            })
            searchResults.observe(this@HomeFragment, Observer {
                if(flag && it!!.results.isNotEmpty()){
                    adapter.add(it.results)
                }else{
                    if(query.isNotEmpty()) {
                        initView(it)
                    }else{
                        adapter.clear()
                    }
                }
            }
            )
            loadingSearchResultsLiveData.observe(this@HomeFragment, Observer {
                it?.let {
                    if (it) {
                        progressBar_home.visibility = View.VISIBLE
                    } else {
                        progressBar_home.visibility = View.GONE
                    }
                }
            })
        }
        setUpTextInput()
    }
    private fun setUpTextInput() {
        queryEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    query = it.toString()
                    adapter.clear()
                    flag = false
                    viewModel.search(query,1)

                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })


        view!!.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard(v)
            false
        })
        rv_main_home!!.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard(v)
            false
        })
    }
    fun hideKeyboard(view: View) {
        val inputMethodManager = activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager!!.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun initView(it: SearchPersonResponseModel?) {
        rv_main_home.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_main_home.adapter = adapter
        adapter.clear()
        if (it!!.results.isNotEmpty()) {
            adapter.add(it.results)
        }else{
            Toast.makeText(context, context?.getString(R.string.empty_list), android.widget.Toast.LENGTH_LONG).show()
        }

        val recyclerViewPaginator = object : RecyclerViewPaginator(rv_main_home) {
            override val isLastPage: Boolean
                get() = viewModel.isLastPage()


            override fun loadMore(currentPage: Int) {
                viewModel.loadData(currentPage)
                flag = true
            }

        }

        rv_main_home.addOnScrollListener(recyclerViewPaginator as RecyclerViewPaginator)
    }
}