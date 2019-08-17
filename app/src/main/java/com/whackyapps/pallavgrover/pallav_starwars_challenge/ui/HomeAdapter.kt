package com.whackyapps.pallavgrover.pallav_starwars_challenge.ui

import android.content.Context
import android.content.Intent
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whackyapps.pallavgrover.pallav_starwars_challenge.BR.item
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PersonModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.databinding.CharachterRowBinding
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.Constants.PERSON_EXTRA_KEY
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.base.DataBindingViewHolder

class HomeAdapter(
    private var items: MutableList<PersonModel> = arrayListOf<PersonModel>()
) : RecyclerView.Adapter<HomeAdapter.SimpleVideoHolder>() {
    override fun getItemCount(): Int = items.size
    lateinit var context:Context
    override fun onBindViewHolder(holder: SimpleVideoHolder, position: Int) {
        holder.onBind(items[position])
        holder.itemView.setOnClickListener(View.OnClickListener {
            Log.d("pal", "getHomeData.success() called with:"+items[position])
            val intent = Intent(context, PersonDetailsActivity::class.java)
            intent.putExtra(PERSON_EXTRA_KEY, items[position])
            context.startActivity(intent)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleVideoHolder {
        context = parent.context
        val binding  = CharachterRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SimpleVideoHolder(binding,parent.context)
    }

    inner class SimpleVideoHolder(dataBinding: ViewDataBinding,context: Context)
        : DataBindingViewHolder<PersonModel>(dataBinding)  {

        override fun onBind(t: PersonModel): Unit = with(t) {
            dataBinding.setVariable(item,t)
        }
    }

    fun add(list: List<PersonModel>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}