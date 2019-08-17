package com.whackyapps.pallavgrover.pallav_starwars_challenge.ui

import android.content.Context
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.FilmModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.BR.item
import com.whackyapps.pallavgrover.pallav_starwars_challenge.databinding.FilmDetailsListItemBinding
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.base.DataBindingViewHolder

class FilmAdapter(
    private var items: MutableList<FilmModel> = arrayListOf<FilmModel>()
) : RecyclerView.Adapter<FilmAdapter.SimpleVideoHolder>() {
    override fun getItemCount(): Int = items.size
    lateinit var context:Context

    override fun onBindViewHolder(holder: SimpleVideoHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleVideoHolder {
        context = parent.context
        val binding  = FilmDetailsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SimpleVideoHolder(binding)
    }

    inner class SimpleVideoHolder(dataBinding: ViewDataBinding)
        : DataBindingViewHolder<FilmModel>(dataBinding)  {

        override fun onBind(t: FilmModel): Unit = with(t) {
            dataBinding.setVariable(item,t)
        }
    }

    fun add(list: List<FilmModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}